package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityMove;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Chunk;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public abstract class V8EntityProjectile extends V8Entity {

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
				this.veloX *= 0.99F;
				this.veloY = this.veloY * 0.99F - getGravity();
				this.veloZ *= 0.99F;
			}
			
			// is hitting block
			{
				final Chunk chunk = this.getWorld().getChunk((int) this.x >> 4, (int) this.z >> 4);
				
				if(chunk == null){
					this.veloX = 0;
					this.veloY = 0;
					this.veloZ = 0;
					return;
				
				}else if(!chunk.isTransparentBlock((int) this.x & 0x10, (int) this.y, (int) this.z & 0x10)){
					this.veloX = 0;
					this.veloY = 0;
					this.veloZ = 0;
				}
			}
			
			// send packet
			{
				final PacketEntityMove out = new PacketEntityMove();
				
				out.entityRuntimeId = this.getId();
				out.x = this.x;
				out.y = this.y + this.getBedrockPacketYAppend();
				out.z = this.z;
				out.yaw = this.yaw;
				out.headYaw = this.headYaw;
				out.pitch = this.pitch;
				out.isOnGround = this.isOnGround;
				out.isTeleport = false;
				
				this.getWorld().getPlayer().sendPacket(out);
			}
		}
	}
	
	public float getGravity(){
		return 0.03F;
	}
}