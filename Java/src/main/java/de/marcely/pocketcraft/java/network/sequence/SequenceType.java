package de.marcely.pocketcraft.java.network.sequence;

import de.marcely.pocketcraft.java.network.sequence.type.*;
import de.marcely.pocketcraft.java.network.sequence.type.client.v1.*;

public enum SequenceType {
	
	// do not change order
	HANDSHAKE, // 0
	STATUS, // 1
	LOGIN, // 2
	PLAY, // 3
	DEAD; // 4
	
	public byte getId(){
		return (byte) this.ordinal();
	}
	
	public Sequence<ClientSequenceHolder> newV1ClientInstance(ClientSequenceHolder holder){
		switch(this){
		case DEAD:
			return new DeadSequence<ClientSequenceHolder>(holder);
		case HANDSHAKE:
			return new V1HandshakeSequence(holder);
		case LOGIN:
			return new V1LoginSequence(holder);
		case PLAY:
			return new PlaySequence<ClientSequenceHolder>(holder);
		case STATUS:
			return new V1StatusSequence(holder);
		}
		
		return null;
	}
	
	public Sequence<ServerSequenceHolder> newV1ServerInstance(ServerSequenceHolder holder){
		return null;
	}
}