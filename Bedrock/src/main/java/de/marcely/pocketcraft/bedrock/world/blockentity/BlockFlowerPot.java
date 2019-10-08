package de.marcely.pocketcraft.bedrock.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockFlowerPot extends BlockEntity {
	
	@Getter @Setter private short item;
	@Getter @Setter private int data;
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.FLOWER_POT;
	}

	@Override
	protected void _write(NBTCompound nbt){
		nbt.addShort("item", this.item);
		nbt.addInt("mData", this.data);
	}

	@Override
	protected void _read(NBTCompound nbt){
		this.item = nbt.get("item").getValue(short.class);
		this.data = nbt.get("mData").getValue(int.class);
	}
}
