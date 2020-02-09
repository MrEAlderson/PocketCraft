package de.marcely.pocketcraft.bedrock.component;

import java.io.InputStreamReader;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import de.marcely.pocketcraft.bedrock.Resources;
import de.marcely.pocketcraft.bedrock.component.nbt.NBTByteBuf;
import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import de.marcely.pocketcraft.bedrock.component.nbt.NBTTag;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValue;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValueCompound;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValueList;
import lombok.Getter;

public class BlockMapping {
	
	public static BlockMapping INSTANCE;
	
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
	
	private static BlockMapping loadInternal() throws Exception {
		final BlockMapping instance = new BlockMapping();
		
		instance.palette = IOUtils.toByteArray(Resources.getResourceAsStream("runtime_block_states.dat"));
		instance.legacyToRuntimeIds = new HashMap<>();
		
		{
			final NBTByteBuf buf = new NBTByteBuf(instance.palette, ByteOrder.LITTLE_ENDIAN, true);
			
			try{
				final NBTTag tag = NBTTag.read(buf);
				final NBTValueList list = (NBTValueList) tag.getValue();
				int runtimeId = -1;
				
				for(NBTValue<?> rawEntry:list.getData()){
					runtimeId++;
					
					final NBTCompound entry = ((NBTValueCompound) rawEntry).getData();
					final NBTTag meta = entry.remove("meta"); // No point in sending this since the client doesn't use it
					
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