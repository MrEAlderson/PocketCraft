package de.marcely.pocketcraft.java.network;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.protocol.Protocol;
import de.marcely.pocketcraft.java.util.EByteBuf;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
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
	private EventLoopGroup eventLoopGroup;
	
	public TCPClientConnection(InetAddress address, int port){
		this.address = address;
		this.port = port;
	}
	
	@Override
	public void open() throws IOException {
		if(!isClosed())
			return;
		
		final Bootstrap bootstrap = new Bootstrap();
		
		bootstrap.group(this.eventLoopGroup = new NioEventLoopGroup());
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
		
		this.eventLoopGroup.shutdownGracefully();
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
		
		try{
			this.channel.writeAndFlush(packet).sync();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public TCPClientConnection clone(){
		return new TCPClientConnection(this.address, this.port);
	}
	
	
	
	
	private class PacketDecoder extends ByteToMessageDecoder {
	
		@Override
		protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
			try{
				packetBuilder.construct(
						new EByteBuf(buf),
						getInterface().getProtocol(),
						getInterface().getSequence().getType(),
						false,
						out);
			}catch(IOException e){
				if(e.getMessage() != null && e.getMessage().startsWith("Unkown packet with id ")){
					// System.out.println(e.getMessage());
				}else
					e.printStackTrace();
			}
		}
	}
	
	private class PacketEncoder extends MessageToByteEncoder<Packet> {

		@Override
		protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {
			final Integer packetId = packet.getProperties().getId(getInterface().getProtocol().getProtocolVersion(), Protocol.CLIENT);
			
			if(packetId == null)
				throw new IOException("Failed to look for packet id of " + packet.getClass().getName());
			
			out.writeBytes(packetBuilder.deconstruct(
					packet,
					packetId));
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
			getInterface().handlePacket(packet);
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