package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.component.BGameRules;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;

public class PacketGameRules extends PCPacket {
	
	public BGameRules gameRules;
	
	public PacketGameRules(){
		super(PacketType.GameRules);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		this.gameRules.write(writer);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		
	}
}
