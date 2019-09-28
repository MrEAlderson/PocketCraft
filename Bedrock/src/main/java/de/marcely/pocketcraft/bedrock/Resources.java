package de.marcely.pocketcraft.bedrock;

import java.io.InputStream;

public class Resources {
	
	public static InputStream getResourceAsStream(String name){
		return Resources.class.getResourceAsStream("/data/" + name);
	}
}