package de.marcely.pocketcraft.translate.bedrocktojava.world;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityEvent;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityAnimate;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityEvent;
import lombok.Getter;
import lombok.Setter;

public abstract class Entity extends de.marcely.pocketcraft.bedrock.component.world.entity.Entity {
	
	@Getter private final World world;
	
	@Getter @Setter protected float x, y, z, yaw, pitch, headYaw;
	@Getter @Setter protected float veloX, veloY, veloZ;
	@Getter @Setter protected boolean isOnGround;
	
	@Getter @Setter private int vehicleEntityId = -1;
	
	public Entity(World world, int id){
		super(id);
		
		this.world = world;
	}
	
	public void tick(){ }
	
	public void onTeleport(){ }
	
	public int getId(){
		return (int) this.getLongId();
	}
	
	public float getBedrockPacketYAppend(){
		return 0F;
	}
	
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
}
