package de.marcely.pocketcraft.java.component.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.jetbrains.annotations.Nullable;

public abstract class NBTBase<T> {
	
	public static final byte END = 0;
	public static final byte BYTE = 1;
	public static final byte SHORT = 2;
	public static final byte INT = 3;
	public static final byte LONG = 4;
	public static final byte FLOAT = 5;
	public static final byte DOUBLE = 6;
	public static final byte BYTE_ARRAY = 7;
	public static final byte STRING = 8;
	public static final byte LIST = 9;
	public static final byte COMPOUND = 10;
	public static final byte INT_ARRAY = 11;
	public static final byte LONG_ARRAY = 12;
	
	
	
	protected T data;
	
	public NBTBase(){ }
	
	public NBTBase(T data){
		this.data = data;
	}
	
	
	public abstract byte getType();
	
	public abstract void write(DataOutput stream) throws IOException;
	
	public abstract void read(DataInput stream) throws IOException;
	
	public abstract String toString();
	
	
	
	public void set(T data){
		this.data = data;
	}
	
	public T get(){
		return this.data;
	}
	
	
	
	
	public static @Nullable NBTBase<?> newInstance(byte type){
		switch(type){
		case END:
			return new NBTTagEnd();
			
		case BYTE:
			return new NBTTagByte();
			
		case SHORT:
			return new NBTTagShort();
			
		case INT:
			return new NBTTagInt();
			
		case LONG:
			return new NBTTagLong();
			
		case FLOAT:
			return new NBTTagFloat();
			
		case DOUBLE:
			return new NBTTagDouble();
			
		case BYTE_ARRAY:
			return new NBTTagByteArray();
			
		case STRING:
			return new NBTTagString();
			
		case LIST:
			return new NBTTagList();
			
		case COMPOUND:
			return new NBTTagCompound();
			
		case INT_ARRAY:
			return new NBTTagIntArray();
			
		case LONG_ARRAY:
			return new NBTTagLongArray();
			
		default:
			return null;
		}
	}
}