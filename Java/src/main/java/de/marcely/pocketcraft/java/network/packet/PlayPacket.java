package de.marcely.pocketcraft.java.network.packet;

import de.marcely.pocketcraft.java.network.sequence.SequenceType;

public abstract class PlayPacket extends Packet {
	
	@Override
	public SequenceType getSequence(){
		return SequenceType.PLAY;
	}
}