package de.marcely.pocketcraft.raknet.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import lombok.Getter;

public class UDPServer {
	
	public final int port, bufferSize;
	
	private DatagramSocket socket;
	@Getter private final Queue<QueuedDatagramPacket> queue = new ConcurrentLinkedQueue<>();
	
	public UDPServer(int port, int bufferSize){
		this.port = port;
		this.bufferSize = bufferSize;
	}
	
	public boolean isRunning(){ return this.socket != null; }
	
	public boolean run() throws SocketException {
		if(isRunning()) return false;
		
		this.socket = new DatagramSocket(port);
		
		new Thread(){
			public void run(){
				while(isRunning()){
					final byte[] buffer = new byte[bufferSize];
					final DatagramPacket packet = new DatagramPacket(buffer, bufferSize);
					
					try{
						socket.receive(packet);
					}catch(IOException e){
						final String msg = e.getMessage();
						
						if(msg == null || (!msg.equals("socket closed")))
							e.printStackTrace();
						else
							return;
					}
					
					queue.add(new QueuedDatagramPacket(Arrays.copyOf(buffer, packet.getLength()), packet.getAddress(), packet.getPort()));
				}
			}
		}.start();
		
		return true;
	}
	
	public boolean shutdown(){
		if(!isRunning()) return false;
		
		this.socket.close();
		this.socket = null;
		
		return true;
	}
	
	public boolean sendPacket(byte[] buffer, InetAddress address, int port){
		if(!isRunning()) return false;
		
		try{
			this.socket.send(new DatagramPacket(buffer, buffer.length, address, port));
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return true;
	}
}