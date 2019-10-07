package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayExplosion extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public float x;
	public float y;
	public float z;
	public float radius;
	public byte[] affectedBlocksRelX;
	public byte[] affectedBlocksRelY;
	public byte[] affectedBlocksRelZ;
	public float velocityX;
	public float velocityY;
	public float velocityZ;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeFloat(this.x);
		stream.writeFloat(this.y);
		stream.writeFloat(this.z);
		stream.writeFloat(this.radius);
		stream.writeInt(this.affectedBlocksRelX.length);
		
		for(int i=0; i<this.affectedBlocksRelX.length; i++){
			stream.writeByte(this.affectedBlocksRelX[i]);
			stream.writeByte(this.affectedBlocksRelY[i]);
			stream.writeByte(this.affectedBlocksRelZ[i]);
		}
		
		stream.writeFloat(this.velocityX);
		stream.writeFloat(this.velocityY);
		stream.writeFloat(this.velocityZ);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.x = stream.readFloat();
		this.y = stream.readFloat();
		this.z = stream.readFloat();
		
		{
			final int amount = stream.readInt();
			
			this.affectedBlocksRelX = new byte[amount];
			this.affectedBlocksRelY = new byte[amount];
			this.affectedBlocksRelZ = new byte[amount];
			
			for(int i=0; i<amount; i++){
				this.affectedBlocksRelX[i] = stream.readByte();
				this.affectedBlocksRelY[i] = stream.readByte();
				this.affectedBlocksRelZ[i] = stream.readByte();
			}
		}
		
		this.velocityX = stream.readFloat();
		this.velocityY = stream.readFloat();
		this.velocityZ = stream.readFloat();
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
