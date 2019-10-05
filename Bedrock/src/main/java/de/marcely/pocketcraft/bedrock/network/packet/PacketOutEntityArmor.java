package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.inventory.Item;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutEntityArmor extends PCPacket {
	
	public static final byte SLOT_HELMET = 0;
	public static final byte SLOT_CHESTPLATE = 1;
	public static final byte SLOT_LEGGINGS = 2;
	public static final byte SLOT_BOOTS = 3;
	
	public long entityID;
	public Item[] items;
	
	public PacketOutEntityArmor(){
		super(PacketType.OutEntityArmor);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarLong(this.entityID);
		
		for(int i=0; i<items.length; i++)
			items[i].write(writer);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
