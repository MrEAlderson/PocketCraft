package de.marcely.pocketcraft.java.component.v8;

import java.security.InvalidParameterException;

public class V8ChunkSection {
	
	private byte[] blocks;
	
	public V8ChunkSection(){
		this.blocks = new byte[16*16*16*2];
	}
	
	public V8ChunkSection(byte[] blocks){
		if(blocks == null)
			blocks = new byte[16*16*16*2];
		
		if(blocks.length != 16*16*16*2)
			throw new InvalidParameterException("Given blocks array doesn't have the expected size");
		
		this.blocks = blocks;
	}
	
	public void setId(int x, int y, int z, short id){
		final int index = (y << 9) | (z << 5) | (x << 1);
		
		this.blocks[index] = (byte) ((id & 0x0FF0) >> 4);
		this.blocks[index+1] = (byte) ((id & 0x0F) << 4);
	}
	
	public short getId(int x, int y, int z){
		final int index = (y << 9) | (z << 5) | (x << 1);
		
		return (short) ((((short) this.blocks[index+1]) << 4) | ((((short) this.blocks[index] & 0xF0) >> 4)));
	}
	
	public void setData(int x, int y, int z, byte data){
		final int index = (y << 9) | (z << 5) | (x << 1);
		
		this.blocks[index] |= (data & 0x0F);
	}
	
	public byte getData(int x, int y, int z){
		final int index = (y << 9) | (z << 5) | (x << 1);
		
		return (byte) (this.blocks[index] & 0x0F);
	}
}