package de.marcely.pocketcraft.translate.component;

public class ItemTranslator {
	
	public static de.marcely.pocketcraft.bedrock.component.inventory.Item toBedrock(de.marcely.pocketcraft.java.component.Item item){
		if(item == null)
			return new de.marcely.pocketcraft.bedrock.component.inventory.Item(0);
		
		return new de.marcely.pocketcraft.bedrock.component.inventory.Item(item.getType(), item.getAmount(), item.getData());
	}
	
	public static de.marcely.pocketcraft.java.component.Item toJava(de.marcely.pocketcraft.bedrock.component.inventory.Item item){
		if(item.id == 0)
			return null;
		
		return new de.marcely.pocketcraft.java.component.Item((short) item.id, item.amount, (short) item.data);
	}
}
