package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntityFurnace extends BlockEntity implements BlockEntityNameable {
	
	@Getter @Setter private short burnDuration;
	@Getter @Setter private short burnTime;
	@Getter @Setter private short cookTime;
	
	@Getter @Setter private String customName;
	
	public BlockEntityFurnace(int x, int y, int z){
		super(x, y, z);
	}
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.FURNACE;
	}

	@Override
	protected void _write(NBTCompound nbt){
		nbt.addShort("BurnDuration", this.burnDuration);
		nbt.addShort("BurnTime", this.burnTime);
		nbt.addShort("CookTime", this.cookTime);
		
		if(hasCustomName()){
			nbt.addString("CustomName", this.customName);
		}
	}

	@Override
	protected void _read(NBTCompound nbt){
		this.burnDuration = nbt.get("BurnDuration").getValueData();
		this.burnTime = nbt.get("BurnTime").getValueData();
		this.cookTime = nbt.get("CookTime").getValueData();
		
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
