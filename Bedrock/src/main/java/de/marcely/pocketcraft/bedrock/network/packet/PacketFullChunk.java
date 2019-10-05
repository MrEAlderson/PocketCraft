package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketFullChunk extends PCPacket {

	public int posX, posZ;
	public int sectionsAmount;
	public boolean isCachingEnabled;
	public long[] blobIds;
	public byte[] data;
	
	public PacketFullChunk(){
		super(PacketType.FullChunk);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarInt(this.posX);
		writer.writeSignedVarInt(this.posZ);
		writer.writeUnsignedVarInt(this.sectionsAmount);
		writer.writeBoolean(this.isCachingEnabled);
		
		if(this.isCachingEnabled){
			writer.writeUnsignedVarInt(this.blobIds.length);
			
			for(long blobId:this.blobIds)
				writer.writeLLong(blobId);
		}
		
		writer.writeByteArray(data);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
