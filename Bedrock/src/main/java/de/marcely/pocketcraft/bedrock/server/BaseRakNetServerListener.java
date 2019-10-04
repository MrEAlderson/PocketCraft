package de.marcely.pocketcraft.bedrock.server;

import java.net.InetSocketAddress;

import com.whirvis.jraknet.RakNet;
import com.whirvis.jraknet.RakNetPacket;
import com.whirvis.jraknet.peer.RakNetClientPeer;
import com.whirvis.jraknet.protocol.ConnectionType;
import com.whirvis.jraknet.server.RakNetServer;
import com.whirvis.jraknet.server.RakNetServerListener;
import com.whirvis.jraknet.server.ServerPing;

import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.bedrock.network.packet.PacketBatch;
import de.marcely.pocketcraft.bedrock.network.packet.PacketType;
import de.marcely.pocketcraft.bedrock.server.player.Player;
import de.marcely.pocketcraft.bedrock.server.player.sequence.Sequence;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.utils.Application;
import de.marcely.pocketcraft.utils.logger.Logger;

public class BaseRakNetServerListener implements RakNetServerListener {
	
	private final BedrockServer server;
	
	private final Logger logger;
	
	public BaseRakNetServerListener(BedrockServer server){
		this.server = server;
		
		this.logger = Logger.get(Application.BEDROCK_SERVER, "BaseServerListener");
	}
	
	@Override
	public void onConnect(RakNetServer server, InetSocketAddress address, ConnectionType connectionType){
		System.out.println("Client from address " + address + " has connected to the server " + connectionType);
	}
	
	@Override
	public void onLogin(RakNetServer server, RakNetClientPeer client){
		final Player player = new Player(client);
		
		player.setSequence(Sequence.get(Sequence.LOGIN, player));
		
		this.server.getConnections().put(client, player);
		
		System.out.println("Connected!");
	}
	
	@Override
	public void onDisconnect(RakNetServer server, InetSocketAddress address, RakNetClientPeer client, String reason){
		// final Player player = this.server.getPlayer(client);
		
		this.server.getConnections().remove(client);
		
		System.out.println("Disconnected");
	}
	
	@Override
	public void handleMessage(RakNetServer server, RakNetClientPeer client, RakNetPacket rawPacket, int channel){
		final PacketType type = PacketType.TYPES_IN.get(rawPacket.getId());
		final Player player = this.server.getPlayer(client);
		
		if(type == null){
			this.logger.warn("Received weird packet " + RakNet.toHexStringId(rawPacket) + " (" + client.getAddress().getHostString() + ")");
			return;
		}
		
		if(type != PacketType.Batch){
			this.logger.warn("Expected a Batch Packet but received " + type + " instead (" + client.getAddress().getHostString() + ")");
			return;
		}
		
		final PacketBatch packet = (PacketBatch) PacketType.Batch.newInstance();
		
		try{
			packet.decode(new EByteArrayReader(rawPacket.read(rawPacket.remaining())));
			
			for(PCPacket child:packet.readPayload()){
				try{
					player.handlePacket(child);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void onPing(RakNetServer server, ServerPing ping){
		
	}
}
