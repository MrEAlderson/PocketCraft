package de.marcely.pocketcraft.bedrock.server;

import java.net.InetAddress;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.whirvis.jraknet.RakNetException;
import com.whirvis.jraknet.identifier.MinecraftIdentifier;
import com.whirvis.jraknet.peer.RakNetClientPeer;
import com.whirvis.jraknet.server.RakNetServer;

import de.marcely.pocketcraft.bedrock.server.player.Player;
import lombok.Getter;

public class BedrockServer {
	
	@Getter private RakNetServer rakNet;
	
	@Getter private Map<RakNetClientPeer, Player> connections = new HashMap<>();
	
	public BedrockServer(InetAddress address, int port){
		this(address, port, RakNetServer.INFINITE_CONNECTIONS);
	}
	
	public BedrockServer(InetAddress address, int port, int slots){
		this.rakNet = new RakNetServer(address, port, slots);
		
		this.rakNet.addListener(new BaseRakNetServerListener(this));
		
		System.out.println("test");
		this.rakNet.setIdentifier(new MinecraftIdentifier("JRakNet Example Server", 361, "1.12.1", 0, 10,
				this.rakNet.getGloballyUniqueId(), "New World", "Survival"));
	}
	
	public int getPort(){
		return this.rakNet.getPort();
	}
	
	public boolean isRunning(){
		return this.rakNet.isRunning();
	}
	
	public Collection<Player> getPlayers(){
		return this.connections.values();
	}
	
	public @Nullable Player getPlayer(RakNetClientPeer client){
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
}