package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.InventoryId;
import de.marcely.pocketcraft.bedrock.network.packet.PacketInventorySetItem;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayWindowSetItem;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.v8.V8ItemTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayWindowSetItem extends JavaPacketTranslator<V8D9PacketPlayWindowSetItem> {

	@Override
	public void handle(V8D9PacketPlayWindowSetItem packet, Player player){
		int inventoryId = -1;
		int slot = -1;
		
		// get correct inventoryId & slot
		{
			if(packet.windowId == 0){ // player inventory
				if(packet.slot >= 45)
					return; // invalid
				
				else if(packet.slot >= 36){ // hotbar
					inventoryId = InventoryId.SPECIAL_INVENTORY;
					slot = packet.slot - 36;
				
				}else if(packet.slot >= 9){ // content
					inventoryId = InventoryId.SPECIAL_INVENTORY;
					slot = packet.slot;
				
				}else if(packet.slot >= 5){
					inventoryId = InventoryId.SPECIAL_ARMOR;
					slot = packet.slot - 5;
				
				}else
					return; // invalid
			}
		}
		
		// send packet
		{
			final PacketInventorySetItem out = new PacketInventorySetItem();
			
			out.inventoryId = inventoryId;
			out.slot = slot;
			out.item = V8ItemTranslator.toBedrock(packet.item);
			
			player.sendPacket(out);
		}
	}
}
