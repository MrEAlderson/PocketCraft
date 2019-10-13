package de.marcely.pocketcraft.bedrock.world.blockentity;

import de.marcely.pocketcraft.utils.nbt.NBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntityEnchantTable extends BlockEntity implements BlockEntityNameable {

	@Getter @Setter private String customName;
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.ENCHANT_TABLE;
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
