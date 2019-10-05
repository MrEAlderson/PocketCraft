package de.marcely.pocketcraft.bedrock.server;

import de.marcely.pocketcraft.bedrock.server.player.BedrockClient;

public interface ServerAdapter extends ServerListener {
	
	public default void onServerInfoRequest(ServerInfoRequest request){ }
	
	public default void onConnect(BedrockClient player){ }
	
	public default void onDisconnect(BedrockClient player){ }
}