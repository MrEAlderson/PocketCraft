package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityAttribute;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityLink;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityMetadata;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketSpawnEntity extends PCPacket {
	
	public long entityUniqueId, entityRuntimeId;
	public EntityType type;
	public float x, y, z, yaw, pitch, headYaw;
	public float veloX, veloY, veloZ;
	public EntityMetadata metadata;
	public EntityAttribute[] attributes;
	public final EntityLink[] links = new EntityLink[0];
	
	public PacketSpawnEntity(){
		super(PacketType.SpawnEntity);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarLong(this.entityUniqueId);
		writer.writeUnsignedVarLong(this.entityRuntimeId);
		writer.writeString(this.type.getId());
		writer.writeVector(this.x, this.y, this.z);
		writer.writeVector(this.veloX, this.veloY, this.veloZ);
		writer.writeLFloat(this.pitch);
		writer.writeLFloat(this.yaw);
		writer.writeLFloat(this.headYaw);
		writer.writeAttributes(this.attributes);
		writer.writeMetadata(this.metadata);
		
		writer.writeUnsignedVarInt(this.links.length);
        
		for(EntityLink link:this.links)
			link.write(writer);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
