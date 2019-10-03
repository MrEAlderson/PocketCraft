package de.marcely.pocketcraft.java.network.packet.handshake.v1;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.util.EByteArrayReader;
import de.marcely.pocketcraft.java.util.EByteArrayWriter;

public class V1PacketHandshake extends Packet {
	
	private static final PacketProperties PROPERTIES = new PacketProperties();
	
	public static final byte STATE_STATUS = 1;
	public static final byte STATE_LOGIN = 2;
	
	public int protocolVersion;
	public String serverAddress;
	public int serverPort;
	public byte nextState;
	
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