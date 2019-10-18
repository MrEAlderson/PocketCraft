package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;
import de.marcely.pocketcraft.utils.math.Vector3;

public class V8D9PacketPlayBlockDig extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public static final byte STATUS_DIG_START = 0;
	public static final byte STATUS_DIG_CANCEL = 1;
	public static final byte STATUS_DIG_FINISH = 2;
	public static final byte STATUS_DROP_ITEM_STACK = 3;
	public static final byte STATUS_DROP_ITEM_SINGLE = 4;
	public static final byte STATUS_FINISH_ACTION = 5;
	
	public byte status;
	public Vector3 position;
	public byte face; // not an actual BlockFace instance since it can also be 0xFF

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.status);
		stream.writeBlockPosition(this.position);
		stream.writeByte(this.face);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.status = (byte) stream.readVarInt();
		this.position = stream.readBlockPosition();
		this.face = stream.readByte();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
