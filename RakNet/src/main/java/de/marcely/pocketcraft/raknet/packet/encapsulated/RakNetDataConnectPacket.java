package de.marcely.pocketcraft.raknet.packet.encapsulated;

import de.marcely.pocketcraft.raknet.packet.RakNetPacket;
import de.marcely.pocketcraft.raknet.packet.data.RakNetDataPacket;
import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

/**
 * 
 * @author Marcel S.
 * @date 02.12.2017 (dd/MM/yyyy)
 * @website https://marcely.de/
 */
public class RakNetDataConnectPacket extends RakNetPacket {

	public RakNetDataConnectPacket(){
		super(RakNetDataPacket.TYPE_DATA_CONNECT);
	}
	
	public long clientID;
	public long ping;
	public boolean useSecurity;

	@Override
	public void decode(ByteArrayReader reader) throws Exception {
        this.clientID = reader.readUnsignedLong();
        this.ping = reader.readUnsignedLong();
        this.useSecurity = reader.readBoolean();
	}

	@Override
	public void encode(ByteArrayWriter writer) throws Exception { }
}
