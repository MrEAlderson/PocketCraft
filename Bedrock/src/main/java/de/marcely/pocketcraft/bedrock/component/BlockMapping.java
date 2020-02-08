package de.marcely.pocketcraft.bedrock.component;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import de.marcely.pocketcraft.bedrock.Resources;
import lombok.Getter;

public class BlockMapping {
	
	public static BlockMapping INSTANCE;
	
	@Getter private byte[] palette;
	@Getter private List<Integer> runtimeIds = new ArrayList<>();
	
	static {
		try{
			INSTANCE = loadInternal();
		}catch(Exception e){
			e.printStackTrace();
		}
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
	
	private static BlockMapping loadInternal() throws Exception {
		final BlockMapping instance = new BlockMapping();
		
		instance.palette = IOUtils.toByteArray(Resources.getResourceAsStream("runtime_block_states.dat"));
		
		return instance;
	}
	
	public static Map<String, Integer> loadIdsMap(){
		final Map<String, Integer> map = new HashMap<>();
		final JsonObject root = new Gson().fromJson(new InputStreamReader(Resources.getResourceAsStream("block_id_map.json")), JsonObject.class);
		
		for(String key:root.keySet())
			map.put(key, root.get(key).getAsInt());
		
		return map;
	}
}