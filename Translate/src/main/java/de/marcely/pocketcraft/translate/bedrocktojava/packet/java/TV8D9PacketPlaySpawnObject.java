package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityAttribute;
import de.marcely.pocketcraft.bedrock.network.packet.PacketSpawnEntity;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlaySpawnObject;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity.V8Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity.V8EntityObject;

public class TV8D9PacketPlaySpawnObject extends JavaPacketTranslator<V8D9PacketPlaySpawnObject> {

	@Override
	public void handle(V8D9PacketPlaySpawnObject packet, Player player){
		final V8Entity entity = (V8Entity) player.getTranslator().newEntityInstance(packet.type, packet.entityId, player.getWorld(), true);
		
		if(entity == null || entity.getType() == null)
			return;
		
		{
			entity.onNetworkPositionChange(packet.x, packet.y, packet.z, false);
			entity.setYaw(packet.yaw);
			entity.setPitch(packet.pitch);
			entity.setVeloX(packet.veloX);
			entity.setVeloY(packet.veloY);
			entity.setVeloZ(packet.veloZ);
			((V8EntityObject) entity).readData(packet.data); // should be at bottom since entity might does custom spawning
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
			out.pitch = entity.getPitch();
			out.veloX = entity.getVeloX();
			out.veloY = entity.getVeloY();
			out.veloZ = entity.getVeloZ();
			out.metadata = entity.getMetadata();
			out.attributes = new EntityAttribute[0];
			
			System.out.println(entity.getVeloX() + " " + entity.getVeloY() + " " + entity.getVeloZ());
			
			player.sendPacket(out);
		}
	}
}
