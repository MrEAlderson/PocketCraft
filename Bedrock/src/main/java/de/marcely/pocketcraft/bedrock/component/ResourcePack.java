package de.marcely.pocketcraft.bedrock.component;

public class ResourcePack {
	
	public final String name, id, version;
	public final byte[] data;
	
	public ResourcePack(String name, String id, String version, byte[] data){
		this.name = name;
		this.id = id;
		this.version = version;
		this.data = data;
	}
}
