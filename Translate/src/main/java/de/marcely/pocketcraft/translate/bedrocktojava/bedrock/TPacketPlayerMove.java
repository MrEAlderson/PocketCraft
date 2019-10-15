package de.marcely.pocketcraft.translate.bedrocktojava.bedrock;

import de.marcely.pocketcraft.bedrock.network.packet.PacketPlayerMove;
import de.marcely.pocketcraft.translate.bedrocktojava.BedrockPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TPacketPlayerMove extends BedrockPacketTranslator<PacketPlayerMove> {

	@Override
	public void handle(PacketPlayerMove packet, Player player){
		player.setX(packet.posX);
		player.setY(packet.posY-1.62F /* eye height */);
		player.setZ(packet.posZ);
		player.setYaw(packet.yaw);
		player.setPitch(packet.pitch);
		player.setOnGround(packet.onGround);
	}
}
