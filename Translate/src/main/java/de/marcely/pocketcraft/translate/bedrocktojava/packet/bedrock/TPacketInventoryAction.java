package de.marcely.pocketcraft.translate.bedrocktojava.packet.bedrock;

import de.marcely.pocketcraft.bedrock.network.packet.PacketInventoryAction;
import de.marcely.pocketcraft.bedrock.network.packet.action.*;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayBlockDig;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayBlockPlace;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClickEntity;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayWindowClick;
import de.marcely.pocketcraft.translate.bedrocktojava.BedrockPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.utils.math.Vector3;

import static de.marcely.pocketcraft.bedrock.network.packet.action.InventoryAction.*;

import de.marcely.pocketcraft.bedrock.component.inventory.Item;
import de.marcely.pocketcraft.bedrock.network.InventoryId;

public class TPacketInventoryAction extends BedrockPacketTranslator<PacketInventoryAction> {
	
	// TODO: Send change slot packet to server with action
	
	@Override
	public void handle(PacketInventoryAction packet, Player player){
		if(packet.action != null)
			handleAction(packet.action, player);
		
		if(packet.invActions.length >= 1)
			handleInventoryActions(packet.invActions, player);
	}
	
	private void handleAction(Action action, Player player){
		if(action instanceof UseItemAction)
			handleAction((UseItemAction) action, player);
		else if(action instanceof UseItemOnEntityAction)
			handleAction((UseItemOnEntityAction) action, player);
		else if(action instanceof CancelUseItemAction)
			handleAction((CancelUseItemAction) action, player);
	}
	
	private void handleAction(UseItemOnEntityAction action, Player player){
		final V8D9PacketPlayClickEntity out = new V8D9PacketPlayClickEntity();
		
		out.targetEntityId = (int) action.entityId;
		out.type = action.actionType == UseItemOnEntityAction.ACTION_TYPE_ATTACK ? V8D9PacketPlayClickEntity.TYPE_ATTACK : V8D9PacketPlayClickEntity.TYPE_INTERACT_AT;
		out.targetX = action.clickPos.getX();
		out.targetY = action.clickPos.getY();
		out.targetZ = action.clickPos.getZ();
		
		player.sendPacket(out);
	}
	
	boolean e = false;
	
	private void handleAction(UseItemAction action, Player player){
		System.out.println("USE");
		
		final V8D9PacketPlayBlockPlace out = new V8D9PacketPlayBlockPlace();
		
		out.position = new Vector3(action.blockPosX, action.blockPosY, action.blockPosZ);
		out.face = -1;
		out.item = player.getTranslateComponents().toJava(action.item, TranslateComponents.ITEM);
		out.cursorPosX = -1;
		out.cursorPosY = -1;
		out.cursorPosZ = -1;
		
		player.sendPacket(out);
	}
	
	// sent e.g. when stopping to eat food
	private void handleAction(CancelUseItemAction action, Player player){
		System.out.println("CANCEL");
		
		final V8D9PacketPlayBlockDig out = new V8D9PacketPlayBlockDig();
		
		out.status = V8D9PacketPlayBlockDig.STATUS_FINISH_ACTION;
		out.face = (byte) 0xFF;
		out.position = new Vector3();
		
		player.sendPacket(out);
	}
	
	private Item cursor;
	private short transactionId = 1;
	
	private void handleInventoryActions(InventoryAction[] actions, Player player){
		if(actions.length == 2){
			// DROP
			/*
				0: source=2, windowId=255, newItem=gleich wie bei 1.oldItem, oldItem = 0
				1: source=0, windowId=0, newItem=wieviel jz, oldItem=wieviel vorher
				
				Falls 1.newItem = 0 & 1.oldItem.amount >= 2, dann ganzer stack
				Amsonsten nur einer
			 */
			if(actions[0].sourceType == SOURCE_TYPE_WORLD && actions[1].sourceType == SOURCE_TYPE_CONTAINER){
				final InventoryAction source = actions[1];
				final V8D9PacketPlayBlockDig out = new V8D9PacketPlayBlockDig();
				// final int slot = source.slot; <-- hotbar slot TODO: Held change server mitteilen
				
				// stack drop
				if(source.newItem.amount == 0 && source.oldItem.amount >= 2)
					out.status = V8D9PacketPlayBlockDig.STATUS_DROP_ITEM_STACK;
				else
					out.status = V8D9PacketPlayBlockDig.STATUS_DROP_ITEM_SINGLE;
				
				System.out.println("DROP " + out.status);
				
				out.face = (byte) 0xFF;
				out.position = new Vector3();
				
				player.sendPacket(out);
			
			// taking/putting back an item
			}else if(actions[1].inventoryId == InventoryId.SPECIAL_CURSOR){
				final V8D9PacketPlayWindowClick out = new V8D9PacketPlayWindowClick();
				final int inventoryId = actions[0].inventoryId;
				final int slot = actions[0].slot;
				final Item oldSlotItem = actions[0].oldItem;
				final Item newSlotItem = actions[0].newItem;
				final Item newCursorItem = actions[1].newItem;
				
				// take/place all (left click)
				if(oldSlotItem.equals(newCursorItem)){
					out.button = 0;
					System.out.println("LEFT CLICK " + slot);
					
				// right click
				}else{
					out.button = 1;
					System.out.println("RIGHT CLICK " + slot);
				}
				
				out.windowId = 0;
				out.mode = V8D9PacketPlayWindowClick.MODE_MOUSE_CLICK;
				out.transactionId = this.transactionId++;
				out.slot = (short) slot;
				out.clickedItem = player.getTranslateComponents().toJava(oldSlotItem, TranslateComponents.ITEM);
				
				player.sendPacket(out);
			}
		}
	}
}
