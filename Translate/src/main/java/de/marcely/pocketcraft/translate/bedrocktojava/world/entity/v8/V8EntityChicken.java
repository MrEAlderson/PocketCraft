package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityChicken extends V8EntityAnimal {

	public V8EntityChicken(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 93;
	}

	@Override
	public EntityType getType(){
		return EntityType.CHICKEN;
	}
}
