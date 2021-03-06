package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntityBeacon extends BlockEntity {
	
	@Getter @Setter private String lock = "";
	@Getter @Setter private int levels;
	@Getter @Setter private int primary;
	@Getter @Setter private int secondary;
	
	public BlockEntityBeacon(int x, int y, int z){
		super(x, y, z);
	}
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.BEACON;
	}

	@Override
	protected void _write(BNBTCompound nbt){
		nbt.addString("Lock", this.lock);
		nbt.addInt("Levels", this.levels);
		nbt.addInt("Primary", this.primary);
		nbt.addInt("Secondary", this.secondary);
	}

	@Override
	protected void _read(BNBTCompound nbt){
		this.lock = nbt.get("Lock").getValueData();
		this.levels = nbt.get("Levels").getValueData();
		this.primary = nbt.get("Primary").getValueData();
		this.secondary = nbt.get("Secondary").getValueData();
	}
}
