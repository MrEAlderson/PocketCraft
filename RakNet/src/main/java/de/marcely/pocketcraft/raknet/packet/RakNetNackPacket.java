package de.marcely.pocketcraft.raknet.packet;

/**
 * 
 * @author Marcel S.
 * @date 02.12.2017 (dd/MM/yyyy)
 * @website https://marcely.de/
 */
public class RakNetNackPacket extends RakNetAckPacket {
	
	public RakNetNackPacket(){
		super(RakNetPacket.TYPE_NACK);
	}
}
