package de.marcely.pocketcraft.java.network.packet;

import de.marcely.pocketcraft.java.network.sequence.SequenceType;
import de.marcely.pocketcraft.java.util.EByteArrayReader;
import de.marcely.pocketcraft.java.util.EByteArrayWriter;

public abstract class Packet {
	
	public static final byte CLIENT = 0;
	public static final byte SERVER = 1;
	
	
	public abstract SequenceType getSequence();
	
	public abstract byte getSource();
	
	public abstract void write(EByteArrayWriter stream) throws Exception;
	
	public abstract void read(EByteArrayReader stream) throws Exception;
	
	public abstract PacketProperties getProperties();
}