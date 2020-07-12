package de.marcely.pocketcraft.bedrock.component.inventory.item;

import java.io.IOException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.component.inventory.Enchantment;
import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValueCompound;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValueString;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;

import static de.marcely.pocketcraft.bedrock.component.inventory.item.ItemId.*;

public abstract class ItemMeta {
	
	private static final String TAG_ENCHANTMENTS = "ench";
	private static final String TAG_DISPLAY = "display";
	
	private static final String TAG_DISPLAY_NAME = "Name";
	private static final String TAG_DISPLAY_LORE = "Lore";
	
	protected final NBTCompound nbt;
	
	ItemMeta(NBTCompound nbt){
		this.nbt = nbt;
	}
	
	public abstract ItemMetaType getType();
	
	
	void write(EByteArrayWriter stream) throws IOException {
		if(this.nbt.getTags().isEmpty()){
			stream.writeLShort(0);
			return;
		}
		
		stream.writeLShort(0xFFFF);
		stream.writeSignedByte((byte) 0x1);
		this.nbt.write(stream, ByteOrder.LITTLE_ENDIAN, true);
	}
	

	
	public @Nullable Short getEnchantmentLevel(Enchantment ench){
		final List<NBTValueCompound> enchants = this.nbt.getList(TAG_ENCHANTMENTS);
		
		if(enchants == null)
			return null;
		
		final int id = ench.getId();
		
		for(NBTValueCompound enchData:enchants){
			if(enchData.getData().getShort("id") == id)
				return enchData.getData().getShort("lvl");
		}
		
		return null;
	}
	
	public void addEnchantment(Enchantment ench, int level){
		final int id = ench.getId();
		List<NBTValueCompound> enchants = this.nbt.getList(TAG_ENCHANTMENTS);
		
		if(enchants == null)
			this.nbt.addList(TAG_ENCHANTMENTS, enchants = new ArrayList<>());
		
		// first try to replace the old one
		for(NBTValueCompound enchData:enchants){
			if(enchData.getData().getShort("id") == id){
				enchData.getData().addShort("lvl", level);
				
				return;
			}
		}
		
		// there's no instance; create one
		final NBTValueCompound compound = new NBTValueCompound();
		
		compound.getData().addShort("id", id);
		compound.getData().addShort("lvl", level);
		
		enchants.add(compound);
	}
	
	/**
	 * @return The level of the enchantment. null if the enchantment doesn't exist
	 */
	public @Nullable Short removeEnchantment(Enchantment ench){
		final List<NBTValueCompound> enchants = this.nbt.getList(TAG_ENCHANTMENTS);
		
		if(enchants == null)
			return null;
		
		final int id = ench.getId();
		final Iterator<NBTValueCompound> it = enchants.iterator();
		
		while(it.hasNext()){
			final NBTValueCompound enchData = it.next();
			
			if(enchData.getData().getShort("id") == id){
				it.remove();
				
				return enchData.getData().getShort("lvl");
			}
		}
		
		return null;
	}
	
	public List<Entry<Enchantment, Short>> getEnchantments(){
		final List<NBTValueCompound> enchants = this.nbt.getList(TAG_ENCHANTMENTS);
		
		if(enchants == null)
			return Collections.emptyList();
		
		final List<Entry<Enchantment, Short>> list = new ArrayList<>(enchants.size());
		
		for(NBTValueCompound enchData:enchants){
			final Enchantment ench = Enchantment.fromId(enchData.getData().getShort("id"));
			
			if(ench != null)
				list.add(new SimpleEntry<>(ench, enchData.getData().getShort("lvl")));
		}
		
		return list;
	}
	
	public boolean hasCustomName(){
		final NBTCompound displayCompound = this.nbt.getCompound(TAG_DISPLAY);
		
		return displayCompound != null && displayCompound.contains(TAG_DISPLAY_NAME);
	}
	
	public @Nullable String getCustomName(){
		final NBTCompound displayCompound = this.nbt.getCompound(TAG_DISPLAY);
		
		if(displayCompound == null)
			return null;
		
		return displayCompound.getString(TAG_DISPLAY_NAME);
	}
	
	public void setCustomName(String name){
		NBTCompound displayCompound = this.nbt.getCompound(TAG_DISPLAY);
		
		if(displayCompound == null)
			this.nbt.addCompound(TAG_DISPLAY, displayCompound = new NBTCompound());
		
		displayCompound.addString(TAG_DISPLAY_NAME, name);
	}
	
	public boolean removeCustomName(){
		final NBTCompound displayCompound = this.nbt.getCompound(TAG_DISPLAY);
		
		if(displayCompound == null)
			return false;
		
		return displayCompound.remove(TAG_DISPLAY_NAME) != null;
	}
	
	public boolean hasLore(){
		final NBTCompound displayCompound = this.nbt.getCompound(TAG_DISPLAY);
		
		return displayCompound != null && displayCompound.contains(TAG_DISPLAY_LORE);
	}
	
	public @Nullable List<String> getLore(){
		final NBTCompound displayCompound = this.nbt.getCompound(TAG_DISPLAY);
		
		if(displayCompound == null)
			return null;
		
		final List<NBTValueString> lore = displayCompound.getList(TAG_DISPLAY_LORE);
		
		if(lore == null)
			return null;
		
		return lore.stream().map(line -> line.getData()).collect(Collectors.toList());
	}
	
	public void setLore(List<String> lore){
		NBTCompound displayCompound = this.nbt.getCompound(TAG_DISPLAY);
		
		if(displayCompound == null)
			this.nbt.addCompound(TAG_DISPLAY, displayCompound = new NBTCompound());
		
		displayCompound.addList(TAG_DISPLAY_LORE,
				lore.stream().map(line -> new NBTValueString(line)).collect(Collectors.toList()));
	}
	
	public boolean removeLore(){
		final NBTCompound displayCompound = this.nbt.getCompound(TAG_DISPLAY);
		
		if(displayCompound == null)
			return false;
		
		return displayCompound.remove(TAG_DISPLAY_LORE) != null;
	}
	
	static ItemMeta newInstance(int typeId, NBTCompound nbt){
		switch(typeId){
		case LEATHER_HELMET:
		case LEATHER_CHESTPLATE:
		case LEATHER_LEGGINGS:
		case LEATHER_BOOTS:
			return new LeatherArmorMeta(nbt);
		
		default:
			return new ItemMeta(nbt){
				
				public ItemMetaType getType(){
					return ItemMetaType.BASIC;
				}
			};
		}
	}
}
