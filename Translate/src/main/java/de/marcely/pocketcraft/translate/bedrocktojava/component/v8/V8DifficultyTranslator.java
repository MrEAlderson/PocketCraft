package de.marcely.pocketcraft.translate.bedrocktojava.component.v8;

import de.marcely.pocketcraft.bedrock.component.Difficulty;
import de.marcely.pocketcraft.java.component.v8.V8Difficulty;
import de.marcely.pocketcraft.translate.bedrocktojava.component.ComponentTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;

public class V8DifficultyTranslator implements ComponentTranslator<V8Difficulty, Difficulty> {

	@Override
	public V8Difficulty toJava(TranslateComponents translate, Difficulty component){
		return V8Difficulty.getById(component.getId());
	}

	@Override
	public Difficulty toBedrock(TranslateComponents translate, V8Difficulty component){
		return Difficulty.getById(component.getId());
	}
}
