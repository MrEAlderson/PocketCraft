package de.marcely.pocketcraft.java.network.packet.status.v1;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;
import de.marcely.pocketcraft.java.util.EByteArrayReader;
import de.marcely.pocketcraft.java.util.EByteArrayWriter;

public class V1PacketStatusPong extends Packet {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public long time;
	
	@Override
	public SequenceType getSequence(){
		return SequenceType.STATUS;
	}

	@Override
	public byte getSource(){
		return SERVER;
	}
	
	@Override
	public void write(EByteArrayWriter stream) throws Exception {
		stream.writeSignedLong(this.time);
	}

	@Override
	public void read(EByteArrayReader stream) throws Exception {
		this.time = stream.readSignedLong();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
