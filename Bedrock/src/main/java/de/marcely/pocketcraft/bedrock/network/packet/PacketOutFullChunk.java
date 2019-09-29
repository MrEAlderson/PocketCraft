package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutFullChunk extends PCPacket {

	public int posX, posZ;
	public byte[] data;
	
	public PacketOutFullChunk(){
		super(PacketType.OutFullChunk);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarInt(posX);
		writer.writeSignedVarInt(posZ);
		writer.writeByteArray(data);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
