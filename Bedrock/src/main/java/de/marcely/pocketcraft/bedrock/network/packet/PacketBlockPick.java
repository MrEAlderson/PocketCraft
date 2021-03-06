package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketBlockPick extends PCPacket {
	
	public int posX, posY, posZ;
	public byte selectedSlot;
	
	public PacketBlockPick(){
		super(PacketType.BlockPick);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception { }

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.posX = reader.readSignedVarInt();
		this.posY = reader.readSignedVarInt();
		this.posZ = reader.readSignedVarInt();
		this.selectedSlot = reader.readSignedByte();
	}
}
