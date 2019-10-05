package de.marcely.pocketcraft.bedrock.component.nbt.value;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;
import lombok.Getter;
import lombok.Setter;

public abstract class NBTValue<T> {
	
	public static final byte TYPE_END = 0x00;
	public static final byte TYPE_BYTE = 0x01;
	public static final byte TYPE_SHORT = 0x02;
	public static final byte TYPE_INT = 0x03;
	public static final byte TYPE_LONG = 0x04;
	public static final byte TYPE_FLOAT = 0x05;
	public static final byte TYPE_DOUBLE = 0x06;
	public static final byte TYPE_BYTE_ARRAY = 0x07;
	public static final byte TYPE_STRING = 0x08;
	public static final byte TYPE_LIST = 0x09;
	public static final byte TYPE_COMPOUND = 0x0A;
	public static final byte TYPE_INT_ARRAY = 0x0B;	
	
	@Getter @Setter protected T value;
	
	public NBTValue(T value){
		this.value = value;
	}
	
	public abstract byte getID();
	
	public abstract void write(ByteArrayWriter stream) throws Exception;
	
	public abstract void read(ByteArrayReader stream) throws Exception;
	
	public static @Nullable NBTValue<?> newInstance(byte type){
		switch(type){
		case TYPE_END:
			return new NBTValueEnd();
		case TYPE_BYTE:
			return new NBTValueByte(null);
		case TYPE_SHORT:
			return new NBTValueShort(null);
		case TYPE_INT:
			return new NBTValueInt(null);
		case TYPE_LONG:
			return new NBTValueLong(null);
		case TYPE_FLOAT:
			return new NBTValueFloat(null);
		case TYPE_DOUBLE:
			return new NBTValueDouble(null);
		case TYPE_BYTE_ARRAY:
			return new NBTValueByteArray(null);
		case TYPE_STRING:
			return new NBTValueString(null);
		case TYPE_LIST:
			return new NBTValueList(null);
		case TYPE_COMPOUND:
			return new NBTValueCompound(null);
		case TYPE_INT_ARRAY:
			return new NBTValueIntArray(null);
		default:
			return null;
		}
	}
}
