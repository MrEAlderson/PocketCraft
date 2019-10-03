package de.marcely.pocketcraft.java.network.packet.handshake.v1;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;
import de.marcely.pocketcraft.java.util.EByteArrayReader;
import de.marcely.pocketcraft.java.util.EByteArrayWriter;

public class V1PacketHandshake extends Packet {
	
	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int protocolVersion;
	public String serverAddress;
	public int serverPort;
	public byte nextState;
	
	@Override
	public SequenceType getSequence(){
		return SequenceType.HANDSHAKE;
	}

	@Override
	public byte getSource(){
		return CLIENT;
	}
	
	@Override
	public void write(EByteArrayWriter stream) throws Exception {
		stream.writeSignedVarInt(this.protocolVersion);
		stream.writeString(this.serverAddress);
		stream.writeUnsignedShort(this.serverPort);
		stream.writeSignedVarInt(this.nextState);
	}

	@Override
	public void read(EByteArrayReader stream) throws Exception {
		this.protocolVersion = stream.readSignedVarInt();
		this.serverAddress = stream.readString(255);
		this.serverPort = stream.readUnsignedShort();
		this.nextState = (byte) stream.readSignedVarInt();
	}
	
	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}