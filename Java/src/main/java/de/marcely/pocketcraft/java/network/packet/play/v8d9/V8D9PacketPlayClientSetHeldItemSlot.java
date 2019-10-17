package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayClientSetHeldItemSlot extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int slot;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeShort(this.slot);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.slot = stream.readShort();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}