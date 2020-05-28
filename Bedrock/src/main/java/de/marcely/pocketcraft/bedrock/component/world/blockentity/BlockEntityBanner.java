package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;

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
	protected void _write(NBTCompound nbt){
		
	}

	@Override
	protected void _read(NBTCompound nbt){
		
	}
}
