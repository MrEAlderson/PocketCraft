package de.marcely.pocketcraft.translate.world;

import de.marcely.pocketcraft.utils.Pair;

public class V8BlockTranslator {
	
	public static Pair<Short, Byte> toBedrock(short id, byte data){
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