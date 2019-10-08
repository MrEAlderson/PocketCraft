package de.marcely.pocketcraft.bedrock.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;

public class BlockEntityEnderChest extends BlockEntity {

	@Override
	public BlockEntityType getType(){
		return BlockEntityType.ENDER_CHEST;
	}

	@Override
	protected void _write(NBTCompound nbt){ }

	@Override
	protected void _read(NBTCompound nbt){ }
}
