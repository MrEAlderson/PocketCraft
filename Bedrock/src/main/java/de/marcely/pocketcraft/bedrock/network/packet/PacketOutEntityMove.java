package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutEntityMove extends PCPacket {
	
	public long entityRuntimeID;
	public float x, y, z, yaw, pitch, headYaw;
	public boolean onGround, isTeleport;
	
	public PacketOutEntityMove(){
		super(PacketType.OutEntityMove);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarLong(entityRuntimeID);
		writer.writeVector(x, y, z);
		writer.writeSignedByte((byte) ((double) this.pitch/(360D/256D)));
		writer.writeSignedByte((byte) ((double) this.headYaw/(360D/256D)));
		writer.writeSignedByte((byte) ((double) this.yaw/(360D/256D)));
		writer.writeBoolean(onGround);
		writer.writeBoolean(isTeleport);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}