package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketPlayerMove;
import de.marcely.pocketcraft.bedrock.network.packet.PacketPlayerMove.PlayerMoveType;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayAbilities;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientCommand;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientPosition;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientPositionLook;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientStanding;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayTeleport;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.utils.scheduler.Scheduler;

public class TV8D9PacketPlayTeleport extends JavaPacketTranslator<V8D9PacketPlayTeleport> {

	boolean first = false;
	
	@Override
	public void handle(V8D9PacketPlayTeleport packet, Player player){
		// read it
		{
			if((packet.flags & V8D9PacketPlayTeleport.FLAG_REL_X) > 0)
				player.serverX += packet.x;
			else
				player.serverX = packet.x;
			
			if((packet.flags & V8D9PacketPlayTeleport.FLAG_REL_Y) > 0)
				player.serverY += packet.y;
			else
				player.serverY = packet.y;
			
			if((packet.flags & V8D9PacketPlayTeleport.FLAG_REL_Z) > 0)
				player.serverZ += packet.z;
			else
				player.serverZ = packet.z;
			
			if((packet.flags & V8D9PacketPlayTeleport.FLAG_REL_X_ROT) > 0)
				player.serverYaw += packet.yaw;
			else
				player.serverYaw = packet.yaw;
			
			if((packet.flags & V8D9PacketPlayTeleport.FLAG_REL_Y_ROT) > 0)
				player.serverPitch += packet.pitch;
			else
				player.serverPitch = packet.pitch;
		}
		
		
		// confirm it to server
		{
			final V8D9PacketPlayClientPositionLook out = new V8D9PacketPlayClientPositionLook();
			
			out.x = player.serverX;
			out.y = player.serverY;
			out.z = player.serverZ;
			out.yaw = player.serverYaw;
			out.pitch = player.serverPitch;
			out.isOnGround = false;
			
			player.sendPacket(out);
		}
		
		if(!first){
			Scheduler.runRepeated(() -> {
				final V8D9PacketPlayClientStanding out = new V8D9PacketPlayClientStanding();
				
				out.isOnGround = false;
				
				player.sendPacket(out);
			}, 1000, 1000);
			

			{
				final V8D9PacketPlayClientCommand out = new V8D9PacketPlayClientCommand();
				
				out.command = V8D9PacketPlayClientCommand.COMMAND_PERFORM_RESPAWN;
				
				player.sendPacket(out);
			}
			
			{
				final V8D9PacketPlayAbilities out = new V8D9PacketPlayAbilities();
				
				out.flySpeed = 0.7F;
				out.fovModifier = 1F;
				out.isFlying = true;
				
				player.sendPacket(out);
			}
			
			first = true;
		}
		// and tell it the client
		{
			final PacketPlayerMove out = new PacketPlayerMove();
			
			out.entityRuntimeID = player.getEntityId();
			out.posX = (float) player.serverX;
			out.posY = (float) player.serverY;
			out.posZ = (float) player.serverZ;
			out.yaw = out.headYaw = player.serverYaw;
			out.pitch = player.serverPitch;
			out.mode = PlayerMoveType.TELEPORT;
			out.onGround = false;
			
			player.sendPacket(out);
		}
		
		
		System.out.println("========== HOOORAY");
	}
}
