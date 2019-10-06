package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayScoreboardDisplay extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public static final byte POSITION_LIST = 0;
	public static final byte POSITION_SIDEBAR = 1;
	public static final byte POSITION_BELOW_NAME = 2;
	
	public byte position;
	public String name;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeByte(this.position);
		stream.writeString(this.name, 16);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.position = stream.readByte();
		this.name = stream.readString(16);
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
