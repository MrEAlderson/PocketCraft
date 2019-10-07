package de.marcely.pocketcraft.translate.bedrocktojava.world;

import de.marcely.pocketcraft.bedrock.network.packet.PacketFullChunk;
import de.marcely.pocketcraft.java.component.v8.V8Biome;
import de.marcely.pocketcraft.java.component.v8.V8ChunkSection;
import de.marcely.pocketcraft.translate.world.V8BiomeTranslator;
import lombok.Getter;

public class V8Chunk extends Chunk {
	
	@Getter private final de.marcely.pocketcraft.java.component.v8.V8Chunk reference;
	
	public V8Chunk(de.marcely.pocketcraft.java.component.v8.V8Chunk ref){
		this.reference = ref;
	}

	@Override
	public PacketFullChunk buildPacket(int x, int z){
		final de.marcely.pocketcraft.bedrock.world.Chunk bedrock = new de.marcely.pocketcraft.bedrock.world.Chunk();
		
		// sections
		for(int si=0; si<16; si++){
			final V8ChunkSection section = reference.getSections()[si];
			
			if(section == null)
				continue;
			
			for(int ix=0; ix<16; ix++){
				for(int iy=0; iy<16; iy++){
					for(int iz=0; iz<16; iz++){
						bedrock.setBlockId(ix, iy+si*16, iz, section.getId(ix, iy, iz));
					}
				}
			}
		}
		
		// biomes
		for(int ix=0; ix<16; ix++){
			for(int iz=0; iz<16; iz++){
				final V8Biome biome = V8Biome.getById(reference.getBiomeId(ix, iz));
				
				if(biome == null)
					continue;
				
				bedrock.setBiome(ix, iz, V8BiomeTranslator.toBedrock(biome));
			}
		}
		
		return bedrock.buildPacket(x, z);
	}
}
