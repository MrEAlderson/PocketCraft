package de.marcely.pocketcraft.java.client;

import de.marcely.pocketcraft.java.network.ServerInfo;

public interface ClientListener {
	
	public void onReady();
	
	public void onConnect();
	
	public void onDisconnect();
	
	public void onServerInfo(ServerInfo info);
}