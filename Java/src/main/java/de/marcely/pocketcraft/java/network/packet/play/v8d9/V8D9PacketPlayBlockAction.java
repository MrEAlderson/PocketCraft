package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;
import de.marcely.pocketcraft.utils.math.Vector3;

public class V8D9PacketPlayBlockAction extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	/*
	 * List of block actions:
	 * == Note Block
	 *  data 1: instrument type
	 *     -> 0: harp
	 *     -> 1: double bass
	 *     -> 2: snare drum
	 *     -> 3: click/sticks
	 *     -> 4: bass drum
	 *  data 2: pitch between 0-24 (24 is highest)
	 *  
	 *  == piston:
	 *   data 1: 0 = pushing, 1 = pulling
	 *   data 2: direction
	 *     -> 0: down
	 *     -> 1: up
	 *     -> 2: south
	 *     -> 3: west
	 *     -> 4: north
	 *     -> 5: east
	 *     
	 *  == chest:
	 *   data 1: not used, always 1
	 *   data 2: 0 = close, 1 = open
	 *   
	 *  == beacon:
	 *   none, only used for updating them (?)
	 */
	
	public int x;
	public int y;
	public int z;
	public short data1;
	public short data2;
	public int blockId;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeBlockPosition(this.x, this.y, this.z);
		stream.writeUnsignedByte(this.data1);
		stream.writeUnsignedByte(this.data2);
		stream.writeVarInt(this.blockId);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		{
			final Vector3 pos = stream.readBlockPosition();
			
			this.x = pos.getFloorX();
			this.y = pos.getFloorY();
			this.z = pos.getFloorZ();
		}
		
		this.data1 = stream.readUnsignedByte();
		this.data2 = stream.readUnsignedByte();
		this.blockId = stream.readVarInt();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}