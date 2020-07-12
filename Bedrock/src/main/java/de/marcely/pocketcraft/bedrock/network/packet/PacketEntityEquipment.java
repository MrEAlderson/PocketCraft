package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.inventory.item.Item;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketEntityEquipment extends PCPacket {

	public long entityRuntimeId;
	public Item item;
	public byte inventorySlot, hotbarSlot, windowId;
	
	public PacketEntityEquipment(){
		super(PacketType.EntityEquipment);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarLong(this.entityRuntimeId);
		item.write(writer);
		writer.writeSignedByte(this.inventorySlot);
		writer.writeSignedByte(this.hotbarSlot);
		writer.writeSignedByte(this.windowId);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.entityRuntimeId = reader.readUnsignedVarLong();
		this.item = Item.read(reader);
		this.inventorySlot = reader.readSignedByte();
		this.hotbarSlot = reader.readSignedByte();
		this.windowId = reader.readSignedByte();
	}
}
