package de.marcely.pocketcraft.pocket.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.GameMode;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutGameMode extends PCPacket {
	
	public GameMode mode;
	
	public PacketOutGameMode(){
		super(PacketType.OutGameMode);
	}
	
	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarInt(mode.getId());
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
