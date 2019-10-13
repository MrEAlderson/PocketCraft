package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketLoginStatus;
import de.marcely.pocketcraft.bedrock.network.packet.PacketPlayerMove;
import de.marcely.pocketcraft.bedrock.network.packet.PacketType;
import de.marcely.pocketcraft.bedrock.network.packet.PacketPlayerMove.PlayerMoveType;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientCommand;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientPositionLook;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayTeleport;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayTeleport extends JavaPacketTranslator<V8D9PacketPlayTeleport> {
	
	@Override
	public void handle(V8D9PacketPlayTeleport packet, Player player){
		// read it
		{
			if((packet.flags & V8D9PacketPlayTeleport.FLAG_REL_X) > 0)
				player.setX(player.getX() + ((float) packet.x));
			else
				player.setX((float) packet.x);
			
			if((packet.flags & V8D9PacketPlayTeleport.FLAG_REL_Y) > 0)
				player.setY(player.getY() + ((float) packet.y));
			else
				player.setY((float) packet.y);
			
			if((packet.flags & V8D9PacketPlayTeleport.FLAG_REL_Z) > 0)
				player.setZ(player.getZ() + ((float) packet.z));
			else
				player.setZ((float) packet.z);
			
			if((packet.flags & V8D9PacketPlayTeleport.FLAG_REL_X_ROT) > 0)
				player.setYaw(player.getYaw() + packet.yaw);
			else
				player.setYaw(packet.yaw);
			
			if((packet.flags & V8D9PacketPlayTeleport.FLAG_REL_Y_ROT) > 0)
				player.setPitch(player.getPitch() + packet.pitch);
			else
				player.setPitch(packet.pitch);
		}
		
		// log in
		if(!player.isLoggedIn()){
			// tells the server that we are ready to join
			{
				final V8D9PacketPlayClientCommand out = new V8D9PacketPlayClientCommand();
				
				out.command = V8D9PacketPlayClientCommand.COMMAND_PERFORM_RESPAWN;
				
				player.sendPacket(out);
			}
			
			{
				final PacketLoginStatus out = (PacketLoginStatus) PacketType.LoginStatus.newInstance();
				
				out.result = PacketLoginStatus.PLAYER_SPAWN;
				
				player.sendPacket(out);
			}
			
			player.setLoginTime(System.currentTimeMillis());
			
			// PacketPlayerAction.TYPE_DIMENSION_CHANGE_ACK isn't getting called on spawn therfore we'll have to directly change it to done
			player.setSpawnState(Player.SPAWN_STATE_DONE);
		}
		
		// confirm it to server
		{
			final V8D9PacketPlayClientPositionLook out = new V8D9PacketPlayClientPositionLook();
			
			out.x = player.getX();
			out.y = player.getY();
			out.z = player.getZ();
			out.yaw = player.getYaw();
			out.pitch = player.getPitch();
			out.isOnGround = false;
			
			player.sendPacket(out);
		}
		
		// and tell it the client
		{
			final PacketPlayerMove out = new PacketPlayerMove();
			
			out.entityRuntimeID = player.getEntityId();
			out.posX = player.getX();
			out.posY = player.getY()+1.62F /* eye height */;
			out.posZ = player.getZ();
			out.yaw = out.headYaw = player.getYaw();
			out.pitch = player.getPitch();
			out.mode = PlayerMoveType.TELEPORT;
			out.onGround = false;
			
			player.sendPacket(out);
		}
	}
}
