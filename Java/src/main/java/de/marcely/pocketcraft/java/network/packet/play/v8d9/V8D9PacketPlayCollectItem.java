package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayCollectItem extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int collectedEntityId;
	public int collectorEntityId;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.collectedEntityId);
		stream.writeVarInt(this.collectorEntityId);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.collectedEntityId = stream.readVarInt();
		this.collectorEntityId = stream.readVarInt();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
