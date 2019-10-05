package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketChangeCheatSettings extends PCPacket {

	public static final int TYPE_COMMANDS_ENABLE = 1;
	public static final int TYPE_COMMANDS_DISABLE = 2;
	
	public int type;
	
	public PacketChangeCheatSettings(){
		super(PacketType.ChangeCheatSettings);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception { }

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.type = reader.readLShort();
	}
}
