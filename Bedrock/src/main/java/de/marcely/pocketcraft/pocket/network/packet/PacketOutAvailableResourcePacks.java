package de.marcely.pocketcraft.pocket.network.packet;

import de.marcely.pocketcraft.bedrock.component.ResourcePack;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutAvailableResourcePacks extends PCPacket {

	public boolean mustAccept = false, hasScripts = false;
	public ResourcePack[] resourcePacks, behaviourPacks;
	
	public PacketOutAvailableResourcePacks(){
		super(PacketType.OutAvailableResourcePacks);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeBoolean(mustAccept);
		writer.writeBoolean(this.hasScripts);
		
		{
			writer.writeLShort(behaviourPacks.length);
			
			for(ResourcePack pack:behaviourPacks){
				writer.writeString(pack.id);
				writer.writeString(pack.version);
				writer.writeLLong(pack.data.length);
				writer.writeByteArray(new byte[0]); // TODO: encryption key
				writer.writeString(""); // TODO: subpack name
				writer.writeString(""); // TODO: content identity
				writer.writeBoolean(false); // TODO: ?
			}
		}
		
		{
			writer.writeLShort(resourcePacks.length);
			
			for(ResourcePack pack:resourcePacks){
				writer.writeString(pack.id);
				writer.writeString(pack.version);
				writer.writeLLong(pack.data.length);
				writer.writeByteArray(new byte[0]); // TODO: encryption key
				writer.writeString(""); // TODO: subpack name
				writer.writeString(""); // TODO: content identity
				writer.writeBoolean(false); // TODO: ?
			}
		}
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
