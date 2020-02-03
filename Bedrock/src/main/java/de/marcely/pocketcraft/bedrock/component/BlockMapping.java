package de.marcely.pocketcraft.bedrock.component;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

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
	
	private static BlockMapping loadInternal() throws Exception {
		final BlockMapping instance = new BlockMapping();
		
		instance.palette = IOUtils.toByteArray(Resources.getResourceAsStream("runtime_block_states.dat"));
		
		return instance;
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