package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.component.BlockFace;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;
import de.marcely.pocketcraft.utils.math.Vector3;

public class V8D9PacketPlaySpawnPainting extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int entityId;
	public String paintingName;
	public int x;
	public int y;
	public int z;
	public BlockFace direction;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.entityId);
		stream.writeString(this.paintingName, 13);
		stream.writeBlockPosition(this.x, this.y, this.z);
		stream.writeByte(this.direction.getSideId());
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.entityId = stream.readVarInt();
		this.paintingName = stream.readString(13);
		
		{
			final Vector3 loc = stream.readBlockPosition();
			
			this.x = loc.getFloorX();
			this.y = loc.getFloorY();
			this.z = loc.getFloorZ();
		}
		
		this.direction = BlockFace.getBySideId(stream.readByte());
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
