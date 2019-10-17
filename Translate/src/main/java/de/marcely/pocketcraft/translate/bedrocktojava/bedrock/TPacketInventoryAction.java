package de.marcely.pocketcraft.translate.bedrocktojava.bedrock;

import de.marcely.pocketcraft.bedrock.network.packet.PacketInventoryAction;
import de.marcely.pocketcraft.bedrock.network.packet.action.*;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClickEntity;
import de.marcely.pocketcraft.translate.bedrocktojava.BedrockPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TPacketInventoryAction extends BedrockPacketTranslator<PacketInventoryAction> {
	
	// TODO: Send change slot packet to server with action
	@Override
	public void handle(PacketInventoryAction packet, Player player){
		if(packet.action != null)
			handleAction(packet.action, player);
	}
	
	private void handleAction(Action action, Player player){
		if(action instanceof UseItemAction)
			handleAction((UseItemAction) action, player);
		else if(action instanceof UseItemOnEntityAction)
			handleAction((UseItemOnEntityAction) action, player);
		else if(action instanceof DropItemAction)
			handleAction((DropItemAction) action, player);
	}
	
	private void handleAction(UseItemAction action, Player player){
		
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
	
	private void handleAction(DropItemAction action, Player player){
		
	}
}
