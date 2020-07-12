package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import java.util.UUID;

import de.marcely.pocketcraft.java.component.v8.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlaySpawnPlayer extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int entityId;
	public UUID uuid;
	public float x;
	public float y;
	public float z;
	public float yaw;
	public float pitch;
	public short holdingItem; // 0 = none
	public V8EntityMetadata metadata;
	
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.entityId);
		stream.writeUUID(this.uuid);
		stream.writeInt((int) (this.x * 32F));
		stream.writeInt((int) (this.y * 32F));
		stream.writeInt((int) (this.z * 32F));
		stream.writeByte(((byte)(int)(this.yaw * 256.0F / 360.0F)));
		stream.writeByte(((byte)(int)(this.pitch * 256.0F / 360.0F)));
		stream.writeShort(this.holdingItem);
		stream.writeV8EntityMetadata(this.metadata);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.entityId = stream.readVarInt();
		this.uuid = stream.readUUID();
		this.x = stream.readInt() / 32F;
		this.y = stream.readInt() / 32F;
		this.z = stream.readInt() / 32F;
		this.yaw = stream.readByte() / 256F * 360F;
		this.pitch = stream.readByte() / 256F * 360F;
		this.holdingItem = stream.readShort();
		this.metadata = stream.readV8EntityMetadata();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}