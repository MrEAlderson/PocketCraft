package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityExperienceOrb extends V8EntityLiving {

	public V8EntityExperienceOrb(World world, int id, short amount){
		super(world, id);
		
		// bedrock only has one variant of xp orb
		
		this.setDataFlag(EntityDataType.FLAG_NO_AI, true);
	}

	@Override
	public int getTypeId(){
		return -1;
	}

	@Override
	public EntityType getType(){
		return EntityType.XP_ORB;
	}
}
