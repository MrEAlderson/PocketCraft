package de.marcely.pocketcraft.java.network.packet.login.v1;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;
import de.marcely.pocketcraft.java.util.EByteArrayReader;
import de.marcely.pocketcraft.java.util.EByteArrayWriter;

public class V1PacketLoginDisconnect extends Packet {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	@Override
	public SequenceType getSequence(){
		return SequenceType.LOGIN;
	}

	@Override
	public byte getSource(){
		return SERVER;
	}

	@Override
	public void write(EByteArrayWriter stream) throws Exception {
		// TODO
	}

	@Override
	public void read(EByteArrayReader stream) throws Exception {
		// TODO
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
