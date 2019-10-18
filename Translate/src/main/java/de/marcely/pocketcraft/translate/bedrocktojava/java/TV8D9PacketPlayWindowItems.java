package de.marcely.pocketcraft.translate.bedrocktojava.java;

import de.marcely.pocketcraft.bedrock.component.inventory.Item;
import de.marcely.pocketcraft.bedrock.network.packet.PacketInventoryContent;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayWindowItems;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.component.ItemTranslator;

public class TV8D9PacketPlayWindowItems extends JavaPacketTranslator<V8D9PacketPlayWindowItems> {

	@Override
	public void handle(V8D9PacketPlayWindowItems packet, Player player){
		final PacketInventoryContent out = new PacketInventoryContent();
		
		out.windowId = packet.windowId;
		out.items = new Item[packet.items.length];
		
		for(int i=0; i<packet.items.length; i++)
			out.items[i] = ItemTranslator.toBedrock(packet.items[i]);
		
		player.sendPacket(out);
	}
}
