package de.marcely.pocketcraft.raknet.packet.encapsulated;

import de.marcely.pocketcraft.raknet.packet.RakNetPacket;
import de.marcely.pocketcraft.raknet.packet.data.RakNetDataPacket;
import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

/**
 * 
 * @author Marcel S.
 * @date 17.12.2017 (dd/MM/yyyy)
 * @website https://marcely.de/
 */
public class RakNetDataPongPacket extends RakNetPacket {
	
	public long ID;
	
	public RakNetDataPongPacket(){
		super(RakNetDataPacket.TYPE_DATA_PONG);
	}

	@Override
	public void decode(ByteArrayReader reader) throws Exception {
		this.ID = reader.readUnsignedLong();
	}

	@Override
	public void encode(ByteArrayWriter writer) throws Exception {
		writer.writeUnsignedLong(ID);
	}
}
