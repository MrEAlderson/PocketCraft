package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlaySpawnObject extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int entityId;
	public short type;
	public float x;
	public float y;
	public float z;
	public float yaw;
	public float pitch;
	public int data;
	public float veloX;
	public float veloY;
	public float veloZ;
	
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.entityId);
		stream.writeUnsignedByte(this.type);
		stream.writeInt((int) (this.x * 32F));
		stream.writeInt((int) (this.y * 32F));
		stream.writeInt((int) (this.z * 32F));
		stream.writeByte(((byte)(int)(this.yaw * 256.0F / 360.0F)));
		stream.writeByte(((byte)(int)(this.pitch * 256.0F / 360.0F)));
		stream.writeInt(this.data);
		
		if(this.data > 0){
			stream.writeShort((short) (this.veloX * 8000F));
			stream.writeShort((short) (this.veloY * 8000F));
			stream.writeShort((short) (this.veloZ * 8000F));
		}
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.entityId = stream.readVarInt();
		this.type = stream.readUnsignedByte();
		this.x = stream.readInt() / 32F;
		this.y = stream.readInt() / 32F;
		this.z = stream.readInt() / 32F;
		this.yaw = stream.readByte() / 256F * 360F;
		this.pitch = stream.readByte() / 256F * 360F;
		this.data = stream.readInt();
		
		if(data > 0){
			this.veloX = stream.readShort() / 8000F;
			this.veloY = stream.readShort() / 8000F;
			this.veloZ = stream.readShort() / 8000F;
		}
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}