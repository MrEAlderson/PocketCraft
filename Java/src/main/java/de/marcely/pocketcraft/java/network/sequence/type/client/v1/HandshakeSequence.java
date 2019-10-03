package de.marcely.pocketcraft.java.network.sequence.type.client.v1;

import de.marcely.pocketcraft.java.network.sequence.Sequence;
import de.marcely.pocketcraft.java.network.sequence.SequenceHolder;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;

public class HandshakeSequence extends Sequence {

	public HandshakeSequence(SequenceHolder holder){
		super(holder);
	}

	@Override
	public void run(Sequence oldSequence){
		
	}

	@Override
	public SequenceType getType(){
		return SequenceType.HANDSHAKE;
	}
}