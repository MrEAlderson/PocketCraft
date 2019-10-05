package de.marcely.pocketcraft.bedrock.server;

import de.marcely.pocketcraft.bedrock.server.player.Player;

public interface ServerAdapter extends ServerListener {
	
	public default void onServerInfoRequest(ServerInfoRequest request){ }
	
	public default void onConnect(Player player){ }
	
	public default void onDisconnect(Player player){ }
}