package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntityCauldron extends BlockEntity {
	
	@Getter @Setter private short potionId = (short) 0xFFFF;
	@Getter @Setter private byte splashPotion;
	
	public BlockEntityCauldron(int x, int y, int z){
		super(x, y, z);
	}
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.CAULDRON;
	}

	@Override
	protected void _write(NBTCompound nbt){
		nbt.addShort("PotionId", this.potionId);
		nbt.addByte("SplashPotion", this.splashPotion);
	}

	@Override
	protected void _read(NBTCompound nbt){
		this.potionId = nbt.get("PotionId").getValueData();
		this.splashPotion = nbt.get("SplashPotion").getValueData();
	}
}
