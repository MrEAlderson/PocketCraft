package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutDestroyEntity extends PCPacket {
	
	public long entityUID;
	
	public PacketOutDestroyEntity(){
		super(PacketType.OutDestroyEntity);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarLong(entityUID);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}