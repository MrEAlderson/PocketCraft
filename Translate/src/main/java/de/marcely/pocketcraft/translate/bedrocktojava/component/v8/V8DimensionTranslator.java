package de.marcely.pocketcraft.translate.bedrocktojava.component.v8;

import de.marcely.pocketcraft.bedrock.component.Dimension;
import de.marcely.pocketcraft.java.component.v8.V8Dimension;
import de.marcely.pocketcraft.translate.bedrocktojava.component.ComponentTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;

public class V8DimensionTranslator implements ComponentTranslator<V8Dimension, Dimension> {

	@Override
	public V8Dimension toJava(TranslateComponents translate, Dimension component){
		switch(component){
		case OVERWORLD:
			return V8Dimension.OVERWORLD;
		
		case NETHER:
			return V8Dimension.NETHER;
			
		case THE_END:
			return V8Dimension.THE_END;
			
		default:
			return null;
		}
	}

	@Override
	public Dimension toBedrock(TranslateComponents translate, V8Dimension component){
		switch(component){
		case OVERWORLD:
			return Dimension.OVERWORLD;
		
		case NETHER:
			return Dimension.NETHER;
			
		case THE_END:
			return Dimension.THE_END;
			
		default:
			return null;
		}
	}
}
