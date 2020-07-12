package de.marcely.pocketcraft.translate.bedrocktojava.component.v8;

import de.marcely.pocketcraft.bedrock.component.inventory.item.BItem;
import de.marcely.pocketcraft.java.component.Item;
import de.marcely.pocketcraft.translate.bedrocktojava.component.ComponentTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;

public class V8ItemTranslator implements ComponentTranslator<Item, BItem> {
	
	@Override
	public Item toJava(TranslateComponents translate, BItem item){
		if(item.getType() == 0)
			return null;
		
		return new Item((short) item.getType(), item.getAmount(), (short) item.getData());
	}

	@Override
	public BItem toBedrock(TranslateComponents translate, Item item){
		if(item == null)
			return new BItem(0);
		
		return new BItem(item.getType(), item.getAmount(), item.getData());
	}
}
