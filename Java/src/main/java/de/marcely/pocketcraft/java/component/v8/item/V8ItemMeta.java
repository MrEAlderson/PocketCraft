package de.marcely.pocketcraft.java.component.v8.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.java.component.ItemMeta;
import de.marcely.pocketcraft.java.component.ItemMetaType;
import de.marcely.pocketcraft.java.component.nbt.NBTBase;
import de.marcely.pocketcraft.java.component.nbt.NBTTagCompound;
import de.marcely.pocketcraft.java.component.nbt.NBTTagString;
import de.marcely.pocketcraft.java.component.v8.V8Enchantment;

public abstract class V8ItemMeta extends ItemMeta {

	private static final String TAG_ENCHANTMENTS = "ench";
	private static final String TAG_DISPLAY = "display";
	
	private static final String TAG_DISPLAY_NAME = "Name";
	private static final String TAG_DISPLAY_LORE = "Lore";
	
	public V8ItemMeta(NBTTagCompound nbt){
		super(nbt);
	}
	
	public @Nullable Short getEnchantmentLevel(V8Enchantment ench){
		final List<NBTBase<?>> enchants = this.nbt.getList(TAG_ENCHANTMENTS);
		
		if(enchants == null)
			return null;
		
		final int id = ench.getId();
		
		for(NBTBase<?> rawEnchData:enchants){
			final NBTTagCompound enchData = (NBTTagCompound) rawEnchData;
			
			if(enchData.getShort("id") == id)
				return enchData.getShort("lvl");
		}
		
		return null;
	}
	
	public void addEnchantment(V8Enchantment ench, int level){
		final int id = ench.getId();
		List<NBTBase<?>> enchants = this.nbt.getList(TAG_ENCHANTMENTS);
		
		if(enchants == null)
			this.nbt.addList(TAG_ENCHANTMENTS, enchants = new ArrayList<>());
		
		// first try to replace the old one
		for(NBTBase<?> rawEnchData:enchants){
			final NBTTagCompound enchData = (NBTTagCompound) rawEnchData;
			
			if(enchData.getShort("id") == id){
				enchData.addShort("lvl", (short) level);
				
				return;
			}
		}
		
		// there's no instance; create one
		final NBTTagCompound compound = new NBTTagCompound();
		
		compound.addShort("id", (short) id);
		compound.addShort("lvl", (short) level);
		
		enchants.add(compound);
	}
	
	/**
	 * @return The level of the enchantment. null if the enchantment doesn't exist
	 */
	public @Nullable Short removeEnchantment(V8Enchantment ench){
		final List<NBTBase<?>> enchants = this.nbt.getList(TAG_ENCHANTMENTS);
		
		if(enchants == null)
			return null;
		
		final int id = ench.getId();
		final Iterator<NBTBase<?>> it = enchants.iterator();
		
		while(it.hasNext()){
			final NBTTagCompound enchData = (NBTTagCompound) it.next();
			
			if(enchData.getShort("id") == id){
				it.remove();
				
				return enchData.getShort("lvl");
			}
		}
		
		return null;
	}
	
	public List<Entry<V8Enchantment, Short>> getEnchantments(){
		final List<NBTBase<?>> enchants = this.nbt.getList(TAG_ENCHANTMENTS);
		
		if(enchants == null)
			return Collections.emptyList();
		
		final List<Entry<V8Enchantment, Short>> list = new ArrayList<>(enchants.size());
		
		for(NBTBase<?> rawEnchData:enchants){
			final NBTTagCompound enchData = (NBTTagCompound) rawEnchData;
			final V8Enchantment ench = V8Enchantment.fromId(enchData.getShort("id"));
			
			if(ench != null)
				list.add(new SimpleEntry<>(ench, enchData.getShort("lvl")));
		}
		
		return list;
	}
	
	public boolean hasCustomName(){
		final NBTTagCompound displayCompound = this.nbt.getCompound(TAG_DISPLAY);
		
		return displayCompound != null && displayCompound.has(TAG_DISPLAY_NAME);
	}
	
	public @Nullable String getCustomName(){
		final NBTTagCompound displayCompound = this.nbt.getCompound(TAG_DISPLAY);
		
		if(displayCompound == null)
			return null;
		
		return displayCompound.getString(TAG_DISPLAY_NAME);
	}
	
	public void setCustomName(String name){
		NBTTagCompound displayCompound = this.nbt.getCompound(TAG_DISPLAY);
		
		if(displayCompound == null)
			this.nbt.addCompound(TAG_DISPLAY, displayCompound = new NBTTagCompound());
		
		displayCompound.addString(TAG_DISPLAY_NAME, name);
	}
	
	public boolean removeCustomName(){
		final NBTTagCompound displayCompound = this.nbt.getCompound(TAG_DISPLAY);
		
		if(displayCompound == null)
			return false;
		
		return displayCompound.remove(TAG_DISPLAY_NAME) != null;
	}
	
	public boolean hasLore(){
		final NBTTagCompound displayCompound = this.nbt.getCompound(TAG_DISPLAY);
		
		return displayCompound != null && displayCompound.has(TAG_DISPLAY_LORE);
	}
	
	public @Nullable List<String> getLore(){
		final NBTTagCompound displayCompound = this.nbt.getCompound(TAG_DISPLAY);
		
		if(displayCompound == null)
			return null;
		
		final List<NBTBase<?>> lore = displayCompound.getList(TAG_DISPLAY_LORE);
		
		if(lore == null)
			return null;
		
		return lore.stream().map(line -> (String) line.get()).collect(Collectors.toList());
	}
	
	public void setLore(List<String> lore){
		NBTTagCompound displayCompound = this.nbt.getCompound(TAG_DISPLAY);
		
		if(displayCompound == null)
			this.nbt.addCompound(TAG_DISPLAY, displayCompound = new NBTTagCompound());
		
		displayCompound.addList(TAG_DISPLAY_LORE,
				lore.stream().map(line -> new NBTTagString(line)).collect(Collectors.toList()));
	}
	
	public boolean removeLore(){
		final NBTTagCompound displayCompound = this.nbt.getCompound(TAG_DISPLAY);
		
		if(displayCompound == null)
			return false;
		
		return displayCompound.remove(TAG_DISPLAY_LORE) != null;
	}
	
	public static V8ItemMeta newInstance(int itemType, NBTTagCompound nbt){
		if(itemType <= 0)
			return null;
		
		switch(itemType){
		default:
			return new V8ItemMeta(nbt){
				
				public ItemMetaType getType(){
					return ItemMetaType.BASIC;
				}
			};
		}
	}
}
