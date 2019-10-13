package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;
import de.marcely.pocketcraft.utils.math.Vector3;

public class V8D9PacketPlayBlockBreakAnimation extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int entityId;
	public int x;
	public int y;
	public int z;
	public byte stage; /* from 0-9 */

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.entityId);
		stream.writeBlockPosition(this.x, this.y, this.z);
		stream.writeByte(this.stage);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.entityId = stream.readVarInt();
		
		{
			final Vector3 pos = stream.readBlockPosition();
			
			this.x = pos.getFloorX();
			this.y = pos.getFloorY();
			this.z = pos.getFloorZ();
		}
		
		this.stage = stream.readByte();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
