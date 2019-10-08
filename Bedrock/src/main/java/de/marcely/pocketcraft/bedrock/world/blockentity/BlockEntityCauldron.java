package de.marcely.pocketcraft.bedrock.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntityCauldron extends BlockEntity {
	
	@Getter @Setter private short potionId;
	@Getter @Setter private byte splashPotion;
	
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
		this.potionId = nbt.get("PotionId").getValue(short.class);
		this.splashPotion = nbt.get("SplashPotion").getValue(byte.class);
	}
}
