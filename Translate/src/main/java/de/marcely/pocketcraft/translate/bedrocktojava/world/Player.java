package de.marcely.pocketcraft.translate.bedrocktojava.world;

import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.bedrock.server.player.BedrockClient;
import de.marcely.pocketcraft.java.client.JavaClient;
import de.marcely.pocketcraft.java.network.packet.Packet;
import lombok.Getter;

public class Player {
	
	@Getter private final BedrockClient bedrock;
	@Getter private final JavaClient java;
	
	@Getter private final World world = new World();
	
	public Player(BedrockClient bedrock, JavaClient java){
		this.bedrock = bedrock;
		this.java = java;
	}
	
	public void sendPacket(Packet packet){
		this.java.sendPacket(packet);
	}
	
	public void sendPacket(PCPacket packet){
		this.bedrock.sendPacket(packet);
	}
}