package de.marcely.pocketcraft.translate.bedrocktojava.bedrock;

import de.marcely.pocketcraft.bedrock.network.packet.PacketInventoryAction;
import de.marcely.pocketcraft.bedrock.network.packet.action.*;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayBlockDig;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayBlockPlace;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClickEntity;
import de.marcely.pocketcraft.translate.bedrocktojava.BedrockPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.component.ItemTranslator;
import de.marcely.pocketcraft.utils.math.Vector3;

public class TPacketInventoryAction extends BedrockPacketTranslator<PacketInventoryAction> {
	
	// TODO: Send change slot packet to server with action
	
	@Override
	public void handle(PacketInventoryAction packet, Player player){
		if(packet.action != null)
			handleAction(packet.action, player);
		
		for(InventoryAction action:packet.invActions)
			handleInventoryAction(action, player);
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
		final V8D9PacketPlayBlockPlace out = new V8D9PacketPlayBlockPlace();
		
		out.position = new Vector3(-1, -1, -1);
		out.face = -1;
		out.item = ItemTranslator.toJava(action.item);
		out.cursorPosX = -1;
		out.cursorPosY = -1;
		out.cursorPosZ = -1;
		
		player.sendPacket(out);
	}
	
	// sent e.g. when stopping to eat food
	private void handleAction(CancelUseItemAction action, Player player){
		final V8D9PacketPlayBlockDig out = new V8D9PacketPlayBlockDig();
		
		out.status = V8D9PacketPlayBlockDig.STATUS_FINISH_ACTION;
		out.face = (byte) 0xFF;
		out.position = new Vector3();
		
		player.sendPacket(out);
	}
	
	private void handleInventoryAction(InventoryAction action, Player player){
		System.out.println("inventory action: " + action);
	}
}
