package de.marcely.pocketcraft.translate.world;

import de.marcely.pocketcraft.utils.Pair;

public class V8BlockTranslator {
	
	public static Pair<Short, Byte> toBedrock(short id, byte data){
		return new Pair<>(id, data);
	}
}