package de.marcely.pocketcraft.java.network.packet;

import de.marcely.pocketcraft.java.util.EByteArrayReader;
import de.marcely.pocketcraft.java.util.EByteArrayWriter;

public abstract class Packet {
	
	public abstract void write(EByteArrayWriter stream) throws Exception;
	
	public abstract void read(EByteArrayReader stream) throws Exception;
	
	public abstract PacketProperties getProperties();
	
	
	
	// TODO Add pooling
	public static <T extends Packet>Packet newInstance(Class<T> clazz){
		try{
			return clazz.newInstance();
		}catch(InstantiationException | IllegalAccessException e){
			e.printStackTrace();
		}
		
		throw new IllegalStateException("Failed to create a new instance of the given class");
	}
}