package de.marcely.pocketcraft.translate.bedrocktojava.bedrock;

import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityAnimate;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientAnimation;
import de.marcely.pocketcraft.translate.bedrocktojava.BedrockPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TPacketEntityAnimate extends BedrockPacketTranslator<PacketEntityAnimate> {

	@Override
	public void handle(PacketEntityAnimate packet, Player player){
		if(packet.type == PacketEntityAnimate.TYPE_SWING_ARM){
			player.sendPacket(new V8D9PacketPlayClientAnimation());
		}
	}
}
