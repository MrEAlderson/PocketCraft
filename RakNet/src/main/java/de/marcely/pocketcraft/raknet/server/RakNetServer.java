package de.marcely.pocketcraft.raknet.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import de.marcely.pocketcraft.raknet.packet.RakNetConnectRequest1Packet;
import de.marcely.pocketcraft.raknet.packet.RakNetConnectResponse1Packet;
import de.marcely.pocketcraft.raknet.packet.RakNetPacket;
import de.marcely.pocketcraft.raknet.packet.RakNetUnconnectedPingPacket;
import de.marcely.pocketcraft.raknet.packet.RakNetUnconnectedPongPacket;
import de.marcely.pocketcraft.raknet.packet.encapsulated.RakNetEncapsulatedPacket;
import de.marcely.pocketcraft.utils.Application;
import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;
import de.marcely.pocketcraft.utils.UThread;
import de.marcely.pocketcraft.utils.logger.Logger;
import lombok.Getter;

/**
 * 
 * @author Marcel S.
 * @date 01.12.2017 (dd/MM/yyyy)
 * @website https://marcely.de/
 */
public abstract class RakNetServer {
	
	private static final int BUFFERSIZE = 2024;
	public static final int TPS = 20;
	
	private final Logger logger = Logger.get(Application.RAKNET, "Core");
	private final UDPServer udpServer;
	
	public final long serverID;
	
	@Getter private boolean running = false;
	public final Map<String, Client> clients = new HashMap<>();
	@Getter private int currentTPS;
	
	public RakNetServer(InetAddress address, int port){
		this.serverID = new Random().nextLong();
		this.udpServer = new UDPServer(address, port, BUFFERSIZE);
	}
	
	public abstract void onConnect(Client client);
	
	public abstract void onDisconnect(Client client, String reason);
	
	public abstract void handleEncapsulated(Client client, RakNetEncapsulatedPacket packet, int flags) throws IOException;
	
	public abstract String getName();
	
	
	
	public InetAddress getAddress(){ return this.udpServer.getAddress(); }
	
	public int getPort(){ return this.udpServer.getPort(); }
	
	public boolean run(){
		if(isRunning())
			return false;
		
		try{
			udpServer.run();
		}catch(SocketException e){
			return false;
		}
		
		this.running = true;
		
		new UThread(Application.RAKNET, "server", getAddress().getHostAddress() + ":" + getPort()){
			long t = System.currentTimeMillis();
			int tps = 0;
			
			public void run(){
				while(running){
					try{
						final long startTime = System.currentTimeMillis();
						
						update();
						tps++;
						
						final long time = System.currentTimeMillis()-startTime;
						
						if(System.currentTimeMillis() >= t+1000L){
							t = System.currentTimeMillis();
							logger.debug("RakNet TPS: " + tps);
							currentTPS = tps;
							tps = 0;
							
							updateSec();
						}
						
						final long sleepTime = 1000/TPS-time;
						
						if(sleepTime >= 1)
							Thread.sleep((long) sleepTime);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}.start();
		
		return true;
	}
	
	public boolean shutdown(){
		if(!isRunning())
			return false;
		
		for(Client client:new ArrayList<>(clients.values()))
			client.disconnect("Server stopped.");
		
		running = false;
		udpServer.shutdown();
		
		return true;
	}
	
	private void update() throws Exception {
		final long time = System.currentTimeMillis();
		
		for(Client client:new ArrayList<>(clients.values()))
			client.handler.update(time);
		
		QueuedDatagramPacket dPacket = null;
		
		while((dPacket = this.udpServer.getQueue().poll()) != null){
			final ByteArrayReader reader = new ByteArrayReader(dPacket.buffer);
			final byte id = reader.readSignedByte();
			final RakNetPacket packet = RakNetPacket.newInstance(id);
			
			if(packet == null){
				logger.warn("Received unkown packet " + Integer.toHexString(id));
				continue;
			}
			
			packet.receive_rawData = dPacket.buffer;
			packet.decode(reader);
			handle(dPacket.address, dPacket.port, packet);
			
			reader.close();
		}
	}
	
	private void updateSec() {
		for(Client client:new ArrayList<>(clients.values()))
			client.handler.update2();
	}
	
	private void handle(InetAddress address, int port, RakNetPacket packet) throws Exception {
		Client client = clients.get(address.getHostAddress() + ":" + port);
		
		if(packet._id == RakNetPacket.TYPE_UNCONNECTED_PING){
			final RakNetUnconnectedPongPacket nPacket = new RakNetUnconnectedPongPacket();
			nPacket.sendPingTime = ((RakNetUnconnectedPingPacket) packet).sendPingTime;
			nPacket.serverID = this.serverID;
			nPacket.serverName = getName();
			
			sendPacket(address, port, nPacket);
			
		}else if(packet._id == RakNetPacket.TYPE_CONNECT_REQUEST1){
			final RakNetConnectResponse1Packet nPacket = new RakNetConnectResponse1Packet();
			nPacket.serverID = serverID;
			nPacket.mtuSize = ((RakNetConnectRequest1Packet) packet).mtuSize;
			
			sendPacket(address, port, nPacket);
		
			client = new Client(this, address, port);
			client.state = ClientState.CONNECTING_1;
			clients.put(client.getIdentifier(), client);
			client.handler.run();
			
		}else if(client != null)
			client.handler.handleNow(packet);
	}
	
	public void sendPacket(InetAddress address, int port, RakNetPacket packet){
		final ByteArrayWriter writer = new ByteArrayWriter();
		
		try{
			writer.writeSignedByte(packet._id);
			packet.encode(writer);
			
			final byte[] data = writer.toByteArray();
			
			sendPacketRaw(address, port, data);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void sendPacketRaw(InetAddress address, int port, byte[] data){
		this.udpServer.sendPacket(data, address, port);
	}
}