package de.marcely.pocketcraft.translate.bedrocktojava.java;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityLink;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityLink.EntityLinkType;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntitySetLink;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityAttach;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.utils.math.Vector3;

public class TV8D9PacketPlayEntityAttach extends JavaPacketTranslator<V8D9PacketPlayEntityAttach> {

	@Override
	public void handle(V8D9PacketPlayEntityAttach packet, Player player){
		final Entity rider = player.getWorld().getEntity(packet.riderEntityId);
		
		if(rider == null)
			return;
		
		final PacketEntitySetLink out = new PacketEntitySetLink();
		
		// enter
		if(packet.vehicleEntityId != -1){
			out.link = new EntityLink(
				EntityLinkType.RIDER,
				packet.vehicleEntityId,
				packet.riderEntityId,
				true);
			
			rider.setVehicleEntityId(packet.vehicleEntityId);
			
			// update rider position
			{
				float yOffset = 0;
				
				if(rider.getType() == EntityType.PLAYER)
					yOffset = 0.6F;
				else
					yOffset = -0.4F;
				
				rider.getMetadata().setVector3(EntityDataType.RIDER_SEAT_POSITION, new Vector3(0F, yOffset, 0F));
				rider.sendAllMetadata(player.getBedrock());
			}
			
			
		// leave
		}else{
			out.link = new EntityLink(
				EntityLinkType.REMOVE,
				rider.getVehicleEntityId(),
				packet.riderEntityId,
				true);
			
			rider.dismount();
		}
		
		player.sendPacket(out);
	}
}
