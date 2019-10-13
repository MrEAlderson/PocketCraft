package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;

public class PacketScoreboardDisplay extends PCPacket {
	
	public static final String POSITION_SIDEBAR = "sidebar";
	public static final String POSITION_LIST = "list";
	
	public String position;
	public String objectiveName;
	public String displayName;
	public String criteriaName;
	public int sortOrder;
	
	public PacketScoreboardDisplay(){
		super(PacketType.ScoreboardDisplay);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeString(this.displayName);
		writer.writeString(this.objectiveName);
		writer.writeString(this.displayName);
		writer.writeString(this.criteriaName);
		writer.writeSignedVarInt(this.sortOrder);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
