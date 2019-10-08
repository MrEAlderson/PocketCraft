package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayClientEntityAction extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public static final byte TYPE_SNEAK_START = 0;
	public static final byte TYPE_SNEAK_STOP = 1;
	public static final byte TYPE_LEAVE_BED = 2;
	public static final byte TYPE_SPRINT_START = 3;
	public static final byte TYPE_SPRINT_STOP = 4;
	public static final byte TYPE_HORSE_JUMP = 5;
	public static final byte TYPE_OPEN_INVENTORY = 6;
	
	public int entityId;
	public byte type;
	// ranges from 0 to 100
	public byte horseJumpBoost = 0;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.entityId);
		stream.writeVarInt(this.type);
		stream.writeVarInt(this.horseJumpBoost);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.entityId = stream.readVarInt();
		this.type = (byte) stream.readVarInt();
		this.horseJumpBoost = (byte) stream.readVarInt();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
