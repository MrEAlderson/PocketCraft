package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.inventory.Item;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketEntityEquipment extends PCPacket {

	public long entityRuntimeID;
	public Item item;
	public byte inventorySlot, hotbarSlot, windowID;
	
	public PacketEntityEquipment(boolean in){
		super(in ? PacketType.InEntityEquipment : PacketType.OutEntityEquipment);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarLong(this.entityRuntimeID);
		item.write(writer);
		writer.writeSignedByte(this.inventorySlot);
		writer.writeSignedByte(this.hotbarSlot);
		writer.writeSignedByte(this.windowID);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.entityRuntimeID = reader.readUnsignedVarLong();
		this.item = Item.read(reader);
		this.inventorySlot = reader.readSignedByte();
		this.hotbarSlot = reader.readSignedByte();
		this.windowID = reader.readSignedByte();
	}
}
