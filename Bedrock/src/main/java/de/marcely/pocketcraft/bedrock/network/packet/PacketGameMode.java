package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.GameMode;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketGameMode extends PCPacket {
	
	public GameMode mode;
	
	public PacketGameMode(){
		super(PacketType.GameMode);
	}
	
	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarInt(mode.getId());
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
