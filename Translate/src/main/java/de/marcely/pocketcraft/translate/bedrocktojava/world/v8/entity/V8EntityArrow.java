package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.v8.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityArrow extends V8EntityProjectile implements V8EntityObject {

	public V8EntityArrow(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 60;
	}

	@Override
	public EntityType getType(){
		return EntityType.ARROW;
	}
	
	@Override
	public float getAdditionalGravity(){
		return 0.05F;
	}
	
	@Override
	public float getAdditionalWeight(){
		return 0.99F /* 0.6F in water */;
	}
	
	@Override
	protected float getBlockCollisionWidth(){
		return 0.01F;
	}
	
	@Override
	protected float getBlockCollisionHeight(){
		return 0.01F;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		if(key == 16)
			this.setDataFlag(EntityDataType.FLAG_CRITICAL, meta.readBoolean(key));
		else
			super.read(meta, key);
	}
	
	@Override
	public void tick(){
		if(this.veloX != 0 || this.veloY != 0 || this.veloZ != 0){
			double f = Math.sqrt((this.veloX * this.veloX) + (this.veloZ * this.veloZ));
			
            this.yaw = (float) (Math.atan2(this.veloX, this.veloZ) * 180 / Math.PI);
            this.pitch = (float) (Math.atan2(this.veloY, f) * 180 / Math.PI);
		}
		
		super.tick();
	}
	
	@Override
	public void setHeadYaw(float headYaw){ }
}
