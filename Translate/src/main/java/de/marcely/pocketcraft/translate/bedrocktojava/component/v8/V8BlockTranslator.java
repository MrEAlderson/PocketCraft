package de.marcely.pocketcraft.translate.bedrocktojava.component.v8;

import de.marcely.pocketcraft.translate.bedrocktojava.component.ComponentTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;
import de.marcely.pocketcraft.utils.Pair;

public class V8BlockTranslator implements ComponentTranslator<Pair<Short, Byte>, Pair<Short, Byte>> {
	
	// TODO
	@Override
	public Pair<Short, Byte> toJava(TranslateComponents translate, Pair<Short, Byte> component){
		return null;
	}

	@Override
	public Pair<Short, Byte> toBedrock(TranslateComponents translate, Pair<Short, Byte> pair){
		final short id = pair.getEntry1();
		byte data = pair.getEntry2();
		
		switch(id){
		// piston
		case 29:
		case 33:
		case 34:
		{
			switch(data){
			case 2:
				data = 3;
				break;
				
			case 3:
				data = 2;
				break;
				
			case 4:
				data = 5;
				break;
				
			case 5:
				data = 4;
				break;
			}
		}
		break;
		}
		
		return new Pair<>(id, data);
	}
}