package de.marcely.pocketcraft.java.network.protocol;

import de.marcely.pocketcraft.java.network.sequence.Sequence;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;

public class ProtocolV8D9 extends Protocol {
	
	@Override
	public int getProtocolVersion(){
		return 47;
	}

	@Override
	public void defineIds(){
		
	}

	@Override
	public Sequence newSequenceInstance(SequenceType type){
		return type.newV1ClientInstance(null);
	}
}