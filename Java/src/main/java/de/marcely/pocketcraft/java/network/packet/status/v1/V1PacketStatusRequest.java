package de.marcely.pocketcraft.java.network.packet.status.v1;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V1PacketStatusRequest extends Packet {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	@Override
	public SequenceType getSequence(){
		return SequenceType.STATUS;
	}

	@Override
	public byte getSource(){
		return CLIENT;
	}
	
	@Override
	public void write(EByteBuf stream) throws Exception { }

	@Override
	public void read(EByteBuf stream) throws Exception { }

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
