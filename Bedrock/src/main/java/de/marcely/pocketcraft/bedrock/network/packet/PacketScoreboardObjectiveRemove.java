package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;

public class PacketScoreboardObjectiveRemove extends PCPacket {

	public String name;
	
	public PacketScoreboardObjectiveRemove(){
		super(PacketType.ScoreboardObjectiveRemove);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeString(this.name);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
