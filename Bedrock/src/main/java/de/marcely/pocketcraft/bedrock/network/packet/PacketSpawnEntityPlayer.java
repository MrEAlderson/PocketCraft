package de.marcely.pocketcraft.bedrock.network.packet;

import java.util.UUID;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.inventory.Item;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityLink;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityMetadata;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketSpawnEntityPlayer extends PCPacket {

	public UUID uuid;
	public String name;
	public long entityUniqueId, entityRuntimeId;
	public String platformChatId = "";
	public float x, y, z, yaw, headYaw, pitch;
	public float veloX, veloY, veloZ;
	public Item itemOnHand;
	public EntityMetadata metadata;
	
	public long[] adventureSettings = new long[5];
	public EntityLink[] links;
	public String deviceId = "";
	public int buildPlatform;
	
	public PacketSpawnEntityPlayer(){
		super(PacketType.SpawnEntityPlayer);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUUID(this.uuid);
		writer.writeString(this.name);
		writer.writeSignedVarLong(this.entityUniqueId);
		writer.writeUnsignedVarLong(this.entityRuntimeId);
		writer.writeString(this.platformChatId);
		writer.writeVector(this.x, this.y, this.z);
		writer.writeVector(this.veloX, this.veloY, this.veloZ);
		writer.writeLFloat(this.pitch);
		writer.writeLFloat(this.headYaw);
		writer.writeLFloat(this.yaw);
		this.itemOnHand.write(writer);
		writer.writeMetadata(this.metadata);
		
		for(long l:this.adventureSettings)
			writer.writeUnsignedVarInt(l);
		
		writer.writeLLong(this.entityUniqueId);
		
		{
			writer.writeUnsignedVarInt(this.links.length);
			
			for(EntityLink link:this.links)
				link.write(writer);
		}
		
		writer.writeString(this.deviceId);
		writer.writeLInt(this.buildPlatform);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
