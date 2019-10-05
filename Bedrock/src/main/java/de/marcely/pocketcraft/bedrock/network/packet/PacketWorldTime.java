package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketWorldTime extends PCPacket {
	
	public int time;
	
	public PacketWorldTime(){
		super(PacketType.WorldTime);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarInt(this.time);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
