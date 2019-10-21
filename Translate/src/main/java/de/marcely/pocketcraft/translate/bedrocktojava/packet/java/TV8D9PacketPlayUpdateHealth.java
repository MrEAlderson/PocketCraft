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
		
		if(packet.health <= 0)
			player.setDead(true);
		else
			player.setDead(false);
	}
}
