package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.component.inventory.item.BItem;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityLink;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityArmor;
import de.marcely.pocketcraft.bedrock.network.packet.PacketPlayerList;
import de.marcely.pocketcraft.bedrock.network.packet.PacketSpawnEntityPlayer;
import de.marcely.pocketcraft.bedrock.network.packet.PacketPlayerList.PlayerListEntry;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlaySpawnPlayer;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.PlayerList;
import de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity.V8Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity.V8EntityHuman;

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
			entity.onNetworkPositionChange(packet.x, packet.y, packet.z, false);
			entity.setYaw(packet.yaw);
			entity.setHeadYaw(packet.yaw);
			entity.setPitch(packet.pitch);
		}
		
		entity.getMetadata().setBoolean(EntityDataType.ALWAYS_SHOW_NAMETAG, true);
		entity.setDataFlag(EntityDataType.FLAG_ALWAYS_SHOW_NAMETAG, true);
		entity.setDataFlag(EntityDataType.FLAG_CAN_SHOW_NAMETAG, true);
		
		player.getWorld().addEntity(entity);
		
		{
			final PacketPlayerList out = new PacketPlayerList();
			
			out.type = PacketPlayerList.TYPE_ADD;
			out.entries = new PlayerListEntry[]{
				new PlayerListEntry(
						packet.uuid,
						packet.entityId,
						listEntry.getName(),
						player.getBedrock().getInfo().getSkin()
				)
			};
			
			player.sendPacket(out);
		}
		
		// send out
		{	
			final PacketSpawnEntityPlayer out = new PacketSpawnEntityPlayer();
			
			// vv it's important that we take the parameters from the entity and not from the packet since the entity might already changed them
			out.uuid = packet.uuid;
			out.name = listEntry.getName();
			out.entityUniqueId = out.entityRuntimeId = entity.getLongId();
			out.x = entity.getX();
			out.y = entity.getY();
			out.z = entity.getZ();
			out.yaw = entity.getYaw();
			out.headYaw = entity.getHeadYaw();
			out.pitch = entity.getPitch();
			out.itemOnHand = new BItem(0);
			out.metadata = entity.getMetadata();
			out.links = new EntityLink[0];
			
			player.sendPacket(out);
		}
		
		{
			final PacketEntityArmor out = new PacketEntityArmor();
			
			out.entityId = packet.entityId;
			
			for(int i=0; i<out.items.length; i++)
				out.items[i] = new BItem(0);
			
			player.sendPacket(out);
		}
	}
}
