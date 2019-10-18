package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlaySpawnGlobalEntity extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public static final byte TYPE_THUNDERBOLT = 1;
	
	public int entityId;
	public byte type;
	public float x;
	public float y;
	public float z;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.entityId);
		stream.writeByte(this.type);
		stream.writeInt((int) (this.x * 32F));
		stream.writeInt((int) (this.y * 32F));
		stream.writeInt((int) (this.z * 32F));
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.entityId = stream.readVarInt();
		this.type = stream.readByte();
		this.x = stream.readInt() / 32F;
		this.y = stream.readInt() / 32F;
		this.z = stream.readInt() / 32F;
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
