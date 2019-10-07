package de.marcely.pocketcraft.translate.world;

import de.marcely.pocketcraft.bedrock.world.Biome;
import de.marcely.pocketcraft.java.component.v8.V8Biome;

public class V8BiomeTranslator {
	
	public static Biome toBedrock(V8Biome biome){
		final Biome res = Biome.getById(biome.getId());
		
		return res != null ? res : Biome.OCEAN;
	}
}