package de.marcely.pocketcraft.bedrock.component;

import java.io.InputStreamReader;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import de.marcely.pocketcraft.bedrock.Resources;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

public class BlockMapping {
	
	public static BlockMapping INSTANCE;
	
	public int cachedTableSize;
	public byte[] cachedTable;
	
	static {
		try{
			INSTANCE = loadInternal();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static BlockMapping loadInternal() throws Exception {
		final BlockMapping instance = new BlockMapping();
		
		instance.cachedTableSize = 0;
		
		{
			final ByteArrayWriter writer = new ByteArrayWriter();
			final JsonObject idsRoot = (JsonObject) new JsonParser().parse(new InputStreamReader(Resources.getResourceAsStream("block_id_map.json")));
			final JsonObject statesRoot = (JsonObject) new JsonParser().parse(new InputStreamReader(Resources.getResourceAsStream("required_block_states.json")));
			
			for(Entry<String, JsonElement> e1:statesRoot.entrySet()){
				for(Entry<String, JsonElement> e2:((JsonObject) e1.getValue()).entrySet()){
					final String name = e1.getKey() + ":" + e2.getKey();
					
					for(JsonElement rawState:(JsonArray) e2.getValue()){
						final short state = rawState.getAsShort();
						
						writer.writeString(name);
						writer.writeLShort(state);
						writer.writeLShort(idsRoot.get(name).getAsInt());
						
						instance.cachedTableSize++;
					}
				}
			}
			
			instance.cachedTable = writer.toByteArray();
			
			writer.close();
		}
		
		return instance;
	}
}