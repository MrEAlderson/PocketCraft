package de.marcely.pocketcraft.bedrock.server.player;

import com.whirvis.jraknet.protocol.message.EncapsulatedPacket;

import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;

public interface PacketListener {
	
	/**
	 * 
	 * @return Returning false causes the packet to not being handled further
	 */
	public boolean onSend(PCPacket packet);
	
	/**
	 * 
	 * @return Returning false causes the packet to not being handled further
	 */
	public boolean onReceive(PCPacket packet);
	
	/**
	 * 
	 * Gets called when the client received our packet
	 */
	public void onAcknowledge(EncapsulatedPacket packet);
}