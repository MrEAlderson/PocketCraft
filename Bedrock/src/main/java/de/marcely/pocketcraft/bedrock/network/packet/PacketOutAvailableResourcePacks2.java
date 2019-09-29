package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.component.ResourcePack;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutAvailableResourcePacks2 extends PCPacket {
	
	public boolean mustAccept = false;
	public ResourcePack[] resourcePacks, behaviourPacks;
	
	public PacketOutAvailableResourcePacks2(){
		super(PacketType.OutAvailableResourcePacks2);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeBoolean(mustAccept);
		
		writer.writeUnsignedVarInt(behaviourPacks.length);
		for(ResourcePack pack:behaviourPacks){
			writer.writeString(pack.id);
			writer.writeString(pack.version);
		}
		
		writer.writeUnsignedVarInt(resourcePacks.length);
		for(ResourcePack pack:resourcePacks){
			writer.writeString(pack.id);
			writer.writeString(pack.version);
		}
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
