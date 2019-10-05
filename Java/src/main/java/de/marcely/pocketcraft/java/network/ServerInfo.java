package de.marcely.pocketcraft.java.network;

import org.jetbrains.annotations.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import de.marcely.pocketcraft.java.component.chat.ChatBaseComponent;
import lombok.Getter;
import lombok.Setter;

public class ServerInfo {
	
	@Getter @Setter public String status;
	@Getter @Setter public long ping;
	
	private DetailedServerInfo detailed;
	
	public ServerInfo(String status, long ping){
		this.status = status;
		this.ping = ping;
	}
	
	public @Nullable DetailedServerInfo getDetailed(){
		if(this.detailed != null)
			return this.detailed;
		
		try{
			this.detailed = DetailedServerInfo.parse(this.status);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return this.detailed;
	}
	
	
	
	public static class DetailedServerInfo {
		
		@Getter private ChatBaseComponent description;
		@Getter private int maxPlayers;
		@Getter private int onlinePlayers;
		@Getter private String versionName;
		@Getter private int protocolVersion;
		
		public static DetailedServerInfo parse(String status) throws Exception {
			final DetailedServerInfo result = new DetailedServerInfo();
			final JsonObject root = new Gson().fromJson(status, JsonObject.class);
			
			result.description = ChatBaseComponent.parse(root.get("description").getAsString());
			
			{
				final JsonObject players = root.getAsJsonObject("players");
				
				result.maxPlayers = players.get("max").getAsInt();
				result.onlinePlayers = players.get("online").getAsInt();
			}
			
			{
				final JsonObject version = root.getAsJsonObject("version");
				
				result.versionName = version.get("name").getAsString();
				result.protocolVersion = version.get("protocol").getAsInt();
			}
			
			return result;
		}
	}
}