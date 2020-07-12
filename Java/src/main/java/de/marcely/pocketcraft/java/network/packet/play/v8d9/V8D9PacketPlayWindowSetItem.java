package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.component.v8.item.V8Item;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayWindowSetItem extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	/* 0             = player inventory
	 * -1            = cursor
	 * anything else = current open window. should match with its' id tho
	 */
	public byte windowId;
	public short slot;
	public V8Item item;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeByte(this.windowId);
		stream.writeShort(this.slot);
		stream.writeV8Item(this.item);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.windowId = stream.readByte();
		this.slot = stream.readShort();
		this.item = stream.readV8Item();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
