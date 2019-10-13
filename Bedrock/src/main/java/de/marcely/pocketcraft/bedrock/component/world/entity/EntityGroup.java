package de.marcely.pocketcraft.bedrock.component.world.entity;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.Nullable;

public enum EntityGroup {
	
	NONE(null),
	
	PROJECTILE(null),
	ARROW(null),
	FIREBALL(null),
	HANGING(null),
	LIVING(null),
	INSENTIENT(LIVING),
	AMBIENT(INSENTIENT),
	CREATURE(INSENTIENT),
	AGEABLE(CREATURE),
	ANIMAL(AGEABLE),
	ABSTRACT_HORSE(ANIMAL),
	CHESTED_HORSE(ABSTRACT_HORSE),
	TAMEABLE_ANIMAL(ANIMAL),
	GOLEM(CREATURE),
	MONSTER(CREATURE),
	GUARDIAN(MONSTER),
	ZOMBIE(MONSTER),
	FLYING(INSENTIENT),
	WATERMOB(INSENTIENT),
	MINECART(null),
	MINECART_RIDEABLE(MINECART),
	MINECART_CONTAINER(MINECART),
	ABSTRACT_SKELETON(MONSTER),
	ABSTRACT_ILLAGER(MONSTER);
	
	private final @Nullable EntityGroup parent;
	private final EntityGroup[] parents;
	
	private EntityGroup(@Nullable EntityGroup parent){
		this.parent = parent;
		
		final List<EntityGroup> parents = new ArrayList<>();
		EntityGroup current = parent;
		
		if(parent != null){
			while((current = current.parent) != null)
				parents.add(current);
		}
		
		this.parents = parents.toArray(new EntityGroup[parents.size()]);
	}
	
	public boolean isParent(EntityGroup group){
		for(int i=0; i<parents.length; i++){
			if(parents[i] == group)
				return true;
		}
		
		return false;
	}
	
	public boolean is(EntityGroup group){
		return this == group || isParent(group);
	}
}
