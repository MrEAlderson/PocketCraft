package de.marcely.pocketcraft.bedrock.world;

import lombok.Getter;

public class Chunk {
	
	@Getter private final ChunkSection[] sections;
	
	public Chunk(){
		this.sections = new ChunkSection[16];
		
		for(int i=0; i<this.sections.length; i++)
			this.sections[i] = new ChunkSection();
	}
	
	public short getBlockId(int x, int y, int z){
		return this.sections[y >> 4].getBlockId(x, y%16, z);
	}
	
	public byte getBlockData(int x, int y, int z){
		return this.sections[y >> 4].getBlockData(x, y%16, z);
	}
	
	public void setBlockId(int x, int y, int z, short id){
		this.sections[y >> 4].setBlockId(x, y%16, z, id);
	}
	
	public void setBlockData(int x, int y, int z, byte data){
		this.sections[y >> 4].setBlockData(x, y%16, z, data);
	}
}