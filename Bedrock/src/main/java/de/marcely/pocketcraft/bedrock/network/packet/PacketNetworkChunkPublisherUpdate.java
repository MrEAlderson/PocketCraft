package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;

public class PacketNetworkChunkPublisherUpdate extends PCPacket {
	
	public int x, y, z;
	public int radius;
	
	public PacketNetworkChunkPublisherUpdate(){
		super(PacketType.NetworkChunkPublisherUpdate);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeBlockPosition(this.x, this.y, this.z);
		writer.writeUnsignedVarInt(this.radius);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		
	}
}
