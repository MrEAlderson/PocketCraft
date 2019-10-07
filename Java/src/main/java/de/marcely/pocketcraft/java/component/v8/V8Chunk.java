package de.marcely.pocketcraft.java.component.v8;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.java.util.EByteBuf;
import lombok.Getter;

public class V8Chunk {
	
	@Getter private final V8ChunkSection[] sections;
	@Getter private byte[] biomes;
	
	public V8Chunk(){
		this.sections = new V8ChunkSection[16];
		this.biomes = new byte[16*16];
	}
	
	public @Nullable V8Biome getBiome(int x, int z){
		return V8Biome.getById(getBiomeId(x, z));
	}
	
	public byte getBiomeId(int x, int z){
		return this.biomes[(x << 4) | z];
	}
	
	public void setBiome(int x, int z, V8Biome biome){
		setBiome(x, z, biome.getId());
	}
	
	public void setBiome(int x, int z, byte id){
		this.biomes[(x << 4) | z] = id;
	}
	
	public void setBlockId(int x, int y, int z, short id){
		V8ChunkSection section = this.sections[y >> 4];
		
		if(section == null){
			this.sections[y >> 4] = section = new V8ChunkSection();
		}
		
		section.setId(x, y % 16, z, id);
	}
	
	public short getBlockId(int x, int y, int z){
		final V8ChunkSection section = this.sections[y >> 4];
		
		return section != null ? section.getId(x, y % 16, z) : 0;
	}
	
	public void setBlockData(int x, int y, int z, byte data){
		V8ChunkSection section = this.sections[y >> 4];
		
		if(section == null){
			this.sections[y >> 4] = section = new V8ChunkSection();
		}
		
		section.setData(x, y % 16, z, data);
	}
	
	public byte getBlockData(int x, int y, int z){
		final V8ChunkSection section = this.sections[y >> 4];
		
		return section != null ? section.getData(x, y % 16, z) : 0;
	}
	
	/**
	 * 
	 * @param chunk Should be null if PacketPlayMapChunk#fullChunk is true. Otherwise it should take (if available) the cached one
	 * @return Returns the given chunk. It'll create and return a new instance if the given chunk is null
	 */
	public static V8Chunk read(byte[] data, int primaryBitMask, @Nullable V8Chunk chunk){
		final EByteBuf buf = new EByteBuf(data);
		final boolean isFullChunk = chunk != null;
		
		if(chunk == null)
			chunk = new V8Chunk();
		
		try{
			// read sections
			{
				for(int si=0; si<16; si++){
					if((primaryBitMask & (0x1 << si)) == 0)
						continue;
					
					chunk.sections[si] = new V8ChunkSection(buf.read(16*16*16*2));
				}
			}
			
			if(isFullChunk){
				// skip light levels
				while(buf.readableBytes() > 2048)
					buf.skipBytes(2048);
				
				// biome ids
				chunk.biomes = buf.read(16*16);
			}
		}finally{
			buf.release();
		}
		
		return chunk;
	}
}