package de.marcely.pocketcraft.bedrock.server;

import java.io.IOException;

import de.marcely.pocketcraft.raknet.packet.encapsulated.RakNetEncapsulatedPacket;
import de.marcely.pocketcraft.raknet.server.Client;
import de.marcely.pocketcraft.raknet.server.RakNetServer;
import lombok.Getter;

public class BedrockServer {
	
	@Getter private RakNetServer rakNet;
	
	public BedrockServer(int port){
		this.rakNet = new RakNetServer(port){
			public void onConnect(Client client){
				
			}
			
			public void onDisconnect(Client client, String reason){
				
			}
			
			public void handleEncapsulated(Client client, RakNetEncapsulatedPacket packet, int flags) throws IOException {
				
			}
			
			public String getName(){
				return "test";
			}
		};
	}
	
	public int getPort(){
		return this.rakNet.getPort();
	}
}