package de.marcely.pocketcraft.raknet.packet;

import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

/**
 * 
 * @author Marcel S.
 * @date 01.12.2017 (dd/MM/yyyy)
 * @website https://marcely.de/
 */
public class RakNetConnectRequest1Packet extends RakNetPacket {
	
	public byte protocol;
	public int mtuSize;
	
	public RakNetConnectRequest1Packet(){
		super(RakNetPacket.TYPE_CONNECT_REQUEST1);
	}

	@Override
	public void decode(ByteArrayReader reader) throws Exception {
		reader.read(16); // magic
		this.protocol = reader.readSignedByte();
		this.mtuSize = reader.available();
	}

	@Override
	public void encode(ByteArrayWriter writer) throws Exception { }
}
