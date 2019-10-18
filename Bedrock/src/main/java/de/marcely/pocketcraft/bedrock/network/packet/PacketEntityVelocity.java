package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketEntityVelocity extends PCPacket {

	public long entityRuntimeId;
	public float veloX, veloY, veloZ;
	
	public PacketEntityVelocity(){
		super(PacketType.EntityVelocity);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarLong(this.entityRuntimeId);
		writer.writeVector(this.veloX, this.veloY, this.veloZ);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
