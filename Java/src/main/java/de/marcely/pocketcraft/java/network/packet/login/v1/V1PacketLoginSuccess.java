package de.marcely.pocketcraft.java.network.packet.login.v1;

import java.util.UUID;

import de.marcely.pocketcraft.java.network.packet.LoginPacket;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.util.EByteArrayReader;
import de.marcely.pocketcraft.java.util.EByteArrayWriter;

public class V1PacketLoginSuccess extends LoginPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public UUID id;
	public String username;

	@Override
	public byte getSource(){
		return SERVER;
	}
	
	@Override
	public void write(EByteArrayWriter stream) throws Exception {
		stream.writeString(this.id.toString());
		stream.writeString(this.username);
	}

	@Override
	public void read(EByteArrayReader stream) throws Exception {
		this.id = UUID.fromString(stream.readString(36));
		this.username = stream.readString(16);
	}
	
	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
