package de.marcely.pocketcraft.java.network.sequence.type;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.sequence.Sequence;
import de.marcely.pocketcraft.java.network.sequence.SequenceHolder;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;

public class DeadSequence extends Sequence {

	public DeadSequence(SequenceHolder holder){
		super(holder);
	}

	@Override
	public SequenceType getType(){
		return SequenceType.DEAD;
	}

	@Override
	public void run(Sequence oldSequence){ }
	
	@Override
	public boolean onSend(Packet packet){
		return false;
	}
	
	@Override
	public boolean onReceive(Packet packet){
		return false;
	}
}
