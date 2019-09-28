package de.marcely.pocketcraft.pocket.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketInChangeCheatSettings extends PCPacket {

	public static final int TYPE_COMMANDS_ENABLE = 1;
	public static final int TYPE_COMMANDS_DISABLE = 2;
	
	public int type;
	
	public PacketInChangeCheatSettings(){
		super(PacketType.InChangeCheatSettings);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception { }

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.type = reader.readLShort();
	}
}
