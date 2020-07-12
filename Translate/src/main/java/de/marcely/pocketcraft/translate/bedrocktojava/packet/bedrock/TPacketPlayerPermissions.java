package de.marcely.pocketcraft.translate.bedrocktojava.packet.bedrock;

import de.marcely.pocketcraft.bedrock.component.permission.BPlayerPermissions;
import de.marcely.pocketcraft.bedrock.network.packet.PacketPlayerPermissions;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayAbilities;
import de.marcely.pocketcraft.translate.bedrocktojava.BedrockPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TPacketPlayerPermissions extends BedrockPacketTranslator<PacketPlayerPermissions> {

	@Override
	public void handle(PacketPlayerPermissions packet, Player player){
		// toggle flight
		if(packet.permissions.isFlying() != player.getPermissions().isFlying()){
			final BPlayerPermissions perms = player.getPermissions();
			
			perms.setFlying(packet.permissions.isFlying());
			player.updateSpeed();
			
			// send
			{
				final V8D9PacketPlayAbilities out = new V8D9PacketPlayAbilities();
				
				out.isInvulnerable = perms.isImmutable();
				out.isFlying = perms.isFlying();
				out.canFly = perms.isFlightAllowed();
				out.canInstantlyBuild = perms.isWorldBuilder();
				
				player.sendPacket(out);
			}
		}
	}
}
