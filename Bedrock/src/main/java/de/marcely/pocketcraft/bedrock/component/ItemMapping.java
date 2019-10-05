package de.marcely.pocketcraft.bedrock.component;

import java.io.InputStreamReader;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import de.marcely.pocketcraft.bedrock.Resources;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

public class ItemMapping {
	
	public static ItemMapping INSTANCE;
	
	public int cachedTableSize;
	public byte[] cachedTable;
	
	static {
		try{
			INSTANCE = loadInternal();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static ItemMapping loadInternal() throws Exception {
		final ItemMapping instance = new ItemMapping();
		
		instance.cachedTableSize = 0;
		
		{
			final ByteArrayWriter writer = new ByteArrayWriter();
			final JsonObject statesRoot = new Gson().fromJson(new InputStreamReader(Resources.getResourceAsStream("item_id_map.json")), JsonObject.class);
			
			for(Entry<String, JsonElement> e:statesRoot.entrySet()){
				writer.writeString(e.getKey());
				writer.writeLShort(e.getValue().getAsInt());
				
				instance.cachedTableSize++;
			}
			
			instance.cachedTable = writer.toByteArray();
			
			writer.close();
		}
		
		return instance;
	}
}
