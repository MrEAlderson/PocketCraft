package de.marcely.pocketcraft.translate.bedrocktojava.packet;

import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public abstract class BedrockPacketTranslator<T extends PCPacket> {
	
	public abstract void handle(T packet, Player player);

	
	@SuppressWarnings("unchecked")
	public void handle0(PCPacket packet, Player player){
		handle((T) packet, player);
	}
}