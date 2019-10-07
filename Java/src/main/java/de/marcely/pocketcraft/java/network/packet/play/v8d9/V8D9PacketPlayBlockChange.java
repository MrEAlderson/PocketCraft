package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;
import de.marcely.pocketcraft.utils.math.Vector3;

public class V8D9PacketPlayBlockChange extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int x;
	public int y;
	public int z;
	public short id;
	public byte data;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeBlockPosition(this.x, this.y, this.z);
		stream.writeVarInt(this.id << 4 | this.data);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		{
			final Vector3 loc = stream.readBlockPosition();
			
			this.x = loc.getFloorX();
			this.y = loc.getFloorY();
			this.z = loc.getFloorZ();
		}
		
		{
			final int raw = stream.readVarInt();
			
			this.id = (short) ((raw & 0xFFFF0) >> 4);
			this.data = (byte) (raw & 0x000F);
		}
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
