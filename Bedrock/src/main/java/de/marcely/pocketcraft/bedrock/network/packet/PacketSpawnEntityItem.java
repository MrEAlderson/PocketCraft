package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.inventory.item.Item;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityMetadata;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketSpawnEntityItem extends PCPacket {

	public long entityUniqueId, entityRuntimeId;
	public Item item;
	public float x, y, z;
	public float veloX, veloY, veloZ;
	public EntityMetadata metadata;
	public boolean isFromFishing = false;
	
	public PacketSpawnEntityItem(){
		super(PacketType.SpawnEntityItem);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarLong(this.entityUniqueId);
		writer.writeUnsignedVarLong(this.entityRuntimeId);
		this.item.write(writer);
		writer.writeVector(this.x, this.y, this.z);
		writer.writeVector(this.veloX, this.veloY, this.veloZ);
		writer.writeMetadata(this.metadata);
		writer.writeBoolean(this.isFromFishing);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}