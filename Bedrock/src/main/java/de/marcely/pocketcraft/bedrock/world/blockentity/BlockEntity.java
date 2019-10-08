package de.marcely.pocketcraft.bedrock.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;

public abstract class BlockEntity {
	
	public abstract BlockEntityType getType();
	
	protected abstract void _write(NBTCompound nbt);
	
	protected abstract void _read(NBTCompound nbt);
}
