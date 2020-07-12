package de.marcely.pocketcraft.bedrock.component.inventory.item;

import java.awt.Color;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;

public class LeatherArmorMeta extends ItemMeta {

	private static final String TAG_CUSTOM_COLOR = "customColor";
	
	LeatherArmorMeta(NBTCompound nbt){
		super(nbt);
	}

	@Override
	public ItemMetaType getType(){
		return ItemMetaType.LEATHER_ARMOR;
	}
	
	public void setCustomColor(Color color){
		this.nbt.addInt(TAG_CUSTOM_COLOR, color.getRGB());
	}
	
	public boolean removeCustomColor(){
		return this.nbt.remove(TAG_CUSTOM_COLOR) != null;
	}
	
	public @Nullable Color getCustomColor(){
		return new Color(this.nbt.getInt(TAG_CUSTOM_COLOR));
	}
	
	public boolean hasCustomColor(){
		return this.nbt.contains(TAG_CUSTOM_COLOR);
	}
}