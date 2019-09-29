package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutChangeServer extends PCPacket {
	
	public String ip;
	public int port;
	
	public PacketOutChangeServer(){
		super(PacketType.OutChangeServer);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeString(this.ip);
		writer.writeLShort(this.port);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
