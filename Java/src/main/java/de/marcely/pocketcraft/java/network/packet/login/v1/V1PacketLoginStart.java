package de.marcely.pocketcraft.java.network.packet.login.v1;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.util.EByteArrayReader;
import de.marcely.pocketcraft.java.util.EByteArrayWriter;

public class V1PacketLoginStart extends Packet {

	private static final PacketProperties PROPERTIES = new PacketProperties();
	
	@Override
	public void write(EByteArrayWriter stream) throws Exception {
		
	}

	@Override
	public void read(EByteArrayReader stream) throws Exception {
		
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}