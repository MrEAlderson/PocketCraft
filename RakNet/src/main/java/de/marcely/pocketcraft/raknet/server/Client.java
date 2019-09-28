package de.marcely.pocketcraft.raknet.server;

import java.net.InetAddress;

import de.marcely.pocketcraft.raknet.packet.RakNetPacket;

/**
 * 
 * @author Marcel S.
 * @date 01.12.2017 (dd/MM/yyyy)
 * @website https://marcely.de/
 */
public class Client {
	
	public final RakNetServer server;
	public final InetAddress address;
	public final int port;
	public final ClientHandler handler;
	
	public long clientID;
	public ClientState state;
	
	public Client(RakNetServer server, InetAddress address, int port){
		this.server = server;
		this.address = address;
		this.port = port;
		this.handler = new ClientHandler(this);
	}
	
	public String getIdentifier(){
		return address.getHostAddress() + ":" + port;
	}
	
	public void sendPacket(RakNetPacket packet){
		handler.sendReadyPacket(packet);
	}
	
	public void handle(RakNetPacket packet) throws Exception {
		handler.handleNow(packet);
	}
	
	public void disconnect(String reason){
		synchronized(server.clients){
			if(!server.clients.containsKey(getIdentifier())) return;
			
			// handle at server side
			server.onDisconnect(Client.this, reason);
			handler.stop();
			server.clients.remove(getIdentifier());
		}
	}
}
