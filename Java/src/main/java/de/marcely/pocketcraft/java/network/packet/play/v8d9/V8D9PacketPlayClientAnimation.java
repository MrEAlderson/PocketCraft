package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

/**
 * 
 * Sent when player is swinging his arm
 */
public class V8D9PacketPlayClientAnimation extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();

	@Override
	public void write(EByteBuf stream) throws Exception { }

	@Override
	public void read(EByteBuf stream) throws Exception { }

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
