package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.inventory.item.BItem;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketInventorySetItem extends PCPacket {
	
	public long inventoryId, slot;
	public BItem item;
	
	public PacketInventorySetItem(){
		super(PacketType.InventorySetItem);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarInt(this.inventoryId);
		writer.writeUnsignedVarInt(this.slot);
		this.item.write(writer);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
