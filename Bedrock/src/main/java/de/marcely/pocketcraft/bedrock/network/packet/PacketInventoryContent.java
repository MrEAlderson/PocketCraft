package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.inventory.item.BItem;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketInventoryContent extends PCPacket {

    public int inventoryId;
    public BItem[] items;
	
	public PacketInventoryContent(){
		super(PacketType.InventoryContent);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
        writer.writeUnsignedVarInt(this.inventoryId);
        writer.writeUnsignedVarInt(this.items.length);
        
        for(BItem item:this.items)
        	item.write(writer);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
