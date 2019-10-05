package de.marcely.pocketcraft.java.client;

import de.marcely.pocketcraft.java.network.ServerInfo;

public interface ClientAdapter extends ClientListener {
	
	public default void onReady(){ }
	
	public default void onConnect(){ }
	
	public default void onDisconnect(){ }
	
	public default void onServerInfo(ServerInfo info){ }
}
