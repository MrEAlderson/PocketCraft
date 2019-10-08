package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayUpdateHealth extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public float health;
	public int foodLevel;
	public float foodSaturation;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeFloat(this.health);
		stream.writeVarInt(this.foodLevel);
		stream.writeFloat(this.foodSaturation);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.health = stream.readFloat();
		this.foodLevel = stream.readVarInt();
		this.foodSaturation = stream.readFloat();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
