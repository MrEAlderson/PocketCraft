package de.marcely.pocketcraft.java.network.sequence.type;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.sequence.Sequence;
import de.marcely.pocketcraft.java.network.sequence.SequenceHolder;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;

public class DeadSequence<T extends SequenceHolder> extends Sequence<T> {

	public DeadSequence(T holder){
		super(holder);
	}

	@Override
	public SequenceType getType(){
		return SequenceType.DEAD;
	}

	@Override
	public void run(Sequence<T> oldSequence){ }
	
	@Override
	public boolean onSend(Packet packet){
		return false;
	}
	
	@Override
	public boolean onReceive(Packet packet){
		return false;
	}
}
