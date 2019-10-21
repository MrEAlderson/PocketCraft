package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayWindowClose extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public byte windowId;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeByte(this.windowId);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.windowId = stream.readByte();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
