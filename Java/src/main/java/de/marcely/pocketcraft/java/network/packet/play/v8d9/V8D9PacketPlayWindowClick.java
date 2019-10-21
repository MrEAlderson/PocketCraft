package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.component.Item;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayWindowClick extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	/* 0 = left
	 * 1 = right */
	public static final byte MODE_MOUSE_CLICK = 0;
	/* 0 = left
	 * 1 = right */
	public static final byte MODE_SHIFT_MOUSE_CLICK = 1;
	/* 0 = key 1
	 * 1 = key 2
	 * 2 = key 3
	 * ... */
	public static final byte MODE_NUMBER_KEY = 2;
	// only button = 0
	public static final byte MODE_MIDDLE_CLICK = 3;
	/* 0 = drop single
	 * 1 = drop stack
	 * 0 (slot = -999) = left click outside without anything in cursor
	 * 0 (slot = -999) = right click outside without anything in cursor */
	public static final byte MODE_DROP = 4;
	/* 0 (slot = -999) = start left/middle mouse drag
	 * 4 (slot = -999) = start right mouse drag
	 * 1 = add slot for left/middle drag
	 * 5 = add slot for right drag
	 * 2 (slot = -999) end left/middle drag
	 * 6 (slot = -999) end right drag */
	public static final byte MODE_PAINT = 5;
	// only button = 0
	public static final byte MODE_DOUBLE_CLICK = 6;
	
	public byte windowId;
	public byte mode;
	public byte button;
	public short slot;
	public short transactionId; // server will send a window click response back with the id. starts at 1
	public Item clickedItem; // -1 = drop
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeByte(this.windowId);
		stream.writeShort(this.slot);
		stream.writeByte(this.button);
		stream.writeShort(this.transactionId);
		stream.writeByte(this.mode);
		stream.writeItem(this.clickedItem);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.windowId = stream.readByte();
		this.slot = stream.readShort();
		this.button = stream.readByte();
		this.transactionId = stream.readShort();
		this.mode = stream.readByte();
		this.clickedItem = stream.readItem();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
