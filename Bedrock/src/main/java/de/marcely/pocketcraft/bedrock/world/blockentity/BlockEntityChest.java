package de.marcely.pocketcraft.bedrock.world.blockentity;

import de.marcely.pocketcraft.utils.nbt.NBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntityChest extends BlockEntity implements BlockEntityNameable {
	
	@Getter private Integer pairX, pairZ;
	@Getter @Setter private String customName;
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.CHEST;
	}

	@Override
	protected void _write(NBTCompound nbt){
		if(isPaired()){
			nbt.addInt("pairx", this.pairX);
			nbt.addInt("pairz", this.pairZ);
		}
		
		if(hasCustomName()){
			nbt.addString("CustomName", this.customName);
		}
	}

	@Override
	protected void _read(NBTCompound nbt){
		if(nbt.contains("pairx")){
			this.pairX = nbt.get("pairx").getValue(int.class);
			this.pairZ = nbt.get("pairz").getValue(int.class);
		}
		
		if(nbt.contains("CustomName")){
			this.customName = nbt.get("CustomName").getValue(String.class);
		}
	}
	
	public void pair(int x, int z){
		this.pairX = x;
		this.pairZ = z;
	}
	
	public void unpair(){
		this.pairX = null;
	}
	
	public boolean isPaired(){
		return this.pairX != null;
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
