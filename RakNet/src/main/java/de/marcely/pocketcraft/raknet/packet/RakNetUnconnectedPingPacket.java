package de.marcely.pocketcraft.raknet.packet;

import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

/**
 * 
 * @author Marcel S.
 * @date 01.12.2017 (dd/MM/yyyy)
 * @website https://marcely.de/
 */
public class RakNetUnconnectedPingPacket extends RakNetPacket {
	
	public long sendPingTime;
	public long clientID;
	
	public RakNetUnconnectedPingPacket(){
		super(RakNetPacket.TYPE_UNCONNECTED_PING);
	}

	@Override
	public void decode(ByteArrayReader reader) throws Exception {
		this.sendPingTime = reader.readUnsignedLong();
		reader.read(RakNetPacket.MAGIC);
		this.clientID = reader.readUnsignedLong();
	}

	@Override
	public void encode(ByteArrayWriter writer) throws Exception { }
}