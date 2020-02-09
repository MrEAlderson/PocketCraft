package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import static de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityEvent.*;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityEvent;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public abstract class V8EntityAnimal extends V8EntityAgeable {

	public V8EntityAnimal(World world, int id){
		super(world, id);
	}
	
	@Override
	public void playEvent(byte type){
		switch(type){
		case TYPE_TAME_START:
			this.setDataFlag(EntityDataType.FLAG_INLOVE, true);
			break;
			
		case TYPE_TAME_SUCCESS:
			this.setDataFlag(EntityDataType.FLAG_INLOVE, false);
			playEvent(EntityEvent.TAME_SUCCESS);
			break;
			
		case TYPE_TAME_FAILED:
			this.setDataFlag(EntityDataType.FLAG_TEMPTED, true);
			playEvent(EntityEvent.TAME_FAIL);
			break;
			
		default:
			super.playEvent(type);
			break;
		}
	}
}
