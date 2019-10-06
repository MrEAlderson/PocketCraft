package de.marcely.pocketcraft.bedrock.network.packet;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

public class PacketProperties {

	private Map<String, Object> metadata = new HashMap<>();	
	
	public @Nullable Object getMetadata(String key){
		return this.metadata.get(key);
	}
	
	public void setMetadata(String key, Object value){
		this.metadata.put(key, value);
	}
}