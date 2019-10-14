package de.marcely.pocketcraft.translate.bedrocktojava.world;

public abstract class Entity extends de.marcely.pocketcraft.bedrock.component.world.entity.Entity {

	public Entity(int id){
		super(id);
	}
	
	public int getId(){
		return (int) this.getLongId();
	}
}
