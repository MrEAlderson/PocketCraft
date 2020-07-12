package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntityHopper extends BlockEntity implements BlockEntityNameable {

	@Getter @Setter private String customName;
	
	public BlockEntityHopper(int x, int y, int z){
		super(x, y, z);
	}
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.HOPPER;
	}

	@Override
	protected void _write(BNBTCompound nbt){
		if(hasCustomName()){
			nbt.addString("CustomName", this.customName);
		}
	}

	@Override
	protected void _read(BNBTCompound nbt){
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
