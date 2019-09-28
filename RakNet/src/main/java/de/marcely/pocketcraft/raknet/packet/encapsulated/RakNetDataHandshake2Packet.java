package de.marcely.pocketcraft.raknet.packet.encapsulated;

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
public class RakNetDataHandshake2Packet extends RakNetPacket {

    public InetSocketAddress ip;
    public InetSocketAddress[] systemAddresses;
    public long ping, pong;
	
	public RakNetDataHandshake2Packet(){
		super(RakNetDataPacket.TYPE_DATA_HANDSHAKE2);
	}

	@Override
	public void decode(ByteArrayReader reader) throws Exception {
		this.ip = IOUtil.readAddress(reader);

		this.systemAddresses = new InetSocketAddress[10];
        for(int i=0; i<10; i++)
            this.systemAddresses[i] = IOUtil.readAddress(reader);

        this.ping = reader.readUnsignedLong();
        this.pong = reader.readUnsignedLong();
	}

	@Override
	public void encode(ByteArrayWriter writer) throws Exception { }
}
