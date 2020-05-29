package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntityPistonArm extends BlockEntity {
	
	// unkown what it does
	@Getter @Setter private boolean isMovable = false;
	// unkown what state does
	@Getter @Setter private byte state = 1;
	@Getter @Setter private byte newState = 1;
	// progress causes smooth extending(1)/contracting (0)
	@Getter @Setter private float progress = 0F;
	@Getter @Setter private float lastProgress = 0F;
	// if it's extended or not
	@Getter @Setter private boolean powered = false;
	
	public BlockEntityPistonArm(int x, int y, int z){
		super(x, y, z);
	}
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.PISTON_ARM;
	}

	@Override
	protected void _write(NBTCompound nbt){
		nbt.addByte("isMovable", this.isMovable ? 1 : 0);
		nbt.addByte("State", this.state);
		nbt.addByte("NewState", this.newState);
		nbt.addFloat("Progress", this.progress);
		nbt.addFloat("LastProgress", this.lastProgress);
		nbt.addByte("powered", this.powered ? 1 : 0);
	}

	@Override
	protected void _read(NBTCompound nbt){
		this.isMovable = nbt.getByte("isMovable") == 1;
		this.state = nbt.getByte("State");
		this.newState = nbt.getByte("NewState");
		this.progress = nbt.getFloat("Progress");
		this.lastProgress = nbt.getFloat("LastProgress");
		this.powered = nbt.getByte("powered") == 1;
	}
}
