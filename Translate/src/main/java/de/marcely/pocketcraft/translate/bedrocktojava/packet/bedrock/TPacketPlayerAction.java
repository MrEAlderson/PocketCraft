package de.marcely.pocketcraft.translate.bedrocktojava.packet.bedrock;

import de.marcely.pocketcraft.bedrock.network.packet.PacketPlayerAction;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientEntityAction;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.BedrockPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TPacketPlayerAction extends BedrockPacketTranslator<PacketPlayerAction> {

	@Override
	public void handle(PacketPlayerAction packet, Player player){
		switch(packet.type){
		case PacketPlayerAction.TYPE_STOP_SLEEPING:
		case PacketPlayerAction.TYPE_START_SPRINT:
		case PacketPlayerAction.TYPE_STOP_SPRINT:
		case PacketPlayerAction.TYPE_START_SNEAK:
		case PacketPlayerAction.TYPE_STOP_SNEAK:
		{
			final V8D9PacketPlayClientEntityAction out = new V8D9PacketPlayClientEntityAction();
			
			switch(packet.type){
			case PacketPlayerAction.TYPE_STOP_SLEEPING:
				out.type = V8D9PacketPlayClientEntityAction.TYPE_LEAVE_BED;
				break;
				
			case PacketPlayerAction.TYPE_START_SPRINT:
				out.type = V8D9PacketPlayClientEntityAction.TYPE_SPRINT_START;
				break;
				
			case PacketPlayerAction.TYPE_STOP_SPRINT:
				out.type = V8D9PacketPlayClientEntityAction.TYPE_SPRINT_STOP;
				break;
				
			case PacketPlayerAction.TYPE_START_SNEAK:
				out.type = V8D9PacketPlayClientEntityAction.TYPE_SNEAK_START;
				break;
				
			case PacketPlayerAction.TYPE_STOP_SNEAK:
				out.type = V8D9PacketPlayClientEntityAction.TYPE_SNEAK_STOP;
				break;
			}
			
			out.entityId = player.getEntityId();
			
			player.sendPacket(out);
		}
		break;
		}
	}
}
