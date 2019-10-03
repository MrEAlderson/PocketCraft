package de.marcely.pocketcraft.java.client;

public interface ClientListener {
	
	public abstract void onReady();
	
	public abstract void onConnect();
	
	public abstract void onDisconnect();
}