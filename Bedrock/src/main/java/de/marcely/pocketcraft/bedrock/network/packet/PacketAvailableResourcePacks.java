package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.component.BResourcePack;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketAvailableResourcePacks extends PCPacket {

	public boolean mustAccept = false, hasScripts = false;
	public BResourcePack[] resourcePacks, behaviourPacks;
	
	public PacketAvailableResourcePacks(){
		super(PacketType.AvailableResourcePacks);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeBoolean(mustAccept);
		writer.writeBoolean(this.hasScripts);
		
		{
			writer.writeLShort(behaviourPacks.length);
			
			for(BResourcePack pack:behaviourPacks){
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
			
			for(BResourcePack pack:resourcePacks){
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
