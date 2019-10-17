package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketDestroyEntity extends PCPacket {
	
	public long entityUniqueId;
	
	public PacketDestroyEntity(){
		super(PacketType.DestroyEntity);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarLong(entityUniqueId);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}