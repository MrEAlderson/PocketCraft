package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityMetadata;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketEntityData extends PCPacket {
	
	public long entityRuntimeID;
	public EntityMetadata metadata;
	
	public PacketEntityData(){
		super(PacketType.EntityData);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarLong(this.entityRuntimeID);
		writer.writeMetadata(this.metadata);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
