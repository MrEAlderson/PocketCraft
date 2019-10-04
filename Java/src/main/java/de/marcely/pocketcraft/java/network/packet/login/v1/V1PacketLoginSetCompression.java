package de.marcely.pocketcraft.java.network.packet.login.v1;

import de.marcely.pocketcraft.java.network.packet.LoginPacket;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V1PacketLoginSetCompression extends LoginPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int threshold;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.threshold);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.threshold = stream.readVarInt();
	}
	
	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
