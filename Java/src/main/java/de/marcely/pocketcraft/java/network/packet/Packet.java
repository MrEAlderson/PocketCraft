package de.marcely.pocketcraft.java.network.packet;

import de.marcely.pocketcraft.java.network.sequence.SequenceType;
import de.marcely.pocketcraft.java.util.EByteBuf;

public abstract class Packet {
	
	public abstract SequenceType getSequence();
	
	public abstract void write(EByteBuf stream) throws Exception;
	
	public abstract void read(EByteBuf stream) throws Exception;
	
	public abstract PacketProperties getProperties();
}