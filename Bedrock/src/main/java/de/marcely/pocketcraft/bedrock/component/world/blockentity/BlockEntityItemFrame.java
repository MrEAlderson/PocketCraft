package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTCompound;

public class BlockEntityItemFrame extends BlockEntity {
	
	// TODO
	public BlockEntityItemFrame(int x, int y, int z){
		super(x, y, z);
	}
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.ITEM_FRAME;
	}

	@Override
	protected void _write(BNBTCompound nbt){
		
	}

	@Override
	protected void _read(BNBTCompound nbt){
		
	}
}
