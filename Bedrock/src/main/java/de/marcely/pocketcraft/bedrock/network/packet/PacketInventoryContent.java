package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.inventory.Item;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketInventoryContent extends PCPacket {

    public int inventoryId;
    public Item[] items;
	
	public PacketInventoryContent(){
		super(PacketType.InventoryContent);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
        writer.writeUnsignedVarInt(this.inventoryId);
        writer.writeUnsignedVarInt(this.items.length);
        
        for(Item item:this.items)
        	item.write(writer);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
