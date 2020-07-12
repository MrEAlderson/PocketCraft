package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTCompound;

public class BlockEntityBanner extends BlockEntity {
	
	// TODO
	
	public BlockEntityBanner(int x, int y, int z){
		super(x, y, z);
	}

	@Override
	public BlockEntityType getType(){
		return BlockEntityType.BANNER;
	}

	@Override
	protected void _write(BNBTCompound nbt){
		
	}

	@Override
	protected void _read(BNBTCompound nbt){
		
	}
}
