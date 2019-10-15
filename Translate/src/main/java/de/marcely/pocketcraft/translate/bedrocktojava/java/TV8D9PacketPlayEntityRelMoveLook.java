package de.marcely.pocketcraft.translate.bedrocktojava.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityMove;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityRelMoveLook;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayEntityRelMoveLook extends JavaPacketTranslator<V8D9PacketPlayEntityRelMoveLook> {

	@Override
	public void handle(V8D9PacketPlayEntityRelMoveLook packet, Player player){
		final Entity entity = player.getWorld().getEntity(packet.entityId);
		
		if(entity == null)
			return;
		
		{
			entity.setX(entity.getX() + packet.relX);
			entity.setY(entity.getY() + packet.relY);
			entity.setZ(entity.getZ() + packet.relZ);
			entity.setYaw(packet.yaw);
			entity.setPitch(packet.pitch);
			entity.setOnGround(packet.isOnGround);
		}
		
		{
			final PacketEntityMove out = new PacketEntityMove();
			
			out.entityRuntimeId = packet.entityId;
			out.x = entity.getX();
			out.y = entity.getY()+entity.getBedrockPacketYAppend();
			out.z = entity.getZ();
			out.yaw = packet.yaw;
			out.headYaw = entity.getHeadYaw();
			out.pitch = packet.pitch;
			out.isOnGround = packet.isOnGround;
			out.isTeleport = false;
			
			player.sendPacket(out);
		}
	}
}
