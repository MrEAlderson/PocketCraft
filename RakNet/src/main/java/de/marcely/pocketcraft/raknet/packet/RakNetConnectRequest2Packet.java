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
public class RakNetConnectRequest2Packet extends RakNetPacket {
	
	public InetSocketAddress ip;
	public int mtuSize;
	public long clientID;
	
	public RakNetConnectRequest2Packet(){
		super(RakNetPacket.TYPE_CONNECT_REQUEST2);
	}

	@Override
	public void decode(ByteArrayReader reader) throws Exception {
		reader.read(16); // magic
		this.ip = IOUtil.readAddress(reader);
		this.mtuSize = reader.readUnsignedShort();
		this.clientID = reader.readUnsignedLong();
	}

	@Override
	public void encode(ByteArrayWriter writer) throws Exception { }
}
