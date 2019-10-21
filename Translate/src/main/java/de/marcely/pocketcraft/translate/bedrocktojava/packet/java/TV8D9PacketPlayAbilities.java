package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.component.permission.PlayerPermissions;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayAbilities;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayAbilities extends JavaPacketTranslator<V8D9PacketPlayAbilities> {

	@Override
	public void handle(V8D9PacketPlayAbilities packet, Player player){
		boolean updateSpeed = false;
		
		{
			final PlayerPermissions perms = player.getPermissions();
			
			if(perms.isFlying() != packet.isFlying)
				updateSpeed = true;
			
			perms.setImmutable(packet.isInvulnerable);
			perms.setFlying(packet.isFlying);
			perms.setFlightAllowed(packet.canFly);
			perms.setWorldBuilder(packet.canInstantlyBuild);
			
			player.updatePermissions();
		}
		
		{
			if((player.getFlySpeed() != packet.flySpeed) || (player.getWalkSpeed() != packet.walkSpeed)){
				player.setFlySpeed(packet.flySpeed);
				player.setWalkSpeed(packet.walkSpeed);
				
				updateSpeed = true;
			}
		}
		
		if(updateSpeed)
			player.updateSpeed();
	}
}
