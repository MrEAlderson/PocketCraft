package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.world.entity.EntityMetadata;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutEntityData extends PCPacket {
	
	public long entityRuntimeID;
	public EntityMetadata metadata;
	
	public PacketOutEntityData(){
		super(PacketType.OutEntityData);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarLong(this.entityRuntimeID);
		writer.writeMetadata(this.metadata);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
