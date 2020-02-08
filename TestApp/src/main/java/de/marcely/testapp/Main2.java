package de.marcely.testapp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Main2 {
	
	public static void main(String[] args) throws Exception {
		final JsonObject root = new JsonObject();
		final JsonObject readRoot = new Gson().fromJson(new InputStreamReader(new FileInputStream("tss.json")), JsonObject.class);
		
		for(String key:readRoot.keySet()){
			final JsonObject entry = new JsonObject();
			final JsonObject readEntry = readRoot.get(key).getAsJsonObject();
			
			entry.addProperty("bedrock_id", key);
			if(readEntry.has("collision") && readEntry.get("collision").isJsonPrimitive())
			entry.addProperty("collision", readEntry.get("collision").getAsString());
			
			root.add(readEntry.get("id").getAsString(), entry);
		}
		
		new Gson().toJson(root, new OutputStreamWriter(new FileOutputStream("asd.json")));
	}
}
