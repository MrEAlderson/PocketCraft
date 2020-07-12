package de.marcely.pocketcraft.translate.bedrocktojava.component.v8;

import de.marcely.pocketcraft.bedrock.component.BGameMode;
import de.marcely.pocketcraft.java.component.v8.V8GameMode;
import de.marcely.pocketcraft.translate.bedrocktojava.component.ComponentTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;

public class V8GameModeTranslator implements ComponentTranslator<V8GameMode, BGameMode> {
	
	@Override
	public V8GameMode toJava(TranslateComponents translate, BGameMode component){
		return V8GameMode.getById(component.getId());
	}

	@Override
	public BGameMode toBedrock(TranslateComponents translate, V8GameMode component){
		return BGameMode.getById(component.getId());
	}
}