package de.marcely.pocketcraft.bedrock.server.player;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.whirvis.jraknet.RakNetPacket;
import com.whirvis.jraknet.peer.RakNetClientPeer;
import com.whirvis.jraknet.protocol.Reliability;

import de.marcely.pocketcraft.bedrock.BedrockConfig;
import de.marcely.pocketcraft.bedrock.component.GameRules;
import de.marcely.pocketcraft.bedrock.component.UserInfo;
import de.marcely.pocketcraft.bedrock.component.world.entity.Entity;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.bedrock.network.packet.PacketBatch;
import de.marcely.pocketcraft.bedrock.network.packet.PacketDisconnect;
import de.marcely.pocketcraft.bedrock.network.packet.PacketGameRules;
import de.marcely.pocketcraft.bedrock.network.packet.PacketType;
import de.marcely.pocketcraft.bedrock.server.BedrockServer;
import de.marcely.pocketcraft.bedrock.server.player.sequence.Sequence;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import lombok.Getter;
import lombok.Setter;

public class BedrockClient {
	
	@Getter private final BedrockServer server;
	@Getter private final RakNetClientPeer client;
	
	@Getter private final List<PacketListener> packetListeners = new ArrayList<>(4);
	
	@Getter @Setter private Entity entity;
	@Getter @Setter private UserInfo info;
	@Getter @Setter private String username;
	
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
		sendPackets(Arrays.asList(packet));
	}
	
	public void sendPackets(List<PCPacket> packets){
		sendPackets(packets, Reliability.RELIABLE_ORDERED, BedrockConfig.COMPRESSION_LEVEL);
	}
	
	public void sendPackets(List<PCPacket> packets, Reliability rel){
		sendPackets(packets, rel, BedrockConfig.COMPRESSION_LEVEL);
	}
	
	public void sendPackets(List<PCPacket> packets, int compressionLevel){
		sendPackets(packets, Reliability.RELIABLE_ORDERED, BedrockConfig.COMPRESSION_LEVEL);
	}
	
	private void sendPackets(List<PCPacket> packets, Reliability rel, int compressionLevel){
		final PacketBatch batch = new PacketBatch();
		
		try{
			batch.writePayload(packets, compressionLevel);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		sendBatchPacket(batch, rel);
	}
	
	private void sendBatchPacket(PacketBatch packet, Reliability rel){
		for(PacketListener listener:this.packetListeners){
			if(!listener.onSend(packet))
				return;
		}
		
		{
			final RakNetPacket rnPacket = new RakNetPacket(packet.getType().getId());
			final EByteArrayWriter stream = new EByteArrayWriter();
			
			try{
				packet.encode(stream);
				
				rnPacket.write(stream.toByteArray());
				
				stream.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			this.client.sendMessage(rel, packet.getType().getChannel().id, rnPacket);
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
		this.entity = new Entity(id){
			public EntityType getType(){
				return EntityType.PLAYER;
			}
		};
	}
	
	public void sendGameRules(GameRules gr){
		final PacketGameRules packet = (PacketGameRules) PacketType.GameRules.newInstance();
		
		packet.gameRules = gr;
		
		this.sendPacket(packet);
	}
	
	/**
	 * 
	 * @return True: He got instantly disconnected. False: Sending him a packet with the reason
	 */
	public boolean kick(@Nullable String message){
		// seems to have no reason, so there's also no need to send the kick packet to the player
		if(message == null || message.isEmpty()){
			this.client.disconnect();
			return true;
		}
		
		// disconnect when player has received info packet
		{
			final PacketDisconnect out = new PacketDisconnect();
			
			out.reason = message;
			out.hideScreen = false;
			
			sendPackets(Arrays.asList(out));
		}
		
		return false;
	}
}
