package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import java.util.UUID;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlaySpectatePlayer extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public UUID targetEntityUUID;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeUUID(this.targetEntityUUID);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.targetEntityUUID = stream.readUUID();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
