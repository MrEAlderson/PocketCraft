package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;

public class PacketOutNetworkChunkPublisherUpdate extends PCPacket {
	
	public int x, y, z;
	public int radius;
	
	public PacketOutNetworkChunkPublisherUpdate(){
		super(PacketType.OutNetworkChunkPublisherUpdate);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeBlockPosition(this.x, this.y, this.z);
		writer.writeUnsignedVarInt(this.radius << 4);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		
	}
}
