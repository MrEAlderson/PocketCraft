package de.marcely.pocketcraft.translate.component;

public class BlockFaceTranslator {
	
	public static de.marcely.pocketcraft.bedrock.component.BlockFace sideToBedrock(de.marcely.pocketcraft.java.component.BlockFace face){
		switch(face){
		case NORTH:
			return de.marcely.pocketcraft.bedrock.component.BlockFace.NORTH;
			
		case WEST:
			return de.marcely.pocketcraft.bedrock.component.BlockFace.WEST;
			
		case SOUTH:
			return de.marcely.pocketcraft.bedrock.component.BlockFace.SOUTH;
			
		case EAST:
			return de.marcely.pocketcraft.bedrock.component.BlockFace.EAST;
			
		default:
			return null;
		}
	}
}