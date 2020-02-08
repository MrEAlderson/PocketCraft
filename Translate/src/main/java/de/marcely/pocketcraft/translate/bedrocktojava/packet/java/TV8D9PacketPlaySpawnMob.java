package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityAttribute;
import de.marcely.pocketcraft.bedrock.network.packet.PacketSpawnEntity;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlaySpawnMob;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8.V8Entity;

public class TV8D9PacketPlaySpawnMob extends JavaPacketTranslator<V8D9PacketPlaySpawnMob> {

	@Override
	public void handle(V8D9PacketPlaySpawnMob packet, Player player){
		// spawn it
		final V8Entity entity = (V8Entity) player.getTranslator().newEntityInstance(packet.type, packet.entityId, player.getWorld(), false);
		
		if(entity == null)
			return;
		
		{
			entity.onNetworkPositionChange(packet.x, packet.y, packet.z, false);
			entity.setYaw(packet.yaw);
			entity.setHeadYaw(packet.headPitch);
			entity.setPitch(packet.pitch);
			entity.readAll(packet.metadata);  // should be at bottom since entity might does custom spawning
		}
		
		player.getWorld().addEntity(entity);
		
		if(!entity.hasCustomSpawning()){
			final PacketSpawnEntity out = new PacketSpawnEntity();
			
			out.entityUniqueId = out.entityRuntimeId = packet.entityId;
			out.type = entity.getType();
			// vv it's important that we take the parameters from the entity and not from the packet since the entity might already changed them
			out.x = entity.getX();
			out.y = entity.getY();
			out.z = entity.getZ();
			out.yaw = entity.getYaw();
			out.headYaw = entity.getHeadYaw();
			out.pitch = entity.getPitch();
			out.veloX = entity.getVeloX();
			out.veloY = entity.getVeloY();
			out.veloZ = entity.getVeloZ();
			out.metadata = entity.getMetadata();
			out.attributes = new EntityAttribute[0];
			
			player.sendPacket(out);
		}
	}
}