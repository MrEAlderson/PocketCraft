package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayKeepAlive extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int id;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.id);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.id = stream.readVarInt();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
