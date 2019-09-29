package de.marcely.pocketcraft.bedrock.world.entity;

public class EntityAttribute {
	
	public final EntityAttributeType type;
	public final float value;
	
	public EntityAttribute(EntityAttributeType type, float value){
		this(type, value, false);
	}
	
	public EntityAttribute(EntityAttributeType type, float value, boolean force){
		if(!force){
			if(value < type.minValue)
				throw new IndexOutOfBoundsException("Value is less than minimum");
			else if(value > type.maxValue)
				throw new IndexOutOfBoundsException("Value is higher than maximum");
		}
		
		this.type = type;
		this.value = value;
	}
}
