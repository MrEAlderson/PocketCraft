package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayClientChatMessage extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public String message;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeString(this.message, 256);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.message = stream.readString(256);
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
