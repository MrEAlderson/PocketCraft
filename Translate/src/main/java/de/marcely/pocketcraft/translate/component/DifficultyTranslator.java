package de.marcely.pocketcraft.translate.component;

public class DifficultyTranslator {
	
	public static de.marcely.pocketcraft.bedrock.component.Difficulty toBedrock(de.marcely.pocketcraft.java.component.Difficulty gm){
		return de.marcely.pocketcraft.bedrock.component.Difficulty.ofId(gm.getId());
	}
	
	public static de.marcely.pocketcraft.java.component.Difficulty toJava(de.marcely.pocketcraft.bedrock.component.Difficulty gm){
		return de.marcely.pocketcraft.java.component.Difficulty.ofId(gm.getId());
	}
}
