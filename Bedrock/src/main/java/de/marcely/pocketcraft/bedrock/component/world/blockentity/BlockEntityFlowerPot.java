package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntityFlowerPot extends BlockEntity {
	
	@Getter @Setter private short item;
	@Getter @Setter private int data;
	
	public BlockEntityFlowerPot(int x, int y, int z){
		super(x, y, z);
	}
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.FLOWER_POT;
	}

	@Override
	protected void _write(BNBTCompound nbt){
		nbt.addShort("item", this.item);
		nbt.addInt("mData", this.data);
	}

	@Override
	protected void _read(BNBTCompound nbt){
		this.item = nbt.get("item").getValueData();
		this.data = nbt.get("mData").getValueData();
	}
}
