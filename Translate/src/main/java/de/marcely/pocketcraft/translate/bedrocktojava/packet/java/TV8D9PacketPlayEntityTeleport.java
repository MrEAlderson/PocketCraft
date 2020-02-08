package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityMove;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityTeleport;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayEntityTeleport extends JavaPacketTranslator<V8D9PacketPlayEntityTeleport> {

	@Override
	public void handle(V8D9PacketPlayEntityTeleport packet, Player player){
		final Entity entity = player.getWorld().getEntity(packet.entityId);
		
		if(entity == null)
			return;
		
		{
			entity.onNetworkPositionChange(packet.x, packet.y, packet.z, false);
			entity.setYaw(packet.yaw);
			entity.setPitch(packet.pitch);
			entity.setOnGround(packet.isOnGround);
			entity.onTeleport();
		}
		
		{
			final PacketEntityMove out = new PacketEntityMove();
			
			out.entityRuntimeId = packet.entityId;
			out.x = entity.getX();
			out.y = entity.getY()+entity.getBedrockPacketYAppend();
			out.z = entity.getZ();
			out.yaw = entity.getYaw();
			out.headYaw = entity.getHeadYaw();
			out.pitch = entity.getPitch();
			out.isOnGround = entity.isOnGround();
			out.isTeleport = false;
			
			player.sendPacket(out);
		}
	}
}
