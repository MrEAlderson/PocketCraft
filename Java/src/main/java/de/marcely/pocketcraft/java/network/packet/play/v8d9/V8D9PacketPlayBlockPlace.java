package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.component.Item;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;
import de.marcely.pocketcraft.utils.math.Vector3;

public class V8D9PacketPlayBlockPlace extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public Vector3 position;
	public int face; // the face on which the block was placed; not an actual BlockFace instance since it can also be 0xFF
	public Item item;
	public float cursorPosX;
	public float cursorPosY;
	public float cursorPosZ;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeBlockPosition(this.position);
		stream.writeUnsignedByte(this.face);
		stream.writeItem(this.item);
		stream.writeUnsignedByte((byte) (this.cursorPosX * 16F));
		stream.writeUnsignedByte((byte) (this.cursorPosY * 16F));
		stream.writeUnsignedByte((byte) (this.cursorPosZ * 16F));
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.position = stream.readBlockPosition();
		this.face = stream.readUnsignedByte();
		this.item = stream.readItem();
		this.cursorPosX = stream.readUnsignedByte() / 16F;
		this.cursorPosY = stream.readUnsignedByte() / 16F;
		this.cursorPosZ = stream.readUnsignedByte() / 16F;
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
