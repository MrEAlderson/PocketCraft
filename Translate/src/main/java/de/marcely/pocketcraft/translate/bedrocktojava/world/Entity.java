package de.marcely.pocketcraft.translate.bedrocktojava.world;

import lombok.Getter;
import lombok.Setter;

public abstract class Entity extends de.marcely.pocketcraft.bedrock.component.world.entity.Entity {
	
	@Getter @Setter private float x, y, z, yaw, pitch, headYaw;
	@Getter @Setter private boolean isOnGround;
	
	public Entity(int id){
		super(id);
	}
	
	public int getId(){
		return (int) this.getLongId();
	}
}
