package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityAttribute;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityAttributeType;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityAttributes;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayUpdateHealth;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayUpdateHealth extends JavaPacketTranslator<V8D9PacketPlayUpdateHealth> {

	@Override
	public void handle(V8D9PacketPlayUpdateHealth packet, Player player){
		final PacketEntityAttributes out = new PacketEntityAttributes();
		
		out.entityRuntimeId = player.getEntityId();
		out.attributes = new EntityAttribute[]{
			new EntityAttribute(EntityAttributeType.HEALTH, packet.health),
			new EntityAttribute(EntityAttributeType.FOOD, packet.foodLevel),
			new EntityAttribute(EntityAttributeType.SATURATION, packet.foodSaturation)
		};
		
		player.sendPacket(out);
		
		if(packet.health <= 0){
			player.setDead(true);
		
		}else{
			player.setDead(false);
		}
	}
	
	/*private void updateDeadStatus(Player player){
		Scheduler.runLater(() -> {
			System.out.println("online: " + player.isOnline());
			
			if(player.isOnline() && player.isDead()){
				if(player.getSpawnState() == Player.SPAWN_STATE_WAITING_SPAWN)
					updateDeadStatus(player);
				
				else{
					final PacketEntityAttributes out = new PacketEntityAttributes();
					
					out.entityRuntimeId = player.getEntityId();
					out.attributes = new EntityAttribute[]{
						new EntityAttribute(EntityAttributeType.HEALTH, 0),
					};
					
					player.sendPacket(out);
				}
			}
		}, 500);
	}*/
}
