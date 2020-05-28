package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import lombok.Getter;
import lombok.ToString;

@ToString
public abstract class BlockEntity {
	
	@Getter private int x, y, z;
	
	public BlockEntity(int x, int y, int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public abstract BlockEntityType getType();
	
	protected abstract void _write(NBTCompound nbt);
	
	protected abstract void _read(NBTCompound nbt);
	
	public void write(NBTCompound nbt){
		nbt.addString("id", getType().getId());
		nbt.addInt("x", this.x);
		nbt.addInt("y", this.y);
		nbt.addInt("z", this.z);
		
		_write(nbt);
	}
	
	public static @Nullable BlockEntity read(NBTCompound nbt){
		final BlockEntityType type = BlockEntityType.getById(nbt.get("id").getValueData());
		
		if(type == null)
			return null;
		
		final BlockEntity entity = type.newInstance(
				nbt.get("x").getValueData(),
				nbt.get("y").getValueData(),
				nbt.get("z").getValueData());
		
		return entity;
	}
}
