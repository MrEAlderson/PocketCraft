package de.marcely.pocketcraft.bedrock.component.nbt.value;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTByteBuf;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public abstract class BNBTValue<T> {
	
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
	
	@Getter @Setter protected T data;
	
	public BNBTValue(T value){
		this.data = value;
	}
	
	@ToString.Include
	public abstract byte getType();
	
	public abstract void write(BNBTByteBuf stream);
	
	public abstract void read(BNBTByteBuf stream);
	
	public abstract String toString();
	
	
	public static @Nullable BNBTValue<?> newInstance(byte type){
		switch(type){
		case TYPE_END:
			return new BNBTValueEnd();
		case TYPE_BYTE:
			return new BNBTValueByte(null);
		case TYPE_SHORT:
			return new BNBTValueShort(null);
		case TYPE_INT:
			return new BNBTValueInt(null);
		case TYPE_LONG:
			return new BNBTValueLong(null);
		case TYPE_FLOAT:
			return new BNBTValueFloat(null);
		case TYPE_DOUBLE:
			return new BNBTValueDouble(null);
		case TYPE_BYTE_ARRAY:
			return new BNBTValueByteArray(null);
		case TYPE_STRING:
			return new BNBTValueString(null);
		case TYPE_LIST:
			return new BNBTValueList(null);
		case TYPE_COMPOUND:
			return new BNBTValueCompound(null);
		case TYPE_INT_ARRAY:
			return new BNBTValueIntArray(null);
		default:
			return null;
		}
	}
}
