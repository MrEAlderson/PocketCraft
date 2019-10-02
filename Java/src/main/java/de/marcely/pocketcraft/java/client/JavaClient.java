package de.marcely.pocketcraft.java.client;

import de.marcely.pocketcraft.java.PacketStream;
import lombok.Getter;

public class JavaClient {
	
	@Getter private final PacketStream stream;
	
	public JavaClient(PacketStream stream){
		this.stream = stream;
	}
}