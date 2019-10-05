package de.marcely.pocketcraft.java.network.sequence;

import de.marcely.pocketcraft.java.network.Connection;
import de.marcely.pocketcraft.java.network.packet.Packet;

public interface SequenceHolder {
	
	public abstract void sendPacket(Packet packet);
	
	public abstract Connection getConnection();
}