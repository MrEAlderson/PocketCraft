package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityAttribute;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityAttributeType;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityAttributes;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlaySetExperience;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlaySetExperience extends JavaPacketTranslator<V8D9PacketPlaySetExperience> {

	@Override
	public void handle(V8D9PacketPlaySetExperience packet, Player player){
		final PacketEntityAttributes out = new PacketEntityAttributes();
		
		out.entityRuntimeId = player.getEntityId();
		out.attributes = new EntityAttribute[]{
			new EntityAttribute(EntityAttributeType.EXPERIENCE_LEVEL, packet.level),
			new EntityAttribute(EntityAttributeType.EXPERIENCE, packet.barPercentage)
		};
		
		player.sendPacket(out);
	}
}
