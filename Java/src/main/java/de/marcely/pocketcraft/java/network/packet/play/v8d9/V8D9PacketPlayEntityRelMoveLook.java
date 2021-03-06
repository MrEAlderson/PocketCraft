package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayEntityRelMoveLook extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int entityId;
	public float relX;
	public float relY;
	public float relZ;
	public float yaw;
	public float pitch;
	public boolean isOnGround;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.entityId);
		stream.writeByte((byte) (this.relX * 32F));
		stream.writeByte((byte) (this.relY * 32F));
		stream.writeByte((byte) (this.relZ * 32F));
		stream.writeByte(((byte) (this.yaw * 256.0F / 360.0F)));
		stream.writeByte(((byte) (this.pitch * 256.0F / 360.0F)));
		stream.writeBoolean(this.isOnGround);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.entityId = stream.readVarInt();
		this.relX = stream.readByte() / 32F;
		this.relY = stream.readByte() / 32F;
		this.relZ = stream.readByte() / 32F;
		this.yaw = stream.readByte() / 256F * 360F;
		this.pitch = stream.readByte() / 256F * 360F;
		this.isOnGround = stream.readBoolean();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
