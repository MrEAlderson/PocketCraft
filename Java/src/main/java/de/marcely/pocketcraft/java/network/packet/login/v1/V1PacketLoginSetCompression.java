package de.marcely.pocketcraft.java.network.packet.login.v1;

import de.marcely.pocketcraft.java.network.packet.LoginPacket;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.util.EByteArrayReader;
import de.marcely.pocketcraft.java.util.EByteArrayWriter;

public class V1PacketLoginSetCompression extends LoginPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int threshold;

	@Override
	public byte getSource(){
		return SERVER;
	}
	
	@Override
	public void write(EByteArrayWriter stream) throws Exception {
		stream.writeSignedVarInt(this.threshold);
	}

	@Override
	public void read(EByteArrayReader stream) throws Exception {
		this.threshold = stream.readSignedVarInt();
	}
	
	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
