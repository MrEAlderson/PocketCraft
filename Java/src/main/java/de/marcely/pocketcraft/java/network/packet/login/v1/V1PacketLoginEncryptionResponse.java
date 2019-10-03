package de.marcely.pocketcraft.java.network.packet.login.v1;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;
import de.marcely.pocketcraft.java.util.EByteArrayReader;
import de.marcely.pocketcraft.java.util.EByteArrayWriter;

public class V1PacketLoginEncryptionResponse extends Packet {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public byte[] sharedKey;
	public byte[] verifyToken;
	
	@Override
	public SequenceType getSequence(){
		return SequenceType.LOGIN;
	}

	@Override
	public byte getSource(){
		return CLIENT;
	}
	
	@Override
	public void write(EByteArrayWriter stream) throws Exception {
		stream.writeByteArray(this.sharedKey);
		stream.writeByteArray(this.verifyToken);
	}

	@Override
	public void read(EByteArrayReader stream) throws Exception {
		this.sharedKey = stream.readByteArray();
		this.verifyToken = stream.readByteArray();
	}
	
	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
