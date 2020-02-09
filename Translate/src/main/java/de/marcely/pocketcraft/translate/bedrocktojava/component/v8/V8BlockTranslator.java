package de.marcely.pocketcraft.translate.bedrocktojava.component.v8;

import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import de.marcely.pocketcraft.translate.Resources;
import de.marcely.pocketcraft.translate.bedrocktojava.component.ComponentTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;
import de.marcely.pocketcraft.translate.bedrocktojava.world.block.BlockState;
import de.marcely.pocketcraft.translate.bedrocktojava.world.block.IDBlockStatesList;
import de.marcely.pocketcraft.translate.bedrocktojava.world.v8.V8Chunk;
import de.marcely.pocketcraft.utils.Pair;
import lombok.Cleanup;

public class V8BlockTranslator implements ComponentTranslator<Pair<Short, Byte>, Pair<Short, Byte>> {
	
	// TODO
	@Override
	public Pair<Short, Byte> toJava(TranslateComponents translate, Pair<Short, Byte> component){
		return null;
	}

	@Override
	public Pair<Short, Byte> toBedrock(TranslateComponents translate, Pair<Short, Byte> pair){
		/*final short id = pair.getEntry1();
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
		*/
		final BlockState state = V8Chunk.BLOCK_STATES_INSTANCE.get(pair.getEntry1(), pair.getEntry2());
		
		return state != null ? new Pair<>(state.getBedrockId(), state.getBedrockData()) : new Pair<>((short) 248 /* error block */, (byte) 0);
	}
}