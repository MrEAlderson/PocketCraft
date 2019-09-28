package de.marcely.pocketcraft.raknet.packet.encapsulated;

import java.net.InetAddress;
import java.net.InetSocketAddress;

import de.marcely.pocketcraft.raknet.packet.RakNetPacket;
import de.marcely.pocketcraft.raknet.packet.data.RakNetDataPacket;
import de.marcely.pocketcraft.raknet.util.IOUtil;
import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

/**
 * 
 * @author Marcel S.
 * @date 02.12.2017 (dd/MM/yyyy)
 * @website https://marcely.de/
 */
public class RakNetDataHandshake1Packet extends RakNetPacket {

    private static final InetSocketAddress[] SYSTEM_ADDRESSES = new InetSocketAddress[]{
            new InetSocketAddress("127.0.0.1", 0),
            new InetSocketAddress("0.0.0.0", 0),
            new InetSocketAddress("0.0.0.0", 0),
            new InetSocketAddress("0.0.0.0", 0),
            new InetSocketAddress("0.0.0.0", 0),
            new InetSocketAddress("0.0.0.0", 0),
            new InetSocketAddress("0.0.0.0", 0),
            new InetSocketAddress("0.0.0.0", 0),
            new InetSocketAddress("0.0.0.0", 0),
            new InetSocketAddress("0.0.0.0", 0)
    };
	
    public InetAddress address;
    public int port;
    public long ping, pong;
	
	public RakNetDataHandshake1Packet(){
		super(RakNetDataPacket.TYPE_DATA_HANDSHAKE1);
	}

	@Override
	public void decode(ByteArrayReader reader) throws Exception { }

	@Override
	public void encode(ByteArrayWriter writer) throws Exception {
		IOUtil.writeAddress(new InetSocketAddress(address, port), writer);
		writer.writeUnsignedShort(0);
		
		for(InetSocketAddress address:SYSTEM_ADDRESSES)
			IOUtil.writeAddress(address, writer);
		
		writer.writeSignedLong(ping);
		writer.writeSignedLong(pong);
	}
}
