package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayEntityVelocity extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int entityId;
	public float veloX;
	public float veloY;
	public float veloZ;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.entityId);
		stream.writeShort((short) (this.veloX * 8000F));
		stream.writeShort((short) (this.veloY * 8000F));
		stream.writeShort((short) (this.veloZ * 8000F));
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.entityId = stream.readVarInt();
		this.veloX = stream.readShort() / 8000F;
		this.veloY = stream.readShort() / 8000F;
		this.veloZ = stream.readShort() / 8000F;
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
