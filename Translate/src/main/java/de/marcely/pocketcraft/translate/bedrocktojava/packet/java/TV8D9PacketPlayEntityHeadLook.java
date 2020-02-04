package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityRelMove;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityHeadLook;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.utils.math.MathUtil;

public class TV8D9PacketPlayEntityHeadLook extends JavaPacketTranslator<V8D9PacketPlayEntityHeadLook> {

	@Override
	public void handle(V8D9PacketPlayEntityHeadLook packet, Player player){
		final Entity entity = player.getWorld().getEntity(packet.entityId);
		
		if(entity == null)
			return;
		
		float yaw = calcHeadYaw(packet.headYaw, entity.getYaw(), 10F);
		
		{
			entity.setYaw(yaw);
			entity.setHeadYaw(packet.headYaw);
		}
		
		{
			final PacketEntityRelMove out = new PacketEntityRelMove();
			
			out.entityRuntimeId = packet.entityId;
			out.flags |= PacketEntityRelMove.FLAG_HAS_HEAD_YAW;
			out.headYaw = packet.headYaw;
			
			player.sendPacket(out);
		}
	}
	
    private float calcHeadYaw(float yaw, float headYaw, float range){
        float value = MathUtil.circularDegrees(headYaw - yaw);
        
        if(value > range)
        	value = range;
        else if (value < -range)
        	value = -range;

        return yaw + value;
    }
}
