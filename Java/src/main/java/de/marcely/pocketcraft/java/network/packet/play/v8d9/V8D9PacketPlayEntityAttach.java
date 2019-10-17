package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayEntityAttach extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int riderEntityId;
	public int vehicleEntityId;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeInt(this.riderEntityId);
		stream.writeInt(this.vehicleEntityId);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.riderEntityId = stream.readInt();
		this.vehicleEntityId = stream.readInt();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
