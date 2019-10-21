package de.marcely.pocketcraft.translate.bedrocktojava.component.v8;

import de.marcely.pocketcraft.bedrock.component.world.Biome;
import de.marcely.pocketcraft.java.component.v8.V8Biome;
import de.marcely.pocketcraft.translate.bedrocktojava.component.ComponentTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;

public class V8BiomeTranslator implements ComponentTranslator<V8Biome, Biome> {
	
	@Override
	public V8Biome toJava(TranslateComponents translate, Biome biome){
		final V8Biome res = V8Biome.getById(biome.getId());
		
		return res != null ? res : V8Biome.OCEAN;
	}

	@Override
	public Biome toBedrock(TranslateComponents translate, V8Biome biome){
		final Biome res = Biome.getById(biome.getId());
		
		return res != null ? res : Biome.OCEAN;
	}
}