package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayTeleport extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	// if one of these flags is set then the given value of the type is relative and not absolute
	public static final byte FLAG_REL_X = 0x01;
	public static final byte FLAG_REL_Y = 0x02;
	public static final byte FLAG_REL_Z = 0x04;
	public static final byte FLAG_REL_Y_ROT = 0x08;
	public static final byte FLAG_REL_X_ROT = 0x10;
	
	public double x;
	public double y;
	public double z;
	public float yaw;
	public float pitch;
	public byte flags;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeDouble(this.x);
		stream.writeDouble(this.y);
		stream.writeDouble(this.z);
		stream.writeFloat(this.yaw);
		stream.writeFloat(this.pitch);
		stream.writeByte(this.flags);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.x = stream.readDouble();
		this.y = stream.readDouble();
		this.z = stream.readDouble();
		this.yaw = stream.readFloat();
		this.pitch = stream.readFloat();
		this.flags = stream.readByte();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
