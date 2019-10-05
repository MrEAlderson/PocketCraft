package de.marcely.pocketcraft.bedrock.server;

import de.marcely.pocketcraft.bedrock.server.player.Player;

public interface ServerListener {
	
	public void onServerInfoRequest(ServerInfoRequest request);
	
	public void onConnect(Player player);
	
	public void onDisconnect(Player player);
}