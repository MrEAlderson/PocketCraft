package de.marcely.pocketcraft.java.network.packet;

import de.marcely.pocketcraft.java.util.EByteArrayReader;
import de.marcely.pocketcraft.java.util.EByteArrayWriter;

public abstract class Packet {
	
	public abstract void write(EByteArrayWriter stream) throws Exception;
	
	public abstract void read(EByteArrayReader stream) throws Exception;
}