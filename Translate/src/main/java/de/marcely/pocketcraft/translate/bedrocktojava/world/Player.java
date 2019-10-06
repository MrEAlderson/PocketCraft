package de.marcely.pocketcraft.translate.bedrocktojava.world;

import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.bedrock.server.player.BedrockClient;
import de.marcely.pocketcraft.java.client.JavaClient;
import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.translate.BedrockToJavaTranslator;
import lombok.Getter;
import lombok.Setter;

public class Player {
	
	@Getter private final BedrockToJavaTranslator translator;
	@Getter private final BedrockClient bedrock;
	@Getter private final JavaClient java;
	
	@Getter private final World world = new World();
	
	@Getter @Setter private float x, y, z, yaw, pitch;
	
	public Player(BedrockToJavaTranslator translator, BedrockClient bedrock, JavaClient java){
		this.translator = translator;
		this.bedrock = bedrock;
		this.java = java;
	}
	
	public void sendPacket(Packet packet){
		this.java.sendPacket(packet);
	}
	
	public void sendPacket(PCPacket packet){
		this.bedrock.sendPacket(packet);
	}
	
	public int getEntityId(){
		return (int) this.bedrock.getEntity().getId();
	}
}