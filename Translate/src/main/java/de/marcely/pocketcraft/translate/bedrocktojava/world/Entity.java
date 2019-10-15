package de.marcely.pocketcraft.translate.bedrocktojava.world;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityEvent;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityEvent;
import lombok.Getter;
import lombok.Setter;

public abstract class Entity extends de.marcely.pocketcraft.bedrock.component.world.entity.Entity {
	
	@Getter private final World world;
	
	@Getter @Setter private float x, y, z, yaw, pitch, headYaw;
	@Getter @Setter private boolean isOnGround;
	
	public Entity(World world, int id){
		super(id);
		
		this.world = world;
	}
	
	public int getId(){
		return (int) this.getLongId();
	}
	
	public void playEvent(EntityEvent event){
		playEvent(event, 0);
	}
	
	public void playEvent(EntityEvent event, int data){
		final PacketEntityEvent out = new PacketEntityEvent();
		
		out.entityId = this.longId;
		out.type = event;
		out.data = data;
		
		this.world.getPlayer().sendPacket(out);
	}
	
	public float getBedrockPacketYAppend(){
		return this.getType() == EntityType.PLAYER ? 1.62F : 0;
	}
}
