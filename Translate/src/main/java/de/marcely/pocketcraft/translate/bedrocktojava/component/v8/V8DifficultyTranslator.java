package de.marcely.pocketcraft.translate.bedrocktojava.component.v8;

import de.marcely.pocketcraft.bedrock.component.BDifficulty;
import de.marcely.pocketcraft.java.component.v8.V8Difficulty;
import de.marcely.pocketcraft.translate.bedrocktojava.component.ComponentTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;

public class V8DifficultyTranslator implements ComponentTranslator<V8Difficulty, BDifficulty> {

	@Override
	public V8Difficulty toJava(TranslateComponents translate, BDifficulty component){
		return V8Difficulty.getById(component.getId());
	}

	@Override
	public BDifficulty toBedrock(TranslateComponents translate, V8Difficulty component){
		return BDifficulty.getById(component.getId());
	}
}
