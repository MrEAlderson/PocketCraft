package de.marcely.pocketcraft.raknet.packet;

import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

/**
 * 
 * @author Marcel S.
 * @date 01.12.2017 (dd/MM/yyyy)
 * @website https://marcely.de/
 */
public class RakNetConnectResponse1Packet extends RakNetPacket {

	public long serverID;
	public byte securityLevel = (byte) 0;
	public int mtuSize;
	
	public RakNetConnectResponse1Packet(){
		super(RakNetPacket.TYPE_CONNECT_RESPONSE1);
	}

	@Override
	public void decode(ByteArrayReader reader) throws Exception { }

	@Override
	public void encode(ByteArrayWriter writer) throws Exception {
		writer.write(RakNetPacket.MAGIC);
		writer.writeUnsignedLong(serverID);
		writer.writeSignedByte(securityLevel);
		writer.writeUnsignedShort(mtuSize);
	}
}
