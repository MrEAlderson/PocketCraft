package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayMultiBlockChange extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int chunkX;
	public int chunkZ;
	public byte[] relX;
	public short[] y;
	public byte[] relZ;
	public short[] id;
	public byte[] data;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeInt(this.chunkX);
		stream.writeInt(this.chunkZ);
		stream.writeVarInt(this.relX.length);
		
		for(int i=0; i<this.relX.length; i++){
			stream.writeUnsignedByte((this.relX[i] << 4) | (this.relZ[i] & 0x0F));
			stream.writeUnsignedByte(this.y[i]);
			stream.writeVarInt((this.id[i] << 4) | this.data[i]);
		}
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.chunkX = stream.readInt();
		this.chunkZ = stream.readInt();
		
		{
			final int size = stream.readVarInt();
			
			this.relX = new byte[size];
			this.y = new short[size];
			this.relZ = new byte[size];
			this.id = new short[size];
			this.data = new byte[size];
		}
		
		for(int i=0; i<this.relX.length; i++){
			final byte rel = stream.readByte();
			
			this.relX[i] = (byte) ((rel & 0xF0) >> 4);
			this.y[i] = stream.readUnsignedByte();
			this.relZ[i] = (byte) (rel & 0x0F);
			
			{
				final int raw = stream.readVarInt();
				
				this.id[i] = (short) ((raw & 0xFFFF0) >> 4);
				this.data[i] = (byte) (raw & 0x000F);
			}
		}
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}
