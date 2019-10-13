package de.marcely.pocketcraft.bedrock.component.world.entity;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.utils.math.Vector3;
import lombok.Getter;

public class EntityMetadata {
	
	@Getter private final Map<EntityDataType, Object> values = new HashMap<>();
	
	public EntityMetadata(){ }
	
	public EntityMetadata(EntityDataType type, Object value){
		this.values.put(type, value);
	}
	
	private void set(EntityDataType type, Object value){
		if(!type.getValueType().isInstance(value))
			throw new InvalidParameterException("Given type expects a different class type of the given value");
		
		this.values.put(type, value);
	}
	
	public void setByte(EntityDataType type, byte value){
		set(type, value);
	}
	
	public void setShort(EntityDataType type, short value){
		set(type, value);
	}
	
	public void setInt(EntityDataType type, int value){
		set(type, value);
	}
	
	public void setLong(EntityDataType type, long value){
		set(type, value);
	}
	
	public void setFloat(EntityDataType type, float value){
		set(type, value);
	}
	
	public void setString(EntityDataType type, String value){
		set(type, value);
	}
	
	public void setVector3(EntityDataType type, Vector3 value){
		set(type, value);
	}
	
	public void setBoolean(EntityDataType type, boolean value){
		setByte(type, (byte) (value ? 1 : 0));
	}
	
	public @Nullable Object get(EntityDataType type){
		return this.values.get(type);
	}
	
	public Object getOrDefault(EntityDataType type, Object def){
		return this.values.getOrDefault(type, def);
	}
	
	public byte getByte(EntityDataType type){
		return (byte) getOrDefault(type, 0);
	}
	
	public short getShort(EntityDataType type){
		return (short) getOrDefault(type, 0);
	}
	
	public int getInt(EntityDataType type){
		return (int) getOrDefault(type, 0);
	}
	
	public long getLong(EntityDataType type){
		return (long) getOrDefault(type, 0);
	}
	
	public float getFloat(EntityDataType type){
		return (float) getOrDefault(type, 0);
	}
	
	public String getString(EntityDataType type){
		return (String) getOrDefault(type, "");
	}
	
	public boolean getBoolean(EntityDataType type){
		return ((int) getOrDefault(type, 0)) == 1;
	}
	
	public Vector3 getVector3(EntityDataType type){
		final Vector3 vec = (Vector3) get(type);
		
		return vec != null ? vec : new Vector3();
	}
	
	public void applyDefault(Entity entity){
		System.out.println(":)");
		
		setShort(EntityDataType.AIR, (short) 400);
		setShort(EntityDataType.MAX_AIR, (short) 400);
		setFloat(EntityDataType.SCALE, 1F);
		setString(EntityDataType.NAMETAG, "");
		setLong(EntityDataType.FLAGS, (long) 0);
		setLong(EntityDataType.LEAD_HOLDER_ID, (long) -1);
		//setFloat(EntityDataType.BOUNDING_BOX_WIDTH, 1F);
		//setFloat(EntityDataType.BOUNDING_BOX_HEIGHT, 1F);
		setInt(EntityDataType.HEALTH, 10);
		
		entity.setDataFlag(EntityDataType.FLAG_HAS_COLLISION, true);
		
		if(entity.getType() == EntityType.PLAYER){
			setByte(EntityDataType.PLAYER_FLAGS, (byte) 0);
			setVector3(EntityDataType.PLAYER_BED_POSITION, new Vector3());
			setString(EntityDataType.NAMETAG, "Notch");
			
			entity.setDataFlag(EntityDataType.FLAG_BREATHING, true); // false = is inside water
			entity.setDataFlag(EntityDataType.FLAG_AFFECTED_BY_GRAVITY, true);
			// entity.setDataFlag(EntityDataType.FLAG_IMMOBILE, true);
			// entity.setDataFlag(EntityDataType.FLAG_CAN_CLIMB, true);
			
			System.out.println(getLong(EntityDataType.FLAGS));
			System.out.println(getShort(EntityDataType.AIR));
		}
	}
}