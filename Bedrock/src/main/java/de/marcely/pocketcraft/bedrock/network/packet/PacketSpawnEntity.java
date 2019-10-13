package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityAttribute;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityMetadata;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketSpawnEntity extends PCPacket {
	
	public long entityUID, entityRuntimeID;
	public EntityType type;
	public float x, y, z, yaw, pitch, headYaw;
	public float veloX, veloY, veloZ;
	public EntityMetadata metadata;
	public EntityAttribute[] attributes;
	public final Object[][] links = new Object[0][3];
	
	public PacketSpawnEntity(){
		super(PacketType.SpawnEntity);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarLong(entityUID);
		writer.writeUnsignedVarLong(entityRuntimeID);
		writer.writeString(type.getId());
		writer.writeVector(x, y, z);
		writer.writeVector(veloX, veloY, veloZ);
		writer.writeLFloat(yaw);
		writer.writeLFloat(pitch);
		writer.writeLFloat(headYaw);
		writer.writeAttributes(attributes);
		writer.writeMetadata(metadata);
		
		writer.writeUnsignedVarInt(links.length);
        for(Object[] link:links){
        	writer.writeSignedVarLong((long) link[0]);
        	writer.writeSignedVarLong((long) link[1]);
            writer.writeSignedByte((byte) link[2]);
        }
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
