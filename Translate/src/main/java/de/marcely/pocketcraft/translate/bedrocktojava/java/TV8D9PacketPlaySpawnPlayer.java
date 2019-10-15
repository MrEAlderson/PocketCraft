package de.marcely.pocketcraft.translate.bedrocktojava.java;

import de.marcely.pocketcraft.bedrock.component.inventory.Item;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityLink;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityArmor;
import de.marcely.pocketcraft.bedrock.network.packet.PacketSpawnEntityPlayer;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlaySpawnPlayer;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.PlayerList;
import de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8.V8Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8.V8EntityHuman;

public class TV8D9PacketPlaySpawnPlayer extends JavaPacketTranslator<V8D9PacketPlaySpawnPlayer> {

	@Override
	public void handle(V8D9PacketPlaySpawnPlayer packet, Player player){
		final PlayerList.Entry listEntry = player.getWorld().getPlayerList().get(packet.uuid);
		
		if(listEntry == null)
			return;
		
		final V8Entity entity = new V8EntityHuman(player.getWorld(), packet.entityId);
		
		{
			// display name
			if(!packet.metadata.has(2))
				packet.metadata.writeString(2, !listEntry.getName().isEmpty() ? listEntry.getName() : " ");
			
			entity.readAll(packet.metadata);
			entity.setX(packet.x);
			entity.setY(packet.y);
			entity.setZ(packet.z);
			entity.setYaw(packet.yaw);
			entity.setHeadYaw(packet.yaw);
			entity.setPitch(packet.pitch);
		}
		
		player.getWorld().addEntity(entity);
		
		// send out
		{	
			final PacketSpawnEntityPlayer out = new PacketSpawnEntityPlayer();
			
			out.uuid = packet.uuid;
			out.name = listEntry.getName();
			out.entityUniqueId = out.entityRuntimeId = packet.entityId;
			out.x = packet.x;
			out.y = packet.y;
			out.z = packet.z;
			out.yaw = out.headYaw = packet.yaw;
			out.pitch = packet.pitch;
			out.itemOnHand = new Item(0);
			out.metadata = entity.getMetadata();
			out.links = new EntityLink[0];
			
			player.sendPacket(out);
		}
		
		{
			final PacketEntityArmor out = new PacketEntityArmor();
			
			out.entityId = packet.entityId;
			
			for(int i=0; i<out.items.length; i++)
				out.items[i] = new Item(0);
			
			player.sendPacket(out);
		}
	}
}