package de.marcely.pocketcraft.translate.bedrocktojava.component.v8;

import de.marcely.pocketcraft.bedrock.component.inventory.item.BItem;
import de.marcely.pocketcraft.java.component.v8.item.V8Item;
import de.marcely.pocketcraft.java.component.v8.item.V8ItemMeta;
import de.marcely.pocketcraft.translate.bedrocktojava.component.ComponentTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;

public class V8ItemTranslator implements ComponentTranslator<V8Item, BItem> {
	
	@Override
	public V8Item toJava(TranslateComponents translate, BItem item){
		if(item.getType() == 0)
			return null;
		
		return new V8Item(item.getType(), item.getAmount(), item.getDurability(), (V8ItemMeta) null);
	}

	@Override
	public BItem toBedrock(TranslateComponents translate, V8Item item){
		if(item == null)
			return new BItem(0);
		
		return new BItem(item.getType(), item.getAmount(), item.getDurability());
	}
}
