package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayParticles extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int type;
	public boolean longDistance; // true = increases distance from 256 to 65536
	public float x;
	public float y;
	public float z;
	public float offsetX;
	public float offsetY;
	public float offsetZ;
	public float data;
	public int amount;
	public int[] data2;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeInt(this.type);
		stream.writeBoolean(this.longDistance);
		stream.writeFloat(this.x);
		stream.writeFloat(this.y);
		stream.writeFloat(this.z);
		stream.writeFloat(this.offsetX);
		stream.writeFloat(this.offsetY);
		stream.writeFloat(this.offsetZ);
		stream.writeFloat(this.data);
		stream.writeInt(this.amount);
		
		{
			final int length = getData2Length();
			
			if(length != this.data2.length)
				throw new IllegalStateException("Given data 2 arrays doesn't have the length than expected (got " + this.data2.length + ", expected " + length + ")");
			
			for(int i=0; i<length; i++)
				stream.writeVarInt(this.data2[i]);
		}
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.type = stream.readInt();
		this.longDistance = stream.readBoolean();
		this.x = stream.readFloat();
		this.y = stream.readFloat();
		this.z = stream.readFloat();
		this.offsetX = stream.readFloat();
		this.offsetY = stream.readFloat();
		this.offsetZ = stream.readFloat();
		this.data = stream.readFloat();
		this.amount = stream.readInt();
		
		{
			this.data2 = new int[getData2Length()];
			
			for(int i=0; i<this.data2.length; i++)
				this.data2[i] = stream.readVarInt();
		}
	}
	
	private int getData2Length(){
		switch(this.type){
		case 36: // iconcrack
			return 2;
			
		case 37: // blockcrack
		case 38: // blockdust
			return 1;
			
		default:
			return 0;
		}
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
