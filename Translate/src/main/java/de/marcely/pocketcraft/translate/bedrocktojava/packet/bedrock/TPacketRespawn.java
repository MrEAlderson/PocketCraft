package de.marcely.pocketcraft.translate.bedrocktojava.packet.bedrock;

import de.marcely.pocketcraft.bedrock.network.packet.PacketRespawn;
import de.marcely.pocketcraft.translate.bedrocktojava.BedrockPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TPacketRespawn extends BedrockPacketTranslator<PacketRespawn> {

	@Override
	public void handle(PacketRespawn packet, Player player){
		if(packet.state == PacketRespawn.STATE_CLIENT_READY_TO_SPAWN){
			System.out.println("REEESPAWN " + packet.state);
		}
	}
}
