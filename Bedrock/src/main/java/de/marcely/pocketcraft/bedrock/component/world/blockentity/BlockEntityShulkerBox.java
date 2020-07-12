package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntityShulkerBox extends BlockEntity implements BlockEntityNameable {
	
	@Getter @Setter private byte facing;
	@Getter @Setter private String customName;
	
	public BlockEntityShulkerBox(int x, int y, int z){
		super(x, y, z);
	}
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.SHULKER_BOX;
	}

	@Override
	protected void _write(BNBTCompound nbt){
		nbt.addByte("facing", this.facing);
		
		if(hasCustomName()){
			nbt.addString("CustomName", this.customName);
		}
	}

	@Override
	protected void _read(BNBTCompound nbt){
		this.facing = nbt.get("facing").getValueData();
		
		if(nbt.contains("CustomName")){
			this.customName = nbt.get("CustomName").getValueData();
		}
	}
	
	@Override
	public boolean hasCustomName(){
		return this.customName != null;
	}
	
	@Override
	public void removeCustomName(){
		this.customName = null;
	}
}
