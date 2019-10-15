package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityWitch extends V8EntityInsentient {

	public V8EntityWitch(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 66;
	}

	@Override
	public EntityType getType(){
		return EntityType.WITCH;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		if(key == 21)
			this.setDataFlag(EntityDataType.FLAG_ANGRY, meta.readBoolean(key));
		else
			super.read(meta, key);
	}
}
