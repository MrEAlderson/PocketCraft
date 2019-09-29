package de.marcely.pocketcraft.bedrock.server.player;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import com.whirvis.jraknet.peer.RakNetClientPeer;

import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.bedrock.server.player.sequence.Sequence;
import lombok.Getter;

public class Player {
	
	@Getter private final RakNetClientPeer client;
	
	@Getter private final List<PacketListener> packetListeners = new ArrayList<>(4);
	
	public Player(RakNetClientPeer client){
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
	
	private void sendPacket(PCPacket packet, boolean needACK){
		for(PacketListener listener:this.packetListeners){
			if(!listener.onSend(packet))
				return;
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
}