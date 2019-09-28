package de.marcely.pocketcraft.pocket.network.packet;

import de.marcely.pocketcraft.bedrock.inventory.Item;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutInventoryContent extends PCPacket {

    public int windowID;
    public Item[] items;
	
	public PacketOutInventoryContent(){
		super(PacketType.OutInventoryContent);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
        writer.writeUnsignedVarInt(windowID);
        writer.writeUnsignedVarInt(items.length);
        
        for(Item item:items)
        	item.write(writer);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
