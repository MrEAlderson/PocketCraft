package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutChunkRadiusChange extends PCPacket {

	public int radius;
	
	public PacketOutChunkRadiusChange(){
		super(PacketType.OutChunkRadiusChange);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarInt(this.radius);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
