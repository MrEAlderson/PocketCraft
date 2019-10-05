package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import lombok.Getter;
import de.marcely.pocketcraft.bedrock.BedrockConfig;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public abstract class PCPacket {
	
	// TODO: Add this as a method and remove the variable
	@Getter private final PacketType type;
	public byte compressionLevel = BedrockConfig.COMPRESSION_LEVEL;
	
	public PCPacket(PacketType type){
		this.type = type;
	}
	
	public abstract void encode(EByteArrayWriter writer) throws Exception;
	
	public abstract void decode(EByteArrayReader reader) throws Exception;
}