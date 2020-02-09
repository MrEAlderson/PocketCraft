package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValue;

/*
 * 
 * NOT only for numbers!
 * Also includes types which are sending numbers whereby they need the ByteOrder.
 */
public abstract class NBTNumericValue<T extends Number> extends NBTValue<T> {

	public NBTNumericValue(T value){
		super(value);
	}
}
