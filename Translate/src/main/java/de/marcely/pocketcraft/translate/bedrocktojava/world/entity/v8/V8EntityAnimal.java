package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import static de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayEntityEvent.*;

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
		case TYPE_TAME_SUCCESS:
			playEvent(EntityEvent.TAME_SUCCESS);
			break;
			
		case TYPE_TAME_FAILED:
			playEvent(EntityEvent.TAME_FAIL);
			break;
			
		default:
			super.playEvent(type);
			break;
		}
	}
}
