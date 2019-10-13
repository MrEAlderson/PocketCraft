package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntityMovingBlock extends BlockEntity {
	
	@Getter @Setter private float movingBlockId;
	@Getter @Setter private float movingBlockData;
	@Getter @Setter private int pistonPosX, pistonPosY, pistonPosZ;
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.MOVING_BLOCK;
	}

	@Override
	protected void _write(NBTCompound nbt){
		nbt.addFloat("movingBlockId", this.movingBlockId);
		nbt.addFloat("movingBlockData", this.movingBlockData);
		nbt.addInt("pistonPosX", this.pistonPosX);
		nbt.addInt("pistonPosY", this.pistonPosY);
		nbt.addInt("pistonPosZ", this.pistonPosZ);
	}

	@Override
	protected void _read(NBTCompound nbt){
		this.movingBlockId = nbt.get("movingBlockId").getValue(float.class);
		this.movingBlockData = nbt.get("movingBlockData").getValue(float.class);
		this.pistonPosX = nbt.get("pistonPosX").getValue(int.class);
		this.pistonPosY = nbt.get("pistonPosY").getValue(int.class);
		this.pistonPosZ = nbt.get("pistonPosZ").getValue(int.class);
	}
}
