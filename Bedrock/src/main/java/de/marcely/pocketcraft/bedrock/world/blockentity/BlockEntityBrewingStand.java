package de.marcely.pocketcraft.bedrock.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntityBrewingStand extends BlockEntity implements BlockEntityNameable {

	public static final int MAX_BREW_TIME = 400;
	
	@Getter @Setter private short fuelTotal, fuelAmount, brewTime;
	@Getter @Setter private String customName;
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.BREWING_STAND;
	}

	@Override
	protected void _write(NBTCompound nbt){
		nbt.addShort("FuelTotal", this.fuelTotal);
		nbt.addShort("FuelAmount", this.fuelAmount);
		
		if(this.brewTime < MAX_BREW_TIME){
			nbt.addShort("CookTime", this.brewTime);
		}
		
		if(hasCustomName()){
			nbt.addString("CustomName", this.customName);
		}
	}

	@Override
	protected void _read(NBTCompound nbt){
		this.fuelTotal = nbt.get("FuelTotal").getValue(short.class);
		this.fuelAmount = nbt.get("FuelAmount").getValue(short.class);
		
		if(nbt.contains("CookTime")){
			this.brewTime = nbt.get("CookTime").getValue(short.class);
		}
		
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
