package de.marcely.pocketcraft.bedrock.component;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import de.marcely.pocketcraft.bedrock.Resources;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;
import lombok.Getter;

public class BlockMapping {
	
	public static BlockMapping INSTANCE;
	
	@Getter private byte[] cachedTable;
	@Getter private List<Integer> runtimeIds = new ArrayList<>();
	
	static {
		try{
			INSTANCE = loadInternal();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static BlockMapping loadInternal() throws Exception {
		final BlockMapping instance = new BlockMapping();
		
		instance.runtimeIds.clear();
		
		{
			final ByteArrayWriter writer = new ByteArrayWriter();
			final JsonObject idsRoot = new Gson().fromJson(new InputStreamReader(Resources.getResourceAsStream("block_id_map.json")), JsonObject.class);
			final JsonObject statesRoot = new Gson().fromJson(new InputStreamReader(Resources.getResourceAsStream("required_block_states.json")), JsonObject.class);
			
			for(Entry<String, JsonElement> e1:statesRoot.entrySet()){
				for(Entry<String, JsonElement> e2:((JsonObject) e1.getValue()).entrySet()){
					final String name = e1.getKey() + ":" + e2.getKey();
					
					for(JsonElement rawState:(JsonArray) e2.getValue()){
						final byte data = rawState.getAsByte();
						final short id = idsRoot.get(name).getAsShort();
						
						writer.writeString(name);
						writer.writeLShort(data);
						writer.writeLShort(id);
						
						instance.runtimeIds.add((id << 4) | data);
					}
				}
			}
			
			instance.cachedTable = writer.toByteArray();
			
			writer.close();
		}
		
		return instance;
	}
	
	public int getCachedTableSize(){
		return this.runtimeIds.size();
	}
	
	/**
	 * 
	 * @return Returns -1 if it hasn't been found
	 */
	public int getRuntimeId(int legacyId){
		return this.runtimeIds.indexOf(legacyId);
	}
	
	/**
	 * 
	 * @return Returns -1 if it hasn't been found
	 */
	public int getRuntimeId(short id, byte data){
		return getRuntimeId((id << 4) | data);
	}
}