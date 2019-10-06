package de.marcely.pocketcraft.translate.bedrocktojava.packet.bedrock;

import de.marcely.pocketcraft.bedrock.network.packet.PacketPlayerMove;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientLook;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientPosition;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientPositionLook;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientStanding;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.BedrockPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TPacketPlayerMove extends BedrockPacketTranslator<PacketPlayerMove> {

	@Override
	public void handle(PacketPlayerMove packet, Player player){
		boolean isMoving = packet.posX != player.getX() || packet.posY != player.getY() || packet.posZ != player.getZ();
		boolean isLooking = packet.yaw != player.getYaw() || packet.pitch != player.getPitch();
		
		packet.posY -= 1.62F; // eye height
		
		if(isMoving && isLooking){
			final V8D9PacketPlayClientPositionLook out = new V8D9PacketPlayClientPositionLook();
			
			player.setX((float) (out.x = packet.posX));
			player.setY((float) (out.y = packet.posY));
			player.setZ((float) (out.z = packet.posZ));
			player.setYaw(out.yaw = packet.yaw);
			player.setPitch(out.pitch = packet.pitch);
			out.isOnGround = packet.onGround;
			
			player.sendPacket(out);
		
		}else if(isMoving && !isLooking){
			final V8D9PacketPlayClientPosition out = new V8D9PacketPlayClientPosition();
			
			player.setX((float) (out.x = packet.posX));
			player.setY((float) (out.y = packet.posY));
			player.setZ((float) (out.z = packet.posZ));
			out.isOnGround = packet.onGround;
			
			player.sendPacket(out);
		
		}else if(!isMoving && isLooking){
			final V8D9PacketPlayClientLook out = new V8D9PacketPlayClientLook();
			
			player.setYaw(out.yaw = packet.yaw);
			player.setPitch(out.pitch = packet.pitch);
			out.isOnGround = packet.onGround;
			
			player.sendPacket(out);
		
		
		}else{
			final V8D9PacketPlayClientStanding out = new V8D9PacketPlayClientStanding();
			
			out.isOnGround = packet.onGround;
			
			player.sendPacket(out);
		}
	}
}
