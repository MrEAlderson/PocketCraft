package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;

public class PacketCollectItem extends PCPacket {
	
	public long itemRuntimeId;
	public long collectorRuntimeId;
	
	public PacketCollectItem(){
		super(PacketType.CollectItem);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarLong(this.itemRuntimeId);
		writer.writeUnsignedVarLong(this.collectorRuntimeId);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
