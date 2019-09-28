package de.marcely.pocketcraft.pocket.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutWorldTime extends PCPacket {
	
	public int time;
	
	public PacketOutWorldTime(){
		super(PacketType.OutWorldTime);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedVarInt(this.time);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
