package de.marcely.pocketcraft.java.network.sequence.type.client.v1;

import de.marcely.pocketcraft.java.network.sequence.Sequence;
import de.marcely.pocketcraft.java.network.sequence.SequenceHolder;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;

public class V1StatusSequence extends Sequence {

	public V1StatusSequence(SequenceHolder holder){
		super(holder);
	}
	
	@Override
	public SequenceType getType(){
		return SequenceType.STATUS;
	}

	@Override
	public void run(Sequence oldSequence){
		
	}
}
