package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityEnderCrystal extends V8Entity {

	public V8EntityEnderCrystal(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 200;
	}

	@Override
	public EntityType getType(){
		return EntityType.ENDER_CRYSTAL;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		if(key == 8)
			this.metadata.setInt(EntityDataType.HEALTH, (int) meta.readFloat(key));
		else
			super.read(meta, key);
	}
}
