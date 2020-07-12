package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntityMovingBlock extends BlockEntity {
	
	@Getter @Setter private float movingBlockId;
	@Getter @Setter private float movingBlockData;
	@Getter @Setter private int pistonPosX, pistonPosY, pistonPosZ;
	
	public BlockEntityMovingBlock(int x, int y, int z){
		super(x, y, z);
	}
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.MOVING_BLOCK;
	}

	@Override
	protected void _write(BNBTCompound nbt){
		nbt.addFloat("movingBlockId", this.movingBlockId);
		nbt.addFloat("movingBlockData", this.movingBlockData);
		nbt.addInt("pistonPosX", this.pistonPosX);
		nbt.addInt("pistonPosY", this.pistonPosY);
		nbt.addInt("pistonPosZ", this.pistonPosZ);
	}

	@Override
	protected void _read(BNBTCompound nbt){
		this.movingBlockId = nbt.get("movingBlockId").getValueData();
		this.movingBlockData = nbt.get("movingBlockData").getValueData();
		this.pistonPosX = nbt.get("pistonPosX").getValueData();
		this.pistonPosY = nbt.get("pistonPosY").getValueData();
		this.pistonPosZ = nbt.get("pistonPosZ").getValueData();
	}
}
