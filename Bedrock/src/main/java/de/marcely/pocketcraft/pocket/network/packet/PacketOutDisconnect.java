package de.marcely.pocketcraft.pocket.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutDisconnect extends PCPacket {

	public boolean hideScreen;
	public String reason;
	
	public PacketOutDisconnect(){
		super(PacketType.OutDisconnect);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeBoolean(hideScreen);
		
		if(!this.hideScreen)
			writer.writeString(reason);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
}
