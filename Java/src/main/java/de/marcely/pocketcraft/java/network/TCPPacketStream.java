package de.marcely.pocketcraft.java.network;

import java.io.IOException;
import java.net.InetAddress;

import de.marcely.pocketcraft.java.network.packet.Packet;
import lombok.Getter;

public class TCPPacketStream implements PacketStream {
	
	@Getter private final InetAddress address;
	@Getter private final int port;
	
	public TCPPacketStream(InetAddress address, int port){
		this.address = address;
		this.port = port;
	}
	
	public void run() throws IOException {
		if(!isClosed())
			return;
		
		
	}
	
	@Override
	public boolean isClosed(){
		return false;
	}

	@Override
	public Packet fetch(){
		return null;
	}
	
	@Override
	public void close() throws IOException {
		if(!isClosed())
			return;
		
		
	}
}