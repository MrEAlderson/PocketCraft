package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;

public class PacketInClientCacheStatusPacket extends PCPacket {
	
	public boolean isEnabled;
	
	public PacketInClientCacheStatusPacket(){
		super(PacketType.InClientCacheStatus);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeBoolean(this.isEnabled);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.isEnabled = reader.readBoolean();
	}
}
