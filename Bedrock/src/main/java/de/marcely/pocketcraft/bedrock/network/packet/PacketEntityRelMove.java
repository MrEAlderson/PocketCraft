package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;

public class PacketEntityRelMove extends PCPacket {

	public static final byte FLAG_HAS_X = 0x01;
    public static final byte FLAG_HAS_Y = 0x02;
    public static final byte FLAG_HAS_Z = 0x04;
    public static final byte FLAG_HAS_HEAD_YAW = 0x08;
    public static final byte FLAG_HAS_YAW = 0x10;
    public static final byte FLAG_HAS_PITCH = 0x20;
	
    public long entityRuntimeId;
    public byte flags;
    public float relX;
    public float relY;
    public float relZ;
    public float yaw;
    public float headYaw;
    public float pitch;
    
	public PacketEntityRelMove(){
		super(PacketType.EntityRelMove);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarLong(this.entityRuntimeId);
		writer.writeSignedByte(this.flags);
		writeCoordinate(this.relX, FLAG_HAS_X, writer);
		writeCoordinate(this.relY, FLAG_HAS_Y, writer);
		writeCoordinate(this.relZ, FLAG_HAS_Z, writer);
		writeRotation(this.pitch, FLAG_HAS_PITCH, writer);
		writeRotation(this.yaw, FLAG_HAS_YAW, writer);
		writeRotation(this.headYaw, FLAG_HAS_HEAD_YAW, writer);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
	
	private void writeCoordinate(float val, byte flag, EByteArrayWriter writer) throws Exception {
		if((this.flags & flag) > 0)
			writer.writeSignedVarInt((int) (val * 16000F));
	}
	
	private void writeRotation(float val, byte flag, EByteArrayWriter writer) throws Exception {
		if((this.flags & flag) > 0)
			writer.writeSignedByte((byte) (val * 256F / 360F));
	}
}
