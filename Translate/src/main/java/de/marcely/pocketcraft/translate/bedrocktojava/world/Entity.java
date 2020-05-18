package de.marcely.pocketcraft.translate.bedrocktojava.world;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityEvent;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityAnimate;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityEvent;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityMove;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;
import de.marcely.pocketcraft.translate.bedrocktojava.world.block.BlockCollisionEvent;
import lombok.Getter;
import lombok.Setter;

public abstract class Entity extends de.marcely.pocketcraft.bedrock.component.world.entity.Entity {
	
	@Getter private final World world;
	
	@Getter @Setter protected float x, y, z;
	@Getter @Setter protected float networkX, networkY, networkZ;
	@Getter @Setter protected float yaw, pitch, headYaw;
	@Getter @Setter protected float veloX, veloY, veloZ;
	@Getter @Setter protected boolean isOnGround;
	
	@Getter @Setter private int vehicleEntityId = -1;
	
	public Entity(World world, int id){
		super(id);
		
		this.world = world;
	}
	
	public abstract List<BlockCollisionEvent> getCollidingBlocks();
	
	// childs can override these
	public void tick(){ }
	
	public void onTeleport(){ }
	
	public float getBedrockPacketYAppend(){ return 0F; }
	
	// info
	public int getId(){
		return (int) this.getLongId();
	}
	
	public TranslateComponents getTranslateComponents(){
		return this.world.getTranslateComponents();
	}
	
	// utils
	public void playEvent(EntityEvent event){
		playEvent(event, 0);
	}
	
	public void playEvent(EntityEvent event, int data){
		final PacketEntityEvent out = new PacketEntityEvent();
		
		out.entityId = this.longId;
		out.type = event.getId();
		out.data = data;
		
		this.world.getPlayer().sendPacket(out);
	}
	
	public void playAnimation(int type){
		playAnimation(type, 0F);
	}
	
	public void playAnimation(int type, float rowingTime){
		final PacketEntityAnimate out = new PacketEntityAnimate();
		
		out.entityId = this.longId;
		out.type = type;
		out.rowingTime = rowingTime;
		
		this.world.getPlayer().sendPacket(out);
	}
	
	public boolean isMounted(){
		return this.vehicleEntityId == -1;
	}
	
	public void dismount(){
		this.vehicleEntityId = -1;
	}
	
	public void sendLocation(Player player, boolean isTeleport){
		final PacketEntityMove out = new PacketEntityMove();
		
		out.entityRuntimeId = this.getId();
		out.x = this.x;
		out.y = this.y+getBedrockPacketYAppend();
		out.z = this.z;
		out.headYaw = this.headYaw;
		out.yaw = this.yaw;
		out.pitch = this.pitch;
		out.isOnGround = this.isOnGround;
		out.isTeleport = isTeleport;
		
		player.sendPacket(out);
	}
	
	public void onNetworkPositionChange(float x, float y, float z, boolean relative){
		if(relative){
			this.x = this.networkX += x;
			this.y = this.networkY += y;
			this.z = this.networkZ += z;
		
		}else{
			this.x = this.networkX = x;
			this.y = this.networkY = y;
			this.z = this.networkZ = z;
		}
	}
	
	/**
	 * Does a linear look up from the current location to the target one
	 * 
	 * @param accuracy Less = more checks
	 */
	public @Nullable BlockCollisionEvent getCollidingBlock(float newX, float newY, float newZ, float accuracy){
		final float actualX = this.x;
		final float actualY = this.y;
		final float actualZ = this.z;
		final float minX = Math.min(this.x, newX);
		final float minY = Math.min(this.y, newY);
		final float minZ = Math.min(this.z, newZ);
		final float maxX = Math.max(this.x, newX);
		final float maxY = Math.max(this.y, newY);
		final float maxZ = Math.max(this.z, newZ);
		
		for(x=minX; x<=maxX; x += accuracy){
			for(y=minY; y<=maxY; y += accuracy){
				for(z=minZ; z<=maxZ; z += accuracy){
					final List<BlockCollisionEvent> events = getCollidingBlocks();
					
					if(events != null){
						final BlockCollisionEvent event = events.get(0);
						
						event.setExact(this.x, this.y, this.z);
						
						this.x = actualX;
						this.y = actualY;
						this.z = actualZ;
						
						return event;
					}
				}
			}
		}
		
		this.x = actualX;
		this.y = actualY;
		this.z = actualZ;
		
		return null;
	}
}
