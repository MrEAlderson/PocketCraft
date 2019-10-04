package de.marcely.pocketcraft.java.network.packet.status.v1;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V1PacketStatusResponse extends Packet {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public String response;
	
	@Override
	public SequenceType getSequence(){
		return SequenceType.STATUS;
	}

	@Override
	public byte getSource(){
		return SERVER;
	}
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeString(this.response);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.response = stream.readString(32767);
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
