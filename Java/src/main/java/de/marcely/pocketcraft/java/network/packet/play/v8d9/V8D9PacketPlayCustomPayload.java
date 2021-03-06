package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayCustomPayload extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public String id;
	public byte[] payload;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeString(this.id, 20);
		stream.write(this.payload);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.id = stream.readString(20);
		this.payload = stream.read(stream.readableBytes());
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}