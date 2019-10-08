package de.marcely.pocketcraft.translate.bedrocktojava.packet.bedrock;

import de.marcely.pocketcraft.bedrock.network.packet.PacketChunkRadiusChange;
import de.marcely.pocketcraft.bedrock.network.packet.PacketChunkRadiusChangeRequest;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.BedrockPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TPacketChunkRadiusChangeRequest extends BedrockPacketTranslator<PacketChunkRadiusChangeRequest> {

	@Override
	public void handle(PacketChunkRadiusChangeRequest packet, Player player){
		final PacketChunkRadiusChange out = new PacketChunkRadiusChange();
		
		player.setViewDistance((byte) (out.radius = (int) (Math.max(8, Math.min(player.getServerViewDistance(), packet.radius)))));
		
		System.out.println("NEW VIEWDISTANCE: " + out.radius + " " + packet.radius + " " + player.getServerViewDistance());
		
		player.sendPacket(out);
	}
}
