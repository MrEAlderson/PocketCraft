package de.marcely.pocketcraft.raknet.packet;

import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

/**
 * 
 * @author Marcel S.
 * @date 01.12.2017 (dd/MM/yyyy)
 * @website https://marcely.de/
 */
public class RakNetUnconnectedPongPacket extends RakNetPacket {
	
	public long sendPingTime, serverID;
	public String serverName;
	
	public RakNetUnconnectedPongPacket(){
		super(RakNetPacket.TYPE_UNCONNECTED_PONG);
	}

	@Override
	public void decode(ByteArrayReader reader) throws Exception { }

	@Override
	public void encode(ByteArrayWriter writer) throws Exception {
		writer.writeUnsignedLong(this.sendPingTime);
		writer.writeUnsignedLong(this.serverID);
		writer.write(RakNetPacket.MAGIC);
		writer.writeShortString(this.serverName);
	}
}
