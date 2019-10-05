package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.inventory.Item;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketInventorySetItem extends PCPacket {
	
	public long inventoryID, slot;
	public Item item;
	
	public PacketInventorySetItem(){
		super(PacketType.InventorySetItem);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarInt(inventoryID);
		writer.writeUnsignedVarInt(slot);
		item.write(writer);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}