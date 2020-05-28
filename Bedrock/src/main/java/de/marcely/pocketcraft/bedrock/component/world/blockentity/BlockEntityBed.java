package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntityBed extends BlockEntity {
	
	@Getter @Setter private byte color;
	
	public BlockEntityBed(int x, int y, int z){
		super(x, y, z);
	}
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.BED;
	}

	@Override
	protected void _write(NBTCompound nbt){
		nbt.addByte("color", this.color);
	}

	@Override
	protected void _read(NBTCompound nbt){
		this.color = nbt.get("color").getValueData();
	}
}
