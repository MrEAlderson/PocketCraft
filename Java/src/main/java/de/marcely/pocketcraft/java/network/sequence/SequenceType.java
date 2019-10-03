package de.marcely.pocketcraft.java.network.sequence;

import de.marcely.pocketcraft.java.network.sequence.type.*;

public enum SequenceType {
	
	// do not change order
	HANDSHAKE, // 0
	SERVER_INFO, // 1
	LOGIN, // 2
	PLAY, // 3
	DEAD; // 4
	
	public byte getId(){
		return (byte) this.ordinal();
	}
	
	public Sequence newV1ClientInstance(SequenceHolder holder){
		switch(this){
		case DEAD:
			return new DeadSequence(holder);
		case HANDSHAKE:
		case LOGIN:
			break;
		case PLAY:
			return new PlaySequence(holder);
		case SERVER_INFO:
			break;
		}
		
		return null;
	}
}