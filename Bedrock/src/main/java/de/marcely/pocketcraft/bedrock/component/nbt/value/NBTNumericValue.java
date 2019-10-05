package de.marcely.pocketcraft.bedrock.component.nbt.value;

import java.nio.ByteOrder;
import java.security.InvalidParameterException;

import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

/*
 * 
 * NOT only for numbers!
 * Also includes types which are sending numbers whereby they need the ByteOrder.
 */
public abstract class NBTNumericValue<T> extends NBTValue<T> {

	public NBTNumericValue(T value){
		super(value);
	}
	
	public abstract byte getID();
	
	public abstract void write(ByteArrayWriter stream, ByteOrder order) throws Exception;
	
	public abstract void read(ByteArrayReader stream, ByteOrder order) throws Exception;
	
	@Override
	@Deprecated
	public void write(ByteArrayWriter stream) throws Exception {
		throw new InvalidParameterException("Missing ByteOrder");
	}
	
	@Override
	@Deprecated
	public void read(ByteArrayReader stream) throws Exception {
		throw new InvalidParameterException("Missing ByteOrder");
	}
}
