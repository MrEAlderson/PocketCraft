package de.marcely.pocketcraft.translate.bedrocktojava.component.v8;

import de.marcely.pocketcraft.bedrock.component.BlockFace;
import de.marcely.pocketcraft.java.component.v8.V8BlockFace;
import de.marcely.pocketcraft.translate.bedrocktojava.component.ComponentTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;

public class V8BlockFaceTranslator implements ComponentTranslator<V8BlockFace, BlockFace> {
	
	@Override
	public V8BlockFace toJava(TranslateComponents translate, BlockFace face){
		return V8BlockFace.getById(face.getId());
	}

	@Override
	public BlockFace toBedrock(TranslateComponents translate, V8BlockFace face){
		return BlockFace.getById(face.getId());
	}
}