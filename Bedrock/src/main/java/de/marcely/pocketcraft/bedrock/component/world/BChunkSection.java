package de.marcely.pocketcraft.bedrock.component.world;

import lombok.Getter;

public class BChunkSection {
	
	private static final int SIZE = 16*16*16;
	
	@Getter private final byte[] data;
	
	@Getter private int blocksAmount = 0;
	
	public BChunkSection(){
		this.data = new byte[SIZE+SIZE/2];
	}
	
	private static int getIndex(int x, int y, int z, int section){
		if(section == 0)
			return ((x << 8) + (z << 4) + y);
		else if(section == 1)
			return SIZE + ((x << 8) + (z << 4) + y)/2;
		else
			return -1;
	}
	
	public short getBlockId(int x, int y, int z){
		return (short) (this.data[getIndex(x, y, z, 0)]&0xFF);
	}
	
	public byte getBlockData(int x, int y, int z){
		final int index = getIndex(x, y, z, 1);
		final byte val = this.data[index];
		
		if((index & 1) == 0)
			return (byte) (val & 0x0F);
		else
			return (byte) ((val & 0xF0) >> 4);
	}
	
	public void setBlockId(int x, int y, int z, short id){
		final int old = getBlockId(x, y, z);
		
		// 0 = air
		if(old == id)
			return;
		else if(old == 0)
			this.blocksAmount++;
		else if(id == 0)
			this.blocksAmount--;
		
		this.data[getIndex(x, y, z, 0)] = (byte) (id & 0xFF);
	}
	
	public void setBlockData(int x, int y, int z, byte data){
		final int index = getIndex(x, y, z, 1);
		final byte old = this.data[index];
		
		data &= 0xF;
		
		if((y & 1) == 0)
			this.data[index] = (byte) ((old & 0xF0) | data);
		else
			this.data[index] = (byte) ((old & 0x0F) | (data << 4));
	}
}