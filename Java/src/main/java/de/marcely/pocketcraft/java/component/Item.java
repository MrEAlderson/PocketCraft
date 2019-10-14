package de.marcely.pocketcraft.java.component;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.java.component.nbt.NBTTagCompound;
import lombok.Getter;
import lombok.Setter;

public class Item {
	
	@Getter @Setter private short type;
	@Getter private byte amount;
	@Getter @Setter private short data;
	
	@Nullable private NBTTagCompound nbt = null;
	
	public Item(short type){
		this(type, 1, (short) 0, null);
	}
	
	public Item(short type, int amount){
		this(type, amount, (short) 0, null);
	}
	
	public Item(short type, int amount, short data){
		this(type, amount, data, null);
	}
	
	public Item(short type, int amount, short data, @Nullable NBTTagCompound nbt){
		this.type = type;
		this.amount = (byte) amount;
		this.data = data;
		this.nbt = nbt;
	}
	
	public void setAmount(int amount){
		this.amount = (byte) amount;
	}
	
	public @Nullable NBTTagCompound getNBT(){
		return this.nbt;
	}
	
	public void setNBT(@Nullable NBTTagCompound nbt){
		this.nbt = nbt;
	}
}