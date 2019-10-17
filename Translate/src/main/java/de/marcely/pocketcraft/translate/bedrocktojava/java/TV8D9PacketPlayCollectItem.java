package de.marcely.pocketcraft.translate.bedrocktojava.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketCollectItem;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayCollectItem;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayCollectItem extends JavaPacketTranslator<V8D9PacketPlayCollectItem> {

	@Override
	public void handle(V8D9PacketPlayCollectItem packet, Player player){
		final PacketCollectItem out = new PacketCollectItem();
		
		out.collectorRuntimeId = packet.collectorEntityId;
		out.itemRuntimeId = packet.collectedEntityId;
		
		player.sendPacket(out);
	}
}
