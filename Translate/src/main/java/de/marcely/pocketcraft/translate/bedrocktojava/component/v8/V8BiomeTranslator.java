package de.marcely.pocketcraft.translate.bedrocktojava.component.v8;

import de.marcely.pocketcraft.bedrock.component.world.BBiome;
import de.marcely.pocketcraft.java.component.v8.V8Biome;
import de.marcely.pocketcraft.translate.bedrocktojava.component.ComponentTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;

public class V8BiomeTranslator implements ComponentTranslator<V8Biome, BBiome> {
	
	@Override
	public V8Biome toJava(TranslateComponents translate, BBiome biome){
		final V8Biome res = V8Biome.getById(biome.getId());
		
		return res != null ? res : V8Biome.OCEAN;
	}

	@Override
	public BBiome toBedrock(TranslateComponents translate, V8Biome biome){
		final BBiome res = BBiome.getById(biome.getId());
		
		return res != null ? res : BBiome.OCEAN;
	}
}