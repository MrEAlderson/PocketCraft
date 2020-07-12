package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTCompound;

public class BlockEntityEnderChest extends BlockEntity {
	
	public BlockEntityEnderChest(int x, int y, int z){
		super(x, y, z);
	}
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.ENDER_CHEST;
	}

	@Override
	protected void _write(BNBTCompound nbt){ }

	@Override
	protected void _read(BNBTCompound nbt){ }
}
