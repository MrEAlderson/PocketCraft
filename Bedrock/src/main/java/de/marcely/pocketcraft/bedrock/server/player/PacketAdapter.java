package de.marcely.pocketcraft.bedrock.server.player;

import com.whirvis.jraknet.protocol.message.EncapsulatedPacket;

import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;

public interface PacketAdapter extends PacketListener {
	
	/**
	 * 
	 * @return Returning false causes the packet to not being handled further
	 */
	public default boolean onSend(PCPacket packet){ return true; }
	
	/**
	 * 
	 * @return Returning false causes the packet to not being handled further
	 */
	public default boolean onReceive(PCPacket packet){ return true; }
	
	/**
	 * 
	 * Gets called when the client received our packet
	 */
	public default void onAcknowledge(EncapsulatedPacket packet){ }
}
