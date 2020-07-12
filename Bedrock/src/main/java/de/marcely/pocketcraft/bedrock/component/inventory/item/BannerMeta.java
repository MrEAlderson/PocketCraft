package de.marcely.pocketcraft.bedrock.component.inventory.item;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;

// TODO
public class BannerMeta extends ItemMeta {

	BannerMeta(NBTCompound nbt){
		super(nbt);
	}

	@Override
	public ItemMetaType getType(){
		return ItemMetaType.BANNER;
	}
}
