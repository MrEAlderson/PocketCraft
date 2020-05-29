package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.bedrock.component.world.entity.Entity;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayPlayerEnterBed;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

public class TV8D9PacketPlayPlayerEnterBed extends JavaPacketTranslator<V8D9PacketPlayPlayerEnterBed> {

	@Override
	public void handle(V8D9PacketPlayPlayerEnterBed packet, Player player){
		final Entity entity = player.getWorld().getEntity(packet.entityId);
		
		if(entity == null || entity.getType() != EntityType.PLAYER){
			System.out.println("unkown entity or not player");
			return;
		}
		
		entity.getMetadata().setVector3(EntityDataType.PLAYER_BED_POSITION, packet.position);
		entity.setDataPlayerFlag(EntityDataType.PLAYER_FLAG_SLEEP, true);
		entity.sendAllMetadata(player.getBedrock());
	}
}
