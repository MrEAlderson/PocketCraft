package de.marcely.pocketcraft.translate.bedrocktojava.packet.bedrock;

import de.marcely.pocketcraft.bedrock.network.packet.PacketPlayerAction;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientCommand;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientEntityAction;
import de.marcely.pocketcraft.translate.bedrocktojava.BedrockPacketTranslator;
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
				player.setSprinting(true);
				player.updateSpeed();
				break;
				
			case PacketPlayerAction.TYPE_STOP_SPRINT:
				out.type = V8D9PacketPlayClientEntityAction.TYPE_SPRINT_STOP;
				player.setSprinting(false);
				player.updateSpeed();
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
		
		case PacketPlayerAction.TYPE_RESPAWN:
		{
			// tell server that we clicked on respawn
			{
				final V8D9PacketPlayClientCommand out = new V8D9PacketPlayClientCommand();
				
				out.command = V8D9PacketPlayClientCommand.COMMAND_PERFORM_RESPAWN;
				
				player.sendPacket(out);
			}
		}
		break;
		
		case PacketPlayerAction.TYPE_DIMENSION_CHANGE_ACK:
		{
			// player.setSpawnState(Player.SPAWN_STATE_DONE);
			System.out.println("READY TO SPAWN");
		}
		break;
		}
	}
}
