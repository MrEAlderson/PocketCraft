package de.marcely.pocketcraft.java.network.packet.login.v1;

import de.marcely.pocketcraft.java.network.packet.LoginPacket;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V1PacketLoginDisconnect extends LoginPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();

	@Override
	public byte getSource(){
		return SERVER;
	}

	@Override
	public void write(EByteBuf stream) throws Exception {
		// TODO
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		// TODO
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
