package de.marcely.pocketcraft.translate.bedrocktojava.world;

import de.marcely.pocketcraft.bedrock.network.packet.PacketFullChunk;
import de.marcely.pocketcraft.java.component.v8.V8Biome;
import de.marcely.pocketcraft.java.component.v8.V8ChunkSection;
import de.marcely.pocketcraft.translate.world.V8BiomeTranslator;
import de.marcely.pocketcraft.translate.world.V8BlockTranslator;
import de.marcely.pocketcraft.utils.Pair;
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
						final int y = iy+si*16;
						
						final Pair<Short, Byte> pair = V8BlockTranslator.toBedrock(section.getId(ix, iy, iz), section.getData(ix, iy, iz));
						
						bedrock.setBlockId(ix, y, iz, pair.getEntry1());
						bedrock.setBlockData(ix, y, iz, pair.getEntry2());
						
						V8BlockEntityTranslator.handleSpawn(null, this, ix, y, iz, pair.getEntry1(), pair.getEntry2(), null);;
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
		
		// others
		bedrock.setBlockEntities(this.getBlockEntities());
		
		return bedrock.buildPacket(x, z);
	}
}
