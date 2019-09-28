package de.marcely.pocketcraft.pocket.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketInChunkRadiusChangeRequest extends PCPacket {
	
	public int radius;
	
	public PacketInChunkRadiusChangeRequest(){
		super(PacketType.InChunkRadiusChangeRequest);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception { }

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.radius = reader.readSignedVarInt();
	}
}
