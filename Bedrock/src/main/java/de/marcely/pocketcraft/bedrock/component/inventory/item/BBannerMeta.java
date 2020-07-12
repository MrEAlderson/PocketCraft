package de.marcely.pocketcraft.bedrock.component.inventory.item;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTCompound;

// TODO
public class BBannerMeta extends BItemMeta {

	BBannerMeta(BNBTCompound nbt){
		super(nbt);
	}

	@Override
	public BItemMetaType getType(){
		return BItemMetaType.BANNER;
	}
}
