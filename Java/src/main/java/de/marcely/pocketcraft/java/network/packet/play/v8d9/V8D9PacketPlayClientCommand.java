package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayClientCommand extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public static final byte COMMAND_PERFORM_RESPAWN = 0;
	public static final byte COMMAND_REQUEST_STATS = 1;
	public static final byte COMMAND_OPEN_INVENTORY_ACHIEVEMENT = 2;
	
	public byte command;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.command);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.command = (byte) stream.readVarInt();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
