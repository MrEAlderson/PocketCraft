package de.marcely.pocketcraft.translate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.whirvis.jraknet.RakNetException;

import de.marcely.pocketcraft.bedrock.server.BedrockServer;
import de.marcely.pocketcraft.java.client.ClientAdapter;
import de.marcely.pocketcraft.java.client.JavaClient;
import de.marcely.pocketcraft.java.network.Connection;
import de.marcely.pocketcraft.java.network.LoginGoal;
import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.*;
import de.marcely.pocketcraft.java.network.protocol.Protocol;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.BedrockServerInterface;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.java.*;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.utils.callback.R1Callback;
import lombok.Getter;

public class BedrockToJavaTranslator extends Translator {
	
	@Getter private final BedrockServer bedrockServer;
	@Getter private final Connection javaConnection;
	@Getter private final Protocol javaProtocol;
	
	private final Map<InetSocketAddress, Player> players = new HashMap<>();
	
	public BedrockToJavaTranslator(BedrockServer bedrockServer, Connection javaConnection, Protocol javaProtocol){
		this.bedrockServer = bedrockServer;
		this.javaConnection = javaConnection;
		this.javaProtocol = javaProtocol;
	}
	
	@Override
	public boolean run() throws IllegalStateException, RakNetException {
		final boolean result = this.bedrockServer.run();
		
		if(!result)
			return false;
		
		this.bedrockServer.registerListener(new BedrockServerInterface(this));
		
		definePacketTranslators();
		
		return true;
	}
	
	public void openConnection(R1Callback<JavaClient> callback, LoginGoal goal){
		final JavaClient client = new JavaClient(this.javaConnection.clone(), this.javaProtocol, goal);
		
		client.registerListener(new ClientAdapter(){
			public void onReady(){
				client.unregisterListener(this);
				
				callback.call(client);
			}
		});
		
		try{
			if(!client.connect())
				throw new IOException("Failed for unkown reason");
		}catch(IOException e){
			e.printStackTrace();
			callback.call(null);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Packet>JavaPacketTranslator<T> getTranslator(T packet){
		return (JavaPacketTranslator<T>) packet.getProperties().getMetadata(getTranslatorMetaName());
	}
	
	private void definePacketTranslators(){
		define(V8D9PacketPlayMapChunk.class, TV8D9PacketPlayMapChunk.class);
		define(V8D9PacketPlayMapChunkBulk.class, TV8D9PacketPlayMapChunkBulk.class);
		define(V8D9PacketPlayLogin.class, TV8D9PacketPlayLogin.class);
		define(V8D9PacketPlayKeepAlive.class, TV8D9PacketPlayKeepAlive.class);
		define(V8D9PacketPlayWorldTime.class, TV8D9PacketPlayWorldTime.class);
		define(V8D9PacketPlayTeleport.class, TV8D9PacketPlayTeleport.class);
	}
	
	protected void define(Class<? extends Packet> packet, Class<? extends JavaPacketTranslator<?>> translatorClazz){
		try{
			final PacketProperties properties = (PacketProperties) packet.getField("PROPERTIES").get(null);
			
			properties.setMetadata(getTranslatorMetaName(), translatorClazz.newInstance());
		}catch(IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e){
			e.printStackTrace();
			throw new IllegalStateException("Missing or invalid PROPERTIES field for " + packet.getName());
		}catch(InstantiationException e){
			e.printStackTrace();
			throw new IllegalStateException("Failed to create instance of " + translatorClazz.getClass());
		}
	}
	
	private String getTranslatorMetaName(){
		return this.javaProtocol.getProtocolVersion() + "_translator";
	}
	
	public Collection<Player> getPlayers(){
		return this.players.values();
	}
	
	public @Nullable Player getPlayer(InetSocketAddress address){
		return this.players.get(address);
	}
	
	public void addPlayer(Player player){
		this.players.put(player.getBedrock().getClient().getAddress(), player);
	}
	
	public boolean removePlayer(Player player){
		return this.players.remove(player.getBedrock().getClient().getAddress()) != null;
	}
}