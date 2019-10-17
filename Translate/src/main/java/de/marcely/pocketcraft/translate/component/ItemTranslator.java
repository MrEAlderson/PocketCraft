package de.marcely.pocketcraft.translate.component;

public class ItemTranslator {
	
	public static de.marcely.pocketcraft.bedrock.component.inventory.Item toBedrock(de.marcely.pocketcraft.java.component.Item item){
		return new de.marcely.pocketcraft.bedrock.component.inventory.Item(item.getType(), item.getAmount(), item.getData());
	}
}
