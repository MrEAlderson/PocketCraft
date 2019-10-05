package de.marcely.pocketcraft.bedrock.server.player;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.whirvis.jraknet.RakNetPacket;
import com.whirvis.jraknet.peer.RakNetClientPeer;
import com.whirvis.jraknet.protocol.Reliability;

import de.marcely.pocketcraft.bedrock.component.GameRules;
import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.bedrock.network.packet.PacketBatch;
import de.marcely.pocketcraft.bedrock.network.packet.PacketOutGameRules;
import de.marcely.pocketcraft.bedrock.network.packet.PacketType;
import de.marcely.pocketcraft.bedrock.server.BedrockServer;
import de.marcely.pocketcraft.bedrock.server.player.sequence.Sequence;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.world.entity.Entity;
import de.marcely.pocketcraft.bedrock.world.entity.EntityType;
import lombok.Getter;

public class BedrockClient {
	
	@Getter private final BedrockServer server;
	@Getter private final RakNetClientPeer client;
	
	@Getter private final List<PacketListener> packetListeners = new ArrayList<>(4);
	
	@Getter private Entity entity;
	
	public BedrockClient(BedrockServer server, RakNetClientPeer client){
		this.server = server;
		this.client = client;
		
		this.packetListeners.add(Sequence.get(Sequence.DEAD, this));
	}
	
	public boolean registerListener(PacketListener listener){
		if(listener instanceof Sequence)
			throw new InvalidParameterException("listener can't be an instance of Sequence");
		
		if(this.packetListeners.contains(listener))
			return false;
		
		this.packetListeners.add(listener);
		
		return true;
	}
	
	public boolean unregisterListener(PacketListener listener){
		return this.packetListeners.remove(listener);
	}
	
	public void sendPacket(PCPacket packet){
		sendPacket(packet, true);
	}
	
	private void sendPacket(PCPacket packet, boolean reliable){
		if(packet.type != PacketType.Batch){
			final PacketBatch batch = new PacketBatch();
			
			try{
				batch.writePayload(Arrays.asList(packet), packet.compressionLevel);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			sendPacket(batch);
			
			return;
		}
		
		for(PacketListener listener:this.packetListeners){
			if(!listener.onSend(packet))
				return;
		}
		
		{
			final RakNetPacket rnPacket = new RakNetPacket(packet.type.id);
			final EByteArrayWriter stream = new EByteArrayWriter();
			
			try{
				packet.encode(stream);
				
				rnPacket.write(stream.toByteArray());
				
				stream.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			this.client.sendMessage(reliable ? Reliability.RELIABLE_ORDERED : Reliability.UNRELIABLE, 2, rnPacket);
		}
	}
	
	public void handlePacket(PCPacket packet) throws Exception {
		for(PacketListener listener:this.packetListeners){
			if(!listener.onReceive(packet))
				return;
		}
	}
	
	public void setSequence(Sequence seq){
		// find old one
		Sequence oldSeq = null;
		
		for(PacketListener listener:this.packetListeners){
			if(listener instanceof Sequence){
				oldSeq = (Sequence) listener;
				break;
			}
		}
		
		if(oldSeq.getClass() == seq.getClass())
			return;
		
		// remove old one
		this.packetListeners.remove(oldSeq);
		
		// replace with new one
		this.packetListeners.add(seq);
	}
	
	public void initEntity(long id){
		this.entity = new Entity(EntityType.PLAYER, id);
	}
	
	public void sendGameRules(GameRules gr){
		final PacketOutGameRules packet = (PacketOutGameRules) PacketType.OutGameRules.newInstance();
		
		packet.gameRules = gr;
		
		this.sendPacket(packet);
	}
}
