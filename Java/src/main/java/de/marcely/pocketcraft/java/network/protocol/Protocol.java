package de.marcely.pocketcraft.java.network.protocol;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.sequence.Sequence;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;

public abstract class Protocol {
	
	public abstract int getProtocolVersion();
	
	public abstract Sequence newSequenceInstance(SequenceType type);
	
	public abstract void defineIds();
	
	@SuppressWarnings("unchecked")
	protected void setClient(Class<Packet>... packets){
		
	}
	
	@SuppressWarnings("unchecked")
	protected void setServer(Class<Packet>... packets){
		
	}
}
