package de.marcely.pocketcraft.java.network.packet;

import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

public class PacketProperties {
	
	private Map<Integer, Integer> ids = new HashMap<>(4);
	private Map<String, Object> metadata = new HashMap<>();	
	
	public @Nullable Integer getId(int protocolVersion, byte source){
		return this.ids.get((protocolVersion << 1) | source);
	}
	
	public @Nullable Object getMetadata(String key){
		return this.metadata.get(key);
	}
	
	@SuppressWarnings("unchecked")
	public @Nullable <T>T getMetadata(String key, Class<T> clazz){
		return (T) getMetadata(key);
	}
	
	public void setId(int protocolVersion, int id, byte source){
		this.ids.put((protocolVersion << 1) | source, id);
	}
	
	public void setMetadata(String key, Object value){
		this.metadata.put(key, value);
	}
}