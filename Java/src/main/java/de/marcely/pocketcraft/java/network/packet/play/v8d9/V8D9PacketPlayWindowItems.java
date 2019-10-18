package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.component.Item;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayWindowItems extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	/* 0             = player inventory
	 * -1            = cursor
	 * anything else = current open window. should match with its' id tho
	 */
	public byte windowId;
	public Item[] items;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeByte(this.windowId);
		stream.writeShort(this.items.length);
		
		for(int i=0; i<this.items.length; i++)
			stream.writeItem(this.items[i]);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.windowId = stream.readByte();
		this.items = new Item[stream.readShort()];
		
		for(int i=0; i<this.items.length; i++)
			this.items[i] = stream.readItem();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
