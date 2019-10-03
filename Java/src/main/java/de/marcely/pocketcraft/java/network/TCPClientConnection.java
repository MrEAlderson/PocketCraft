package de.marcely.pocketcraft.java.network;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.PacketBuilder;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.Getter;

public class TCPClientConnection extends Connection {
	
	@Getter private final InetAddress address;
	@Getter private final int port;
	
	private Channel channel;
	
	public TCPClientConnection(InetAddress address, int port){
		this.address = address;
		this.port = port;
	}
	
	@Override
	public void open() throws IOException {
		if(!isClosed())
			return;
		
		final Bootstrap bootstrap = new Bootstrap();
		
		bootstrap.group(new NioEventLoopGroup());
		bootstrap.channel(NioSocketChannel.class);
		bootstrap.remoteAddress(this.address, this.port);
		bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);
		bootstrap.handler(new ChannelInitializer<SocketChannel>(){
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast("timeout_handler", new ReadTimeoutHandler(15));
				ch.pipeline().addLast("packet_decoder", new PacketDecoder());
				ch.pipeline().addLast("packet_encoder", new PacketEncoder());
				ch.pipeline().addLast("packet_handler", new PacketHandler());
			}
		});
		
		try{
			final ChannelFuture future = bootstrap.connect();
			
			this.channel = future.channel();
			
			future.sync();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void close() throws IOException {
		if(isClosed())
			return;
		
		this.channel.close();
	}
	
	@Override
	public boolean isClosed(){
		return this.channel == null || !this.channel.isOpen();
	}

	@Override
	public void write(Packet packet){
		if(isClosed()){
			System.out.println("oop");
			return;
		}
		
		System.out.println("okii " + packet.getClass().getName());
		
		try {
			this.channel.writeAndFlush(packet).sync();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	private class PacketDecoder extends ByteToMessageDecoder {

		@Override
		protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
			final byte[] data = new byte[in.readableBytes()];
			
			in.readBytes(data);
			
			System.out.println("read");
			
			out.add(PacketBuilder.construct(
					data,
					getSharedKey(),
					getCompressionThreshold(),
					getInterface().getProtocol(),
					getInterface().getSequence().getType(),
					true));
		}
	}
	
	private class PacketEncoder extends MessageToByteEncoder<Packet> {

		@Override
		protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {
			final int packetId = packet.getProperties().getId(getInterface().getProtocol().getProtocolVersion());
			
			System.out.println("write");
			
			out.writeBytes(PacketBuilder.deconstruct(
					packet,
					packetId,
					getSharedKey(),
					getCompressionThreshold()));
		}
	}
	
	private class PacketHandler extends SimpleChannelInboundHandler<Packet> {
		
		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			getInterface().onReady();
		}
		
		@Override
		public void channelInactive(ChannelHandlerContext ctx) throws Exception {
			getInterface().onClose();
		}
		
		@Override
		protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
			packetReadQueue.add(packet);
		}
		
		@Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
			if(cause instanceof ReadTimeoutException)
				channelInactive(ctx);
			else
				cause.printStackTrace();
		}
	}
}