package de.marcely.pocketcraft.java.network.packet.login.v1;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.util.EByteArrayReader;
import de.marcely.pocketcraft.java.util.EByteArrayWriter;

public class V1PacketLoginEncryptionRequest extends Packet {

	private static final PacketProperties PROPERTIES = new PacketProperties();
	
	public String serverId;
	public byte[] publicKey;
	public byte[] verifyToken;
	
	@Override
	public void write(EByteArrayWriter stream) throws Exception {
		stream.writeString(this.serverId);
		stream.writeByteArray(this.publicKey);
		stream.writeByteArray(this.verifyToken);
	}

	@Override
	public void read(EByteArrayReader stream) throws Exception {
		this.serverId = stream.readString(20);
		this.publicKey = stream.readByteArray();
		this.verifyToken = stream.readByteArray();
	}
	
	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
