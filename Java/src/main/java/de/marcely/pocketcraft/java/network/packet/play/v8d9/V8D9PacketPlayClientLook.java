package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayClientLook extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public boolean isOnGround;
	public float yaw;
	public float pitch;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeFloat(this.yaw);
		stream.writeFloat(this.pitch);
		stream.writeBoolean(this.isOnGround);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.yaw = stream.readFloat();
		this.pitch = stream.readFloat();
		this.isOnGround = stream.readBoolean();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
