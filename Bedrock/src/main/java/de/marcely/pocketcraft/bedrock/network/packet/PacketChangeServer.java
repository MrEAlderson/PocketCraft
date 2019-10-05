package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketChangeServer extends PCPacket {
	
	public String ip;
	public int port;
	
	public PacketChangeServer(){
		super(PacketType.ChangeServer);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeString(this.ip);
		writer.writeLShort(this.port);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
