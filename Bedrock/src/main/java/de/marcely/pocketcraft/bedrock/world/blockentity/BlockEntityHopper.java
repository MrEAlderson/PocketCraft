package de.marcely.pocketcraft.bedrock.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntityHopper extends BlockEntity implements BlockEntityNameable {

	@Getter @Setter private String customName;
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.HOPPER;
	}

	@Override
	protected void _write(NBTCompound nbt){
		if(hasCustomName()){
			nbt.addString("CustomName", this.customName);
		}
	}

	@Override
	protected void _read(NBTCompound nbt){
		if(nbt.contains("CustomName")){
			this.customName = nbt.get("CustomName").getValue(String.class);
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
