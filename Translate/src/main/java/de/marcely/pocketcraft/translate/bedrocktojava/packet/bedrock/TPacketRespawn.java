package de.marcely.pocketcraft.translate.bedrocktojava.packet.bedrock;

import de.marcely.pocketcraft.bedrock.network.packet.PacketRespawn;
import de.marcely.pocketcraft.translate.bedrocktojava.BedrockPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TPacketRespawn extends BedrockPacketTranslator<PacketRespawn> {
	
	private boolean respawning = false;
	
	@Override
	public void handle(PacketRespawn packet, Player player){
		if(!player.isDead() || packet.state != PacketRespawn.STATE_CLIENT_READY_TO_SPAWN || respawning)
			return;
		
		{
        	final PacketRespawn out = new PacketRespawn();
        	
        	out.entityRuntimeId = player.getEntityId();
        	out.posX = player.getX();
        	out.posY = player.getY();
        	out.posZ = player.getZ();
        	out.state = PacketRespawn.STATE_READY_TO_SPAWN;
        	
        	player.sendPacket(out);
		}
		
		// player responds with TPacketPlayerAction.RESPAWN (Only on overworld)
		// no need to check it since other dimensions are currently not implemented yet
	}
}
