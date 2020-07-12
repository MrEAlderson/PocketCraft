package de.marcely.pocketcraft.translate.bedrocktojava.component.v8;

import de.marcely.pocketcraft.translate.bedrocktojava.component.ComponentTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;

public class V8ItemTranslator implements ComponentTranslator<de.marcely.pocketcraft.java.component.Item, de.marcely.pocketcraft.bedrock.component.inventory.item.Item> {
	
	@Override
	public de.marcely.pocketcraft.java.component.Item toJava(TranslateComponents translate, de.marcely.pocketcraft.bedrock.component.inventory.item.Item item){
		if(item.id == 0)
			return null;
		
		return new de.marcely.pocketcraft.java.component.Item((short) item.id, item.amount, (short) item.data);
	}

	@Override
	public de.marcely.pocketcraft.bedrock.component.inventory.item.Item toBedrock(TranslateComponents translate, de.marcely.pocketcraft.java.component.Item item){
		if(item == null)
			return new de.marcely.pocketcraft.bedrock.component.inventory.item.Item(0);
		
		return new de.marcely.pocketcraft.bedrock.component.inventory.item.Item(item.getType(), item.getAmount(), item.getData());
	}
}
