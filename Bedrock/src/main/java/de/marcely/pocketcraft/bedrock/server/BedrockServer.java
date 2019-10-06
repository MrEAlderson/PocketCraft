package de.marcely.pocketcraft.bedrock.server;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.whirvis.jraknet.RakNetException;
import com.whirvis.jraknet.peer.RakNetClientPeer;
import com.whirvis.jraknet.server.RakNetServer;

import de.marcely.pocketcraft.bedrock.server.player.BedrockClient;
import lombok.Getter;

public class BedrockServer {
	
	@Getter private RakNetServer rakNet;
	
	@Getter private Map<RakNetClientPeer, BedrockClient> connections = new HashMap<>();
	
	@Getter private List<ServerListener> listeners = new ArrayList<>(4);
	
	public BedrockServer(InetAddress address, int port){
		this(address, port, RakNetServer.INFINITE_CONNECTIONS);
	}
	
	public BedrockServer(InetAddress address, int port, int slots){
		this.rakNet = new RakNetServer(address, port, slots);
		
		this.rakNet.addListener(new BaseRakNetServerListener(this));
	}
	
	public int getPort(){
		return this.rakNet.getPort();
	}
	
	public boolean isRunning(){
		return this.rakNet.isRunning();
	}
	
	public Collection<BedrockClient> getPlayers(){
		return this.connections.values();
	}
	
	public @Nullable BedrockClient getPlayer(RakNetClientPeer client){
		return this.connections.get(client);
	}
	
	public boolean run() throws IllegalStateException, RakNetException {
		if(isRunning())
			return false;
		
		this.rakNet.start();
		
		return true;
	}
	
	public boolean stop(){
		if(!isRunning())
			return false;
		
		this.rakNet.shutdown();
		
		return true;
	}
	
	public boolean registerListener(ServerListener listener){
		if(this.listeners.contains(listener))
			return false;
		
		return this.listeners.add(listener);
	}
	
	public boolean unregisterListener(ServerListener listener){
		return this.listeners.remove(listener);
	}
}