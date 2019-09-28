package de.marcely.pocketcraft.pocket.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.Difficulty;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutGameDifficulty extends PCPacket {
	
	public Difficulty difficulty;
	
	public PacketOutGameDifficulty(){
		super(PacketType.OutGameDifficulty);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarInt(difficulty.getId());
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
