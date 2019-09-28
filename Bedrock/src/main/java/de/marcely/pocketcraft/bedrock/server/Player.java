package de.marcely.pocketcraft.bedrock.server;

import de.marcely.pocketcraft.raknet.server.Client;
import lombok.Getter;

public class Player {
	
	@Getter private final Client client;
	
	public Player(Client client){
		this.client = client;
	}
	
	
}
