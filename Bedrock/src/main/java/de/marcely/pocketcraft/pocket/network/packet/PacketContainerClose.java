package de.marcely.pocketcraft.pocket.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketContainerClose extends PCPacket {

	public byte windowID;
	
	public PacketContainerClose(boolean in){
		super(in ? PacketType.InContainerClose : PacketType.OutContainerClose);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedByte(windowID);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.windowID = reader.readSignedByte();
	}
}
