package de.marcely.pocketcraft.java.network.packet.login.v1;

import de.marcely.pocketcraft.java.network.packet.LoginPacket;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.util.EByteArrayReader;
import de.marcely.pocketcraft.java.util.EByteArrayWriter;

public class V1PacketLoginStart extends LoginPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public String username;

	@Override
	public byte getSource(){
		return CLIENT;
	}
	
	@Override
	public void write(EByteArrayWriter stream) throws Exception {
		stream.writeString(this.username);
	}

	@Override
	public void read(EByteArrayReader stream) throws Exception {
		this.username = stream.readString();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}