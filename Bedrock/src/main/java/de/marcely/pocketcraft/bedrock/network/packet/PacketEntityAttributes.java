package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.world.entity.EntityAttribute;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketEntityAttributes extends PCPacket {
	
	public long entityRuntimeID;
	public EntityAttribute[] attributes;
	
	public PacketEntityAttributes(){
		super(PacketType.EntityAttributes);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarLong(entityRuntimeID);
		
		// why a different scheme tho
		writer.writeUnsignedVarInt(attributes.length);
		
		for(EntityAttribute attr:attributes){
			writer.writeLFloat(attr.type.minValue);
			writer.writeLFloat(attr.type.maxValue);
			writer.writeLFloat(attr.value);
			writer.writeLFloat(attr.type.defaultValue);
			writer.writeString(attr.type.name);
		}
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
