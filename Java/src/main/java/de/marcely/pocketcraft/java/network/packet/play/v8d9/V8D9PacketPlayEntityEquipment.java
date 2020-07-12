package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.component.v8.item.V8Item;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayEntityEquipment extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public static final byte SLOT_HAND = 0;
	public static final byte SLOT_BOOTS = 1;
	public static final byte SLOT_LEGGINGS = 2;
	public static final byte SLOT_CHESTPLATE = 3;
	public static final byte SLOT_HELMET = 4;
	
	
	public int entityId;
	public byte slot;
	public V8Item item;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.entityId);
		stream.writeShort(this.slot);
		stream.writeV8Item(this.item);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.entityId = stream.readVarInt();
		this.slot = (byte) stream.readShort();
		this.item = stream.readV8Item();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
