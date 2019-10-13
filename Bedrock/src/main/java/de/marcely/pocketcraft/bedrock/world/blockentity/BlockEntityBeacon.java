package de.marcely.pocketcraft.bedrock.world.blockentity;

import de.marcely.pocketcraft.utils.nbt.NBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntityBeacon extends BlockEntity {
	
	@Getter @Setter private String lock = "";
	@Getter @Setter private int levels;
	@Getter @Setter private int primary;
	@Getter @Setter private int secondary;
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.BEACON;
	}

	@Override
	protected void _write(NBTCompound nbt){
		nbt.addString("Lock", this.lock);
		nbt.addInt("Levels", this.levels);
		nbt.addInt("Primary", this.primary);
		nbt.addInt("Secondary", this.secondary);
	}

	@Override
	protected void _read(NBTCompound nbt){
		this.lock = nbt.get("Lock").getValue(String.class);
		this.levels = nbt.get("Levels").getValue(int.class);
		this.primary = nbt.get("Primary").getValue(int.class);
		this.secondary = nbt.get("Secondary").getValue(int.class);
	}
}
