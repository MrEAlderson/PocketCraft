package de.marcely.pocketcraft.raknet.packet;

import java.net.InetSocketAddress;

import de.marcely.pocketcraft.raknet.util.IOUtil;
import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

/**
 * 
 * @author Marcel S.
 * @date 01.12.2017 (dd/MM/yyyy)
 * @website https://marcely.de/
 */
public class RakNetConnectResponse2Packet extends RakNetPacket {
	
	public long serverID;
	public InetSocketAddress ip;
	public int mtuSize;
	
	public RakNetConnectResponse2Packet(){
		super(RakNetPacket.TYPE_CONNECT_RESPONSE2);
	}

	@Override
	public void decode(ByteArrayReader reader) throws Exception { }

	@Override
	public void encode(ByteArrayWriter writer) throws Exception {
		writer.write(RakNetPacket.MAGIC);
		writer.writeUnsignedLong(serverID);
		IOUtil.writeAddress(ip, writer);
		writer.writeUnsignedShort(mtuSize);
	}
}
