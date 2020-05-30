package de.marcely.pocketcraft.java.network.sequence;

import de.marcely.pocketcraft.java.network.ServerInfo;

public interface ClientSequenceHolder extends SequenceHolder {
	
	public abstract void setSequence(Sequence<ClientSequenceHolder> type);
	
	public abstract Sequence<ClientSequenceHolder> getSequence();
	
	public abstract ClientLoginInfo getLoginInfo();
	
	public abstract void completeLogin(ClientLoginResult rawResult);
	
	public abstract void onServerInfo(ServerInfo info);
}