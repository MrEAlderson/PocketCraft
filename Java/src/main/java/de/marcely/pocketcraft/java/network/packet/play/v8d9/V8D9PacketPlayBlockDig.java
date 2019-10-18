package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.component.BlockFace;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;
import de.marcely.pocketcraft.utils.math.Vector3;

public class V8D9PacketPlayBlockDig extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public byte status;
	public Vector3 position;
	public BlockFace face;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.status);
		stream.writeBlockPosition(this.position);
		stream.writeByte(this.face.getId());
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.status = (byte) stream.readVarInt();
		this.position = stream.readBlockPosition();
		this.face = BlockFace.getById(stream.readByte());
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
