package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayCustomPayload extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int id;
	public byte[] payload;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.id);
		stream.writeByteArray(this.payload);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.id = stream.readVarInt();
		this.payload = stream.readByteArray();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}