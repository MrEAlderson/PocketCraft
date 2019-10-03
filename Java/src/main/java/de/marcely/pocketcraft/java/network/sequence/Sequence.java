package de.marcely.pocketcraft.java.network.sequence;

import de.marcely.pocketcraft.java.network.packet.PacketAdapter;
import lombok.Getter;

public abstract class Sequence implements PacketAdapter {
	
	@Getter protected final SequenceHolder holder;
	
	public Sequence(SequenceHolder holder){
		this.holder = holder;
	}
	
	public abstract void run(Sequence oldSequence);
	
	public abstract SequenceType getType();
}
