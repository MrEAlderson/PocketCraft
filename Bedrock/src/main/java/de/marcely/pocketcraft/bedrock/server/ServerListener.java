package de.marcely.pocketcraft.bedrock.server;

import de.marcely.pocketcraft.bedrock.server.player.BedrockClient;

public interface ServerListener {
	
	public void onServerInfoRequest(ServerInfoRequest request);
	
	public void onConnect(BedrockClient player);
	
	public void onDisconnect(BedrockClient player);
}