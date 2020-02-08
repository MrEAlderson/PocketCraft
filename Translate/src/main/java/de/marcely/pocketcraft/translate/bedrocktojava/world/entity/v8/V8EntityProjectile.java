package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.translate.bedrocktojava.world.World;
import de.marcely.pocketcraft.translate.bedrocktojava.world.block.BlockInfo;

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
			this.x += this.veloX;
			this.y += this.veloY;
			this.z += this.veloZ;
			
			// gravity
			{
				this.veloX *= getWeight() * getAdditionalWeight();
				this.veloY = this.veloY * getWeight() - getGravity();
				this.veloY = this.veloY * getAdditionalWeight() - getAdditionalGravity();
				this.veloZ *= getWeight() * getAdditionalWeight();
			}
			
			// is hitting block
			{
				final BlockInfo block = this.getCollidingBlock();
				
				if(block != null){
					this.veloX = 0;
					this.veloY = 0;
					this.veloZ = 0;
				}
			}
			
			// send packet
			this.sendLocation(this.getWorld().getPlayer(), !ticked);
		}
		
		ticked = true;
	}
	
	@Override
	public void onTeleport(){
		System.out.println(":)");
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
}