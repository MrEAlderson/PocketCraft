package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.inventory.item.Item;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketEntityArmor extends PCPacket {
	
	public static final byte SLOT_HELMET = 0;
	public static final byte SLOT_CHESTPLATE = 1;
	public static final byte SLOT_LEGGINGS = 2;
	public static final byte SLOT_BOOTS = 3;
	
	public long entityId;
	public Item[] items = new Item[4];
	
	public PacketEntityArmor(){
		super(PacketType.EntityArmor);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarLong(this.entityId);
		
		for(int i=0; i<this.items.length; i++)
			this.items[i].write(writer);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
