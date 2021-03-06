package de.marcely.pocketcraft.java.network.packet.status.v1;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V1PacketStatusPing extends Packet {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public long time;
	
	@Override
	public SequenceType getSequence(){
		return SequenceType.STATUS;
	}
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeLong(this.time);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.time = stream.readLong();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
