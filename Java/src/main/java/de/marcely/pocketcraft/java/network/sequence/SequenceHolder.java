package de.marcely.pocketcraft.java.network.sequence;

import de.marcely.pocketcraft.java.network.packet.Packet;

public interface SequenceHolder {
	
	public abstract void setSequence(Sequence type);
	
	public abstract Sequence getSequence();
	
	public abstract void sendPacket(Packet packet);
	
	public abstract Object getLoginInfo(); // Must be ClientLoginInfo or ServerLoginInfo
	
	public abstract void completeLogin(Object rawResult); // Must be ClentLoginResult or ServerLoginResult
}