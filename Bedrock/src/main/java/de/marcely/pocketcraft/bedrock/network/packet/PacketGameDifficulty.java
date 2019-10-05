package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.Difficulty;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketGameDifficulty extends PCPacket {
	
	public Difficulty difficulty;
	
	public PacketGameDifficulty(){
		super(PacketType.GameDifficulty);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarInt(difficulty.getId());
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
