package de.marcely.pocketcraft.java.network.packet;

import java.util.HashMap;
import java.util.Map;

public class PacketProperties {
	
	private Map<Integer, Integer> ids = new HashMap<>(4);
	
	public Integer getId(int protocolVersion){
		return this.ids.get(protocolVersion);
	}
	
	public void setId(int protocolVersion, int id){
		this.ids.put(protocolVersion, id);
	}
}