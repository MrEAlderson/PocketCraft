package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketRespawn extends PCPacket {
	
	public float posX, posY, posZ;
	
	public PacketRespawn(){
		super(PacketType.Respawn);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeVector(posX, posY, posZ);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
