package de.marcely.pocketcraft.java.client;

import java.util.ArrayList;
import java.util.List;

import de.marcely.pocketcraft.java.network.Connection;
import de.marcely.pocketcraft.java.network.ConnectionInterface;
import de.marcely.pocketcraft.java.network.LoginGoal;
import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.PacketListener;
import de.marcely.pocketcraft.java.network.protocol.Protocol;
import de.marcely.pocketcraft.java.network.sequence.Sequence;
import de.marcely.pocketcraft.java.network.sequence.SequenceHolder;
import de.marcely.pocketcraft.java.network.sequence.type.DeadSequence;
import lombok.Getter;

public class JavaClient implements SequenceHolder, ConnectionInterface {
	
	@Getter private final Connection connection;
	@Getter private final Protocol protocol;
	@Getter private final LoginGoal goal;
	
	@Getter private final List<PacketListener> packetListeners = new ArrayList<>(4);
	
	public JavaClient(Connection conn, Protocol protocol, LoginGoal goal){
		this.connection = conn;
		this.protocol = protocol;
		this.goal = goal;
		
		this.packetListeners.add(new DeadSequence(this));
	}

	@Override
	public void setSequence(Sequence seq){
		for(int i=0; i<this.packetListeners.size(); i++){
			final PacketListener listener = this.packetListeners.get(i);
			
			if(listener instanceof Sequence){
				if(seq == listener)
					return;
				
				this.packetListeners.set(i, seq);
				
				seq.run((Sequence) listener);
				return;
			}
		}
	}

	@Override
	public Sequence getSequence(){
		for(int i=0; i<this.packetListeners.size(); i++){
			final PacketListener listener = this.packetListeners.get(i);
			
			if(listener instanceof Sequence)
				return (Sequence) listener;
		}
		
		return null;
	}

	@Override
	public void sendPacket(Packet packet){
		this.connection.write(packet);
	}

	@Override
	public Object getLoginInfo(){
		return null;
	}

	@Override
	public void completeLogin(Object rawResult){
		
	}

	@Override
	public void onReady(){
		
	}

	@Override
	public void onClose(){
		
	}
}