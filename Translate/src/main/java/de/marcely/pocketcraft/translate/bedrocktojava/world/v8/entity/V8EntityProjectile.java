package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.translate.bedrocktojava.world.World;
import de.marcely.pocketcraft.translate.bedrocktojava.world.block.BlockCollisionEvent;

public abstract class V8EntityProjectile extends V8Entity {
	
	private boolean ticked = false;
	
	public V8EntityProjectile(World world, int id){
		super(world, id);
	}
	
	@Override
	public void tick(){
		super.tick();
		
		// update velocity
		if(this.veloX != 0 || this.veloY != 0 || this.veloZ != 0){
			final float newX = this.x + this.veloX;
			final float newY = this.y + this.veloY;
			final float newZ = this.z + this.veloZ;
			
			// gravity
			{
				this.veloX *= getWeight() * getAdditionalWeight();
				this.veloY = this.veloY * getWeight() - getGravity();
				this.veloY = this.veloY * getAdditionalWeight() - getAdditionalGravity();
				this.veloZ *= getWeight() * getAdditionalWeight();
			}
			
			// is hitting block
			{
				final BlockCollisionEvent block = this.getCollidingBlock(newX, newY, newZ, 0.1F, getBlockCollisionWidth(), getBlockCollisionHeight());
				
				if(block != null){
					this.x = block.getExactX();
					this.y = block.getExactY();
					this.z = block.getExactZ();
					this.veloX = 0;
					this.veloY = 0;
					this.veloZ = 0;
					
				}else{
					this.x = newX;
					this.y = newY;
					this.z = newZ;
				}
			}
			
			// send packet
			this.sendLocation(this.getWorld().getPlayer(), !ticked);
		}
		
		ticked = true;
	}
	
	@Override
	public void onTeleport(){
		
	}
	
	public float getGravity(){
		return 0.03F;
	}
	
	public float getWeight(){
		return 0.99F /* 0.8 in water */;
	}
	
	public float getAdditionalGravity(){
		return 0F;
	}
	
	public float getAdditionalWeight(){
		return 1F;
	}
	
	protected float getBlockCollisionWidth(){
		return getType().getWidth();
	}
	
	protected float getBlockCollisionHeight(){
		return getType().getWidth();
	}
}