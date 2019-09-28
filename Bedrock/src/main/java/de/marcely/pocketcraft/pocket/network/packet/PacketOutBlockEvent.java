package de.marcely.pocketcraft.pocket.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutBlockEvent extends PCPacket {
	
	public int posX, posY, posZ;
	public int case1, case2;
	
	public PacketOutBlockEvent(){
		super(PacketType.OutBlockEvent);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarInt(this.posX);
		writer.writeUnsignedVarInt(this.posY);
		writer.writeSignedVarInt(this.posZ);
		writer.writeSignedVarInt(this.case1);
		writer.writeSignedVarInt(this.case2);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
