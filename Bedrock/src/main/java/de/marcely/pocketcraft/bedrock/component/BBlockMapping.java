package de.marcely.pocketcraft.bedrock.component;

import java.io.InputStreamReader;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import de.marcely.pocketcraft.bedrock.Resources;
import de.marcely.pocketcraft.bedrock.component.nbt.BNBTByteBuf;
import de.marcely.pocketcraft.bedrock.component.nbt.BNBTCompound;
import de.marcely.pocketcraft.bedrock.component.nbt.BNBTTag;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTValue;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTValueCompound;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTValueList;
import lombok.Getter;

public class BBlockMapping {
	
	public static BBlockMapping INSTANCE;
	
	@Getter private byte[] palette;
	@Getter private Map<Integer, Integer> legacyToRuntimeIds;
	
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
		final Integer runtimeId = this.legacyToRuntimeIds.get(legacyId);
		
		return runtimeId != null ? runtimeId : -1;
	}
	
	/**
	 * 
	 * @return Returns -1 if it hasn't been found
	 */
	public int getRuntimeId(int id, int data){
		return getRuntimeId((id << 6) | (data & 0xF));
	}
	
	private static BBlockMapping loadInternal() throws Exception {
		final BBlockMapping instance = new BBlockMapping();
		
		instance.palette = IOUtils.toByteArray(Resources.getResourceAsStream("runtime_block_states.dat"));
		instance.legacyToRuntimeIds = new HashMap<>();
		
		{
			final BNBTByteBuf buf = new BNBTByteBuf(instance.palette, ByteOrder.LITTLE_ENDIAN, true);
			
			try{
				final BNBTTag tag = BNBTTag.read(buf);
				final BNBTValueList list = (BNBTValueList) tag.getValue();
				int runtimeId = -1;
				
				for(BNBTValue<?> rawEntry:list.getData()){
					runtimeId++;
					
					final BNBTCompound entry = ((BNBTValueCompound) rawEntry).getData();
					final BNBTTag meta = entry.remove("meta"); // No point in sending this since the client doesn't use it
					
					if(meta == null)
						continue;
					
					final int id = entry.getShort("id");
					
					for(int m:(int[]) meta.getValueData())
						instance.legacyToRuntimeIds.put(id << 6 | m, runtimeId);
				}
				
				instance.palette = tag.write(ByteOrder.LITTLE_ENDIAN, true);
			}finally{
				buf.release();
			}
		}
		
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