package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.component.BResourcePack;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketAvailableResourcePacks2 extends PCPacket {
	
	public boolean mustAccept = false;
	public BResourcePack[] resourcePacks, behaviourPacks;
	public boolean isExperimental;
	public String gameVersion;
	
	public PacketAvailableResourcePacks2(){
		super(PacketType.AvailableResourcePacks2);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeBoolean(mustAccept);
		
		writer.writeUnsignedVarInt(behaviourPacks.length);
		for(BResourcePack pack:behaviourPacks){
			writer.writeString(pack.id);
			writer.writeString(pack.version);
		}
		
		writer.writeUnsignedVarInt(resourcePacks.length);
		for(BResourcePack pack:resourcePacks){
			writer.writeString(pack.id);
			writer.writeString(pack.version);
		}
		
		writer.writeBoolean(this.isExperimental);
		writer.writeString(this.gameVersion);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
