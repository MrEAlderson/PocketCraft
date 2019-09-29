package de.marcely.pocketcraft.bedrock.network.packet;

import java.util.UUID;

import de.marcely.pocketcraft.bedrock.entity.EntityLink;
import de.marcely.pocketcraft.bedrock.entity.EntityMetadata;
import de.marcely.pocketcraft.bedrock.inventory.Item;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutSpawnEntityPlayer extends PCPacket {

	public UUID uuid;
	public String name, thirdPartyName = "";
	public int platform = 0;
	public long entityUID, entityRuntimeID;
	public String platformChatID = "";
	public float x, y, z, yaw, headYaw, pitch;
	public float veloX, veloY, veloZ;
	public Item itemOnHand;
	public EntityMetadata metadata;
	
	public long[] uvarint = new long[5];
	
	public long long1;
	
	public EntityLink[] links;
	
	public String devideID;
	
	public PacketOutSpawnEntityPlayer(){
		super(PacketType.OutSpawnEntityPlayer);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUUID(uuid);
		writer.writeString(name);
		writer.writeString(thirdPartyName);
		writer.writeSignedVarInt(platform);
		writer.writeSignedVarLong(entityUID);
		writer.writeUnsignedVarLong(entityRuntimeID);
		writer.writeString(platformChatID);
		writer.writeVector(x, y, z);
		writer.writeVector(veloX, veloY, veloZ);
		writer.writeLFloat(pitch);
		writer.writeLFloat(headYaw);
		writer.writeLFloat(yaw);
		itemOnHand.write(writer);
		writer.writeMetadata(metadata);
		
		for(long l:this.uvarint)
			writer.writeUnsignedVarInt(l);
		
		writer.writeLLong(this.long1);
		
		{
			writer.writeUnsignedVarInt(this.links.length);
			
			for(EntityLink link:this.links)
				link.write(writer);
		}
		
		writer.writeString(this.devideID);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
