package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketSetLocalPlayerInitialized extends PCPacket {
	
	public long entityRuntimeID;
	
	public PacketSetLocalPlayerInitialized(boolean in){
		super(in ? PacketType.InSetLocalPlayerInitialized : PacketType.OutSetLocalPlayerInitialized);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarLong(this.entityRuntimeID);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.entityRuntimeID = reader.readUnsignedVarLong();
	}
}
