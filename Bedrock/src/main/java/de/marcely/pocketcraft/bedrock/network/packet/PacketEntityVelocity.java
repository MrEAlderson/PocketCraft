package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketEntityVelocity extends PCPacket {

	public long entityRuntimeID;
	public float veloX, veloY, veloZ;
	
	public PacketEntityVelocity(){
		super(PacketType.EntityVelocity);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarLong(entityRuntimeID);
		writer.writeVector(veloX, veloY, veloZ);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
