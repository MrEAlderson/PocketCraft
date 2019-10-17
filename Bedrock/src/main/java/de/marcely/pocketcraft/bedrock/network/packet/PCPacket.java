package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import lombok.Getter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public abstract class PCPacket {
	
	// TODO: Add this as a method and remove the variable
	@Getter private final PacketType type;
	
	public PCPacket(PacketType type){
		this.type = type;
	}
	
	public abstract void encode(EByteArrayWriter writer) throws Exception;
	
	public abstract void decode(EByteArrayReader reader) throws Exception;
	
	@SuppressWarnings("deprecation")
	public PacketProperties getProperties(){
		return this.type.getProperties();
	}
}