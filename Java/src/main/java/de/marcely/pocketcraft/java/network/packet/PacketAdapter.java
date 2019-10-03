package de.marcely.pocketcraft.java.network.packet;

public interface PacketAdapter extends PacketListener {
	
	/**
	 * 
	 * @return Returning false causes the packet to not being handled further
	 */
	public default boolean onSend(Packet packet){ return true; }
	
	/**
	 * 
	 * @return Returning false causes the packet to not being handled further
	 */
	public default boolean onReceive(Packet packet){ return true; }
}
