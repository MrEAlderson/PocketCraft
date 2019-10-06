package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayMapChunkBulk extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public int[] x;
	public int[] z;
	public byte[][] data;
	public boolean isFullChunk;
	public int[] primaryBitMask;

	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeBoolean(this.isFullChunk);
		stream.writeVarInt(this.x.length);
		
		for(int i=0; i<this.x.length; i++){
			stream.writeInt(this.x[i]);
			stream.writeInt(this.z[i]);
			stream.writeShort((short)(this.primaryBitMask[i] & 0xFFFF));
		}
		
		for(int i=0; i<this.x.length; i++)
			stream.write(this.data[i]);
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.isFullChunk = stream.readBoolean();
		{
			final int size = stream.readVarInt();
			
			this.x = new int[size];
			this.z = new int[size];
			this.data = new byte[size][];
			this.primaryBitMask = new int[size];
		}
		
		for(int i=0; i<this.x.length; i++){
			this.x[i] = stream.readInt();
			this.z[i] = stream.readInt();
			this.primaryBitMask[i] = (short)(stream.readShort() & 0xFFFF);
			this.data[i] = new byte[getChunkDataSize(Integer.bitCount(this.primaryBitMask[i]), this.isFullChunk, true)];
		}
		
		for(int i=0; i<this.x.length; i++)
			stream.read(this.data[i]);
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
	
	private int getChunkDataSize(int i, boolean flag, boolean flag1){
		int j = i * 2 * 16 * 16 * 16;
	    int k = i * 16 * 16 * 16 / 2;
	    int l = flag ? i * 16 * 16 * 16 / 2 : 0;
	    int i1 = flag1 ? 256 : 0;
	    
	    return j + k + l + i1;
	}
}
