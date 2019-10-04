package de.marcely.pocketcraft.java.network.packet.handshake.v1;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;
import de.marcely.pocketcraft.java.util.EByteBuf;

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
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.protocolVersion);
		stream.writeString(this.serverAddress);
		stream.writeUnsignedShort(this.serverPort);
		stream.writeVarInt(this.nextState);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.protocolVersion = stream.readVarInt();
		this.serverAddress = stream.readString(255);
		this.serverPort = stream.readUnsignedShort();
		this.nextState = (byte) stream.readVarInt();
	}
	
	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}