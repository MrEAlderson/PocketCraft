package de.marcely.pocketcraft.translate.bedrocktojava.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketInventorySetItem;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayWindowSetItem;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.component.ItemTranslator;

public class TV8D9PacketPlayWindowSetItem extends JavaPacketTranslator<V8D9PacketPlayWindowSetItem> {

	@Override
	public void handle(V8D9PacketPlayWindowSetItem packet, Player player){
		final PacketInventorySetItem out = new PacketInventorySetItem();
		
		out.inventoryId = packet.windowId;
		out.slot = packet.slot;
		out.item = ItemTranslator.toBedrock(packet.item);
		
		player.sendPacket(out);
	}
}
