package de.marcely.pocketcraft.translate.bedrocktojava.world.v8;

import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import de.marcely.pocketcraft.bedrock.network.packet.PacketFullChunk;
import de.marcely.pocketcraft.java.component.v8.V8Biome;
import de.marcely.pocketcraft.java.component.v8.V8ChunkSection;
import de.marcely.pocketcraft.translate.Resources;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Chunk;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;
import de.marcely.pocketcraft.translate.bedrocktojava.world.block.BlockState;
import de.marcely.pocketcraft.translate.bedrocktojava.world.block.IDBlockStatesList;
import de.marcely.pocketcraft.utils.Pair;
import lombok.Cleanup;
import lombok.Getter;

public class V8Chunk extends Chunk {
	
	public static IDBlockStatesList BLOCK_STATES_INSTANCE;
	
	@Getter private final de.marcely.pocketcraft.java.component.v8.V8Chunk reference;
	
	static {
		try{
			final @Cleanup InputStream is = Resources.getResourceAsStream("blocks/1.8.json");
			
			BLOCK_STATES_INSTANCE = IDBlockStatesList.load(new Gson().fromJson(new InputStreamReader(is), JsonObject.class));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public V8Chunk(de.marcely.pocketcraft.java.component.v8.V8Chunk ref){
		this.reference = ref;
		
		fetchBlockEntities();
	}
	
	@Override
	public BlockState getBlockState(int x, int y, int z){
		return BLOCK_STATES_INSTANCE.get(this.reference.getBlockId(x, y, z), this.reference.getBlockData(x, y, z));
	}

	@Override
	public PacketFullChunk buildPacket(World world, int x, int z){
		final de.marcely.pocketcraft.bedrock.component.world.Chunk bedrock = new de.marcely.pocketcraft.bedrock.component.world.Chunk();
		
		// sections
		for(int si=0; si<16; si++){
			final V8ChunkSection section = reference.getSections()[si];
			
			if(section == null)
				continue;
			
			for(int ix=0; ix<16; ix++){
				for(int iy=0; iy<16; iy++){
					for(int iz=0; iz<16; iz++){
						final int y = iy+si*16; 
						
						final Pair<Short, Byte> pair = world.getTranslateComponents()
								.toBedrock(new Pair<Short, Byte>(section.getId(ix, iy, iz), section.getData(ix, iy, iz)), TranslateComponents.BLOCK);
						
						bedrock.setBlockId(ix, y, iz, pair.getEntry1());
						bedrock.setBlockData(ix, y, iz, pair.getEntry2());
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
				
				bedrock.setBiome(ix, iz, world.getTranslateComponents().toBedrock(biome, TranslateComponents.BIOME));
			}
		}
		
		// others
		bedrock.setBlockEntities(this.blockEntities);
		
		return bedrock.buildPacket(x, z);
	}
	
	private void fetchBlockEntities(){
		this.blockEntities.clear();
		
		for(int si=0; si<16; si++){
			final V8ChunkSection section = reference.getSections()[si];
			
			if(section == null)
				continue;
			
			for(int ix=0; ix<16; ix++){
				for(int iy=0; iy<16; iy++){
					for(int iz=0; iz<16; iz++){
						final int y = iy+si*16;
						
						V8BlockEntityTranslator.handleSpawn(null, this, ix, y, iz, section.getId(ix, iy, iz), section.getData(ix, iy, iz), null);
					}
				}
			}
		}
	}
}
