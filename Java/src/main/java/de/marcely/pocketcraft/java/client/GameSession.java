package de.marcely.pocketcraft.java.client;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

public class GameSession {
	
	@Getter private final JavaClient client;
	
	@Getter @Setter private String username = "NamelessTee";
	@Getter @Setter private UUID id = UUID.randomUUID();
	
	public GameSession(JavaClient client){
		this.client = client;
	}
}