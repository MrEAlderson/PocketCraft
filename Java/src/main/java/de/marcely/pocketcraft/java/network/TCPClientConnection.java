package de.marcely.pocketcraft.java.network;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.PacketBuilder;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.Getter;

public class TCPClientConnection extends Connection {
	
	@Getter private final InetAddress address;
	@Getter private final int port;
	
	private Channel channel;
	
	public TCPClientConnection(InetAddress address, int port){
		this.address = address;
		this.port = port;
	}
	
	public void run() throws IOException {
		if(!isClosed())
			return;
		
		final Bootstrap bootstrap = new Bootstrap();
		
		bootstrap.group(new NioEventLoopGroup());
		bootstrap.channel(NioSocketChannel.class);
		bootstrap.remoteAddress(this.address, this.port);
		bootstrap.handler(new ChannelInitializer<SocketChannel>(){
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new PacketDecoder());
				ch.pipeline().addLast(new PacketEncoder());
				ch.pipeline().addLast(new PacketHandler());
			}
		});
		
		this.channel = bootstrap.connect().channel();
	}
	
	@Override
	public boolean isClosed(){
		return this.channel == null || !this.channel.isOpen();
	}

	@Override
	public void write(Packet packet){
		this.channel.write(packet);
	}
	
	@Override
	public void close() throws IOException {
		if(!isClosed())
			return;
		
		this.channel.close();
	}
	
	
	
	
	private class PacketDecoder extends ByteToMessageDecoder {

		@Override
		protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
			final byte[] data = new byte[in.readableBytes()];
			
			out.add(PacketBuilder.construct(data, getSharedKey(), getCompressionThreshold(), getProtocol(), true));
		}
	}
	
	private class PacketEncoder extends MessageToByteEncoder<Packet> {

		@Override
		protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {
			final int packetId = packet.getProperties().getId(getProtocol().getProtocolVersion());
			
			out.writeBytes(PacketBuilder.deconstruct(packet, packetId, getSharedKey(), getCompressionThreshold()));
		}
	}
	
	private class PacketHandler extends SimpleChannelInboundHandler<Packet> {
		
		@Override
		public void channelActive(ChannelHandlerContext channelhandlercontext) throws Exception {
			getInterface().onReady();
		}
		
		@Override
		public void channelInactive(ChannelHandlerContext channelhandlercontext) throws Exception {
			getInterface().onClose();
		}
		
		@Override
		protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
			packetReadQueue.add(packet);
		}
	}
}