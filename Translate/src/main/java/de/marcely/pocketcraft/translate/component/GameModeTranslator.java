package de.marcely.pocketcraft.translate.component;

public class GameModeTranslator {
	
	public static de.marcely.pocketcraft.bedrock.component.GameMode toBedrock(de.marcely.pocketcraft.java.component.GameMode gm){
		return de.marcely.pocketcraft.bedrock.component.GameMode.ofId(gm.getId());
	}
	
	public static de.marcely.pocketcraft.java.component.GameMode toJava(de.marcely.pocketcraft.bedrock.component.GameMode gm){
		return de.marcely.pocketcraft.java.component.GameMode.ofId(gm.getId());
	}
}