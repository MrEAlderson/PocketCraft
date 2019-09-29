package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutRespawn extends PCPacket {
	
	public float posX, posY, posZ;
	
	public PacketOutRespawn(){
		super(PacketType.OutRespawn);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeVector(posX, posY, posZ);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
