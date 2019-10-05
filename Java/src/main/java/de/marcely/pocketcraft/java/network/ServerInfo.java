package de.marcely.pocketcraft.java.network;

import lombok.Getter;
import lombok.Setter;

public class ServerInfo {
	
	@Getter @Setter public String status;
	@Getter @Setter public long ping;
	
	public ServerInfo(String status, long ping){
		this.status = status;
		this.ping = ping;
	}
}