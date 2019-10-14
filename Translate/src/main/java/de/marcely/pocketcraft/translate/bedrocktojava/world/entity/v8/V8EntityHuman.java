package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMeta;

public class V8EntityHuman extends V8EntityLiving {

	public V8EntityHuman(int id){
		super(id);
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
	public void write(V8EntityMeta meta){
		super.write(meta);
		
		meta.writeByte(10, 0); // skin flags
		meta.writeByte(16, 0); // meta, 0x02 = hide cape
		meta.writeFloat(17, 0); // absorption hearts
		meta.writeInt(18, 0); // score
	}
	
	@Override
	public void read(V8EntityMeta meta){
		super.read(meta);
	}
}
