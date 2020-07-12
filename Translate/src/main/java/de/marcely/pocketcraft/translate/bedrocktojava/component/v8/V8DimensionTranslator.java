package de.marcely.pocketcraft.translate.bedrocktojava.component.v8;

import de.marcely.pocketcraft.bedrock.component.BDimension;
import de.marcely.pocketcraft.java.component.v8.V8Dimension;
import de.marcely.pocketcraft.translate.bedrocktojava.component.ComponentTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;

public class V8DimensionTranslator implements ComponentTranslator<V8Dimension, BDimension> {

	@Override
	public V8Dimension toJava(TranslateComponents translate, BDimension component){
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
	public BDimension toBedrock(TranslateComponents translate, V8Dimension component){
		switch(component){
		case OVERWORLD:
			return BDimension.OVERWORLD;
		
		case NETHER:
			return BDimension.NETHER;
			
		case THE_END:
			return BDimension.THE_END;
			
		default:
			return null;
		}
	}
}
