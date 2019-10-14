package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlaySpawnExperienceOrb extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int entityId;
	public float x;
	public float y;
	public float z;
	public short amount;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.entityId);
		stream.writeInt((int) (this.x * 32F));
		stream.writeInt((int) (this.y * 32F));
		stream.writeInt((int) (this.z * 32F));
		stream.writeShort(this.amount);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.entityId = stream.readVarInt();
		this.x = stream.readInt() / 32F;
		this.y = stream.readInt() / 32F;
		this.z = stream.readInt() / 32F;
		this.amount = stream.readShort();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
