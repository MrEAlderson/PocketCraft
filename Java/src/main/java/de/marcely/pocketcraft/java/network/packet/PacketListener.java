package de.marcely.pocketcraft.java.network.packet;

public interface PacketListener {
	
	/**
	 * 
	 * @return Returning false causes the packet to not being handled further
	 */
	public boolean onSend(Packet packet);
	
	/**
	 * 
	 * @return Returning false causes the packet to not being handled further
	 */
	public boolean onReceive(Packet packet);
}