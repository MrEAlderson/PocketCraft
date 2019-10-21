package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityAnimation;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

import static de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityAnimation.*;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityEvent;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityAnimate;

public class TV8D9PacketPlayEntityAnimation extends JavaPacketTranslator<V8D9PacketPlayEntityAnimation> {

	@Override
	public void handle(V8D9PacketPlayEntityAnimation packet, Player player){
		final Entity entity = player.getWorld().getEntity(packet.entityId);
		
		if(entity == null)
			return;
		
		switch(packet.type){
		case TYPE_SWING_ARM:
			entity.playAnimation(PacketEntityAnimate.TYPE_SWING_ARM);
			break;
			
		case TYPE_TAKE_DAMAGE:
			entity.playEvent(EntityEvent.HURT);
			break;
			
		case TYPE_LEAVE_BED:
			entity.playAnimation(PacketEntityAnimate.TYPE_WAKE_UP);
			break;
			
		case TYPE_EAT_FOOD:
			entity.playEvent(EntityEvent.EAT);
			break;
			
		case TYPE_CRITICAL_EFFECT:
			entity.playAnimation(PacketEntityAnimate.TYPE_CRITICAL_HIT);
			break;
			
		case TYPE_MAGIC_CRITICAL_EFFECT:
			entity.playAnimation(PacketEntityAnimate.TYPE_MAGIC_CRITICAL_HIT);
			break;
		}
	}
}
