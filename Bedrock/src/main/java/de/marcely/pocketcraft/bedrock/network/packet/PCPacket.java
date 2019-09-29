package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.BedrockConfig;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public abstract class PCPacket {
	
	public final PacketType type;
	public byte compressionLevel = BedrockConfig.COMPRESSION_LEVEL;
	
	public PCPacket(PacketType type){
		this.type = type;
	}
	
	public abstract void encode(EByteArrayWriter writer) throws Exception;
	
	public abstract void decode(EByteArrayReader reader) throws Exception;
}