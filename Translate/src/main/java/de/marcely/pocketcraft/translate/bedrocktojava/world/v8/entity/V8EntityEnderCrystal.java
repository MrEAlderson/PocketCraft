package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.v8.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityEnderCrystal extends V8Entity implements V8EntityObject {

	public V8EntityEnderCrystal(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 51;
	}

	@Override
	public EntityType getType(){
		return EntityType.ENDER_CRYSTAL;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		if(key == 8)
			this.metadata.setInt(EntityDataType.HEALTH, meta.readInt(key));
		else
			super.read(meta, key);
	}
}
