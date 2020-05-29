package de.marcely.pocketcraft.translate.bedrocktojava.component.v8;

import de.marcely.pocketcraft.translate.bedrocktojava.component.ComponentTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;
import de.marcely.pocketcraft.translate.bedrocktojava.world.block.BlockState;
import de.marcely.pocketcraft.translate.bedrocktojava.world.v8.V8Chunk;
import de.marcely.pocketcraft.utils.Pair;

public class V8BlockTranslator implements ComponentTranslator<Pair<Short, Byte>, Pair<Short, Byte>> {
	
	// TODO
	@Override
	public Pair<Short, Byte> toJava(TranslateComponents translate, Pair<Short, Byte> component){
		return null;
	}

	@Override
	public Pair<Short, Byte> toBedrock(TranslateComponents translate, Pair<Short, Byte> pair){
		final BlockState state = V8Chunk.BLOCK_STATES_INSTANCE.get(pair.getEntry1(), pair.getEntry2());
		
		return state != null ? new Pair<>(state.getBedrockId(), state.getBedrockData()) : new Pair<>((short) 248 /* error block */, (byte) 0);
	}
}