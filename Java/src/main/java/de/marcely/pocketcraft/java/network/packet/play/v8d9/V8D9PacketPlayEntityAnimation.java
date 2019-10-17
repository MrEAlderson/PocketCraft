package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayEntityAnimation extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public static final byte TYPE_SWING_ARM = 0;
	public static final byte TYPE_TAKE_DAMAGE = 1;
	public static final byte TYPE_LEAVE_BED = 2;
	public static final byte TYPE_EAT_FOOD = 3;
	public static final byte TYPE_CRITICAL_EFFECT = 4;
	public static final byte TYPE_MAGIC_CRITICAL_EFFECT = 5;
	
	public int entityId;
	public byte type;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.entityId);
		stream.writeByte(this.type);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.entityId = stream.readVarInt();
		this.type = stream.readByte();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
