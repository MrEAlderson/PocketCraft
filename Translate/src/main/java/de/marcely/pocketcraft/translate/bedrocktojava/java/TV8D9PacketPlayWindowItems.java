package de.marcely.pocketcraft.translate.bedrocktojava.java;

import java.util.Arrays;

import de.marcely.pocketcraft.bedrock.component.inventory.Item;
import de.marcely.pocketcraft.bedrock.network.InventoryId;
import de.marcely.pocketcraft.bedrock.network.packet.PacketInventoryContent;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayWindowItems;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.component.ItemTranslator;

public class TV8D9PacketPlayWindowItems extends JavaPacketTranslator<V8D9PacketPlayWindowItems> {

	@Override
	public void handle(V8D9PacketPlayWindowItems packet, Player player){
		if(packet.windowId == 0){ // player inventory
			if(packet.items.length >= 44){
				{
					final de.marcely.pocketcraft.java.component.Item[] items = new de.marcely.pocketcraft.java.component.Item[36];
					
					System.arraycopy(packet.items, 36, items, 0, 9); // hotbar
					System.arraycopy(packet.items, 9, items, 9, 27); // content
					
					sendContentPacket(player, InventoryId.SPECIAL_INVENTORY, items);
				}
				
				{
					sendContentPacket(player, InventoryId.SPECIAL_ARMOR, Arrays.copyOfRange(packet.items, 5, 8)); // armor
				}
			}
		}
	}
	
	private static void sendContentPacket(Player player, int inventoryId, de.marcely.pocketcraft.java.component.Item[] items){
		final PacketInventoryContent out = new PacketInventoryContent();
		
		out.inventoryId = inventoryId;
		out.items = new Item[items.length];
		
		for(int i=0; i<items.length; i++)
			out.items[i] = ItemTranslator.toBedrock(items[i]);
		
		player.sendPacket(out);
	}
}
