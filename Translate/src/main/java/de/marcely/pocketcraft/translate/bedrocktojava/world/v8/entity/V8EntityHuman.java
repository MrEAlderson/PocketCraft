package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityHuman extends V8EntityLiving {

	public V8EntityHuman(World world, int id){
		super(world, id);
		
		setDataFlag(EntityDataType.FLAG_ALWAYS_SHOW_NAMETAG, true);
	}
	
	@Override
	public int getTypeId(){
		return -1;
	}

	@Override
	public EntityType getType(){
		return EntityType.PLAYER;
	}
	
	@Override
	public float getBedrockPacketYAppend(){
		return 1.62F;
	}
	
	@Override
	public void write(V8EntityMetadata meta){
		super.write(meta);
		
		meta.writeByte(10, 0); // skin flags
		meta.writeByte(16, 0); // meta, 0x02 = hide cape
		meta.writeFloat(17, 0); // absorption hearts
		meta.writeInt(18, 0); // score
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		switch(key){
		// unused
		case 10:
		case 16:
		case 17:
		case 18:
			break;
			
		default:
			super.read(meta, key);
			break;
		}
	}
}
