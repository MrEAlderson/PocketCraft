package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketChunkRadiusChangeRequest extends PCPacket {
	
	public int radius;
	
	public PacketChunkRadiusChangeRequest(){
		super(PacketType.ChunkRadiusChangeRequest);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception { }

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.radius = reader.readSignedVarInt();
	}
}
