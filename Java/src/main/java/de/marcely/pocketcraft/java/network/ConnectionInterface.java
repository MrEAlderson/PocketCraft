package de.marcely.pocketcraft.java.network;

import de.marcely.pocketcraft.java.network.protocol.Protocol;
import de.marcely.pocketcraft.java.network.sequence.Sequence;

public interface ConnectionInterface {
	
	public void onReady();
	
	public void onClose();
	
	public Sequence getSequence();
	
	public Protocol getProtocol();
}