package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.world.entity.EntityMetadata;
import de.marcely.pocketcraft.bedrock.component.inventory.Item;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutSpawnEntityItem extends PCPacket {

	public long entityUID, entityRuntimeID;
	public Item item;
	public float x, y, z;
	public float veloX, veloY, veloZ;
	public EntityMetadata metadata;
	public boolean bool1;
	
	public PacketOutSpawnEntityItem(){
		super(PacketType.OutSpawnEntityItem);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarLong(this.entityUID);
		writer.writeUnsignedVarLong(this.entityRuntimeID);
		this.item.write(writer);
		writer.writeVector(this.x, this.y, this.z);
		writer.writeVector(this.veloX, this.veloY, this.veloZ);
		writer.writeMetadata(this.metadata);
		writer.writeBoolean(this.bool1);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}