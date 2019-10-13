package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.component.nbt.NBTTagCompound;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;
import de.marcely.pocketcraft.utils.math.Vector3;

public class V8D9PacketPlayUpdateBlockEntity extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public static final byte ACTION_MOB_SPAWNER = 1;
	public static final byte ACTION_COMMAND_BLOCK = 2;
	public static final byte ACTION_BEACON = 3;
	public static final byte ACTION_SKULL = 4;
	public static final byte ACTION_FLOWER_POT = 5;
	public static final byte ACTION_BANNER = 6;
	
	public int x;
	public int y;
	public int z;
	public byte action;
	public NBTTagCompound data;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeBlockPosition(this.x, this.y, this.z);
		stream.writeByte(this.action);
		stream.writeNBT(this.data);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		{
			final Vector3 pos = stream.readBlockPosition();
			
			this.x = pos.getFloorX();
			this.y = pos.getFloorY();
			this.z = pos.getFloorZ();
		}
		
		this.action = stream.readByte();
		this.data = stream.readNBT();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
