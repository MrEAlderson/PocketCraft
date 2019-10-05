package de.marcely.pocketcraft.java.network.sequence;

import de.marcely.pocketcraft.java.network.packet.PacketAdapter;
import lombok.Getter;

public abstract class Sequence<T extends SequenceHolder> implements PacketAdapter {
	
	@Getter protected final T holder;
	
	public Sequence(T holder){
		this.holder = holder;
	}
	
	public abstract void run(Sequence<T> oldSequence);
	
	public abstract SequenceType getType();
}
