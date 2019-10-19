package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityMove;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Chunk;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public abstract class V8EntityProjectile extends V8Entity {
	
	private float serverX, serverY, serverZ;
	private float localX, localY, localZ;
	
	public V8EntityProjectile(World world, int id){
		super(world, id);
	}
	
	@Override
	// TODO: Remove block collision and replace it with proper block collision checks
	public void tick(){
		super.tick();
		
		// update velocity
		if(this.veloX != 0 || this.veloY != 0 || this.veloZ != 0){
			if(this.serverX != this.x || this.serverY != this.y || this.serverZ != this.z){
				this.serverX = this.localX = this.x;
				this.serverY = this.localY = this.y;
				this.serverZ = this.localZ = this.z;
				return;
			}
			
			this.localX += this.veloX;
			this.localY += this.veloY;
			this.localZ += this.veloZ;
			
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
				
				}else if(!chunk.isTransparentBlock(((int) this.x) & 0x10, (int) this.y, ((int) this.z) & 0x10)){
					this.veloX = 0;
					this.veloY = 0;
					this.veloZ = 0;
					return;
				}
			}
			
			// send packet
			{
				final PacketEntityMove out = new PacketEntityMove();
				
				out.entityRuntimeId = this.getId();
				out.x = this.localX;
				out.y = this.localY + this.getBedrockPacketYAppend();
				out.z = this.localZ;
				out.yaw = this.yaw;
				out.headYaw = this.headYaw;
				out.pitch = this.pitch;
				out.isOnGround = this.isOnGround;
				out.isTeleport = false;
				
				this.getWorld().getPlayer().sendPacket(out);
			}
		}
	}
	
	@Override
	public void onTeleport(){
		this.veloX = 0;
		this.veloY = 0;
		this.veloZ = 0;
	}
	
	public float getGravity(){
		return 0.03F;
	}
}