package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketBlockEvent extends PCPacket {
	
	public int posX, posY, posZ;
	public int case1, case2;
	
	public PacketBlockEvent(){
		super(PacketType.BlockEvent);
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
