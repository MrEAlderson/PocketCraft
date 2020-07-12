package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTValue;

/*
 * 
 * NOT only for numbers!
 * Also includes types which are sending numbers whereby they need the ByteOrder.
 */
public abstract class BNBTNumericValue<T extends Number> extends BNBTValue<T> {

	public BNBTNumericValue(T value){
		super(value);
	}
}
