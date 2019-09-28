package de.marcely.pocketcraft.raknet.packet.encapsulated;

import de.marcely.pocketcraft.raknet.packet.RakNetPacket;
import de.marcely.pocketcraft.raknet.packet.data.RakNetDataPacket;
import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

/**
 * 
 * @author Marcel S.
 * @date 08.12.2017 (dd/MM/yyyy)
 * @website https://marcely.de/
 */
public class RakNetDataDisconnectPacket extends RakNetPacket {

	public RakNetDataDisconnectPacket(){
		super(RakNetDataPacket.TYPE_DATA_DISCONNECT);
	}

	@Override
	public void decode(ByteArrayReader reader) throws Exception { }

	@Override
	public void encode(ByteArrayWriter writer) throws Exception { }
}
