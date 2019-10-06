package de.marcely.pocketcraft.translate.component;

public class DimensionTranslator {
	
	public static de.marcely.pocketcraft.bedrock.component.Dimension toBedrock(de.marcely.pocketcraft.java.component.Dimension gm){
		switch(gm){
		case NETHER:
			return de.marcely.pocketcraft.bedrock.component.Dimension.NETHER;
			
		case OVERWORLD:
			return de.marcely.pocketcraft.bedrock.component.Dimension.OVERWORLD;
			
		case END:
			return de.marcely.pocketcraft.bedrock.component.Dimension.END;
		}
		
		return null;
	}
	
	public static de.marcely.pocketcraft.java.component.Dimension toJava(de.marcely.pocketcraft.bedrock.component.Dimension gm){
		switch(gm){
		case NETHER:
			return de.marcely.pocketcraft.java.component.Dimension.NETHER;
			
		case OVERWORLD:
			return de.marcely.pocketcraft.java.component.Dimension.OVERWORLD;
			
		case END:
			return de.marcely.pocketcraft.java.component.Dimension.END;
		}
		
		return null;
	}
}
