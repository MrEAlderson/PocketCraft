package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.component.GameRules;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;

public class PacketOutGameRules extends PCPacket {
	
	public GameRules gameRules;
	
	public PacketOutGameRules(){
		super(PacketType.OutGameRules);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		this.gameRules.write(writer);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		
	}
}
