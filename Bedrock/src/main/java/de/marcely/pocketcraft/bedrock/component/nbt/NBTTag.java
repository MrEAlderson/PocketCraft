package de.marcely.pocketcraft.bedrock.component.nbt;

import java.nio.ByteOrder;

import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTNumericValue;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValue;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValueString;
import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;
import lombok.Getter;

public class NBTTag {
	
	@Getter private final String name;
	@Getter private final NBTValue<?> value;
	
	public NBTTag(String name, NBTValue<?> value){
		this.name = name;
		this.value = value;
	}
	
	public void write(ByteArrayWriter stream, ByteOrder order) throws Exception {
		stream.writeSignedByte(this.value.getID());
		
		if(this.value.getID() == NBTValue.TYPE_END) return;
		
		NBTValueString.writeString(stream, order, this.name);
		
		if(!(value instanceof NBTNumericValue))
			value.write(stream);
		else
			((NBTNumericValue<?>) value).write(stream, order);
	}
	
	@SuppressWarnings("unchecked")
	public <T>T getValue(Class<T> clazz){
		return (T) this.value;
	}
	
	public static NBTTag read(ByteArrayReader stream, ByteOrder order) throws Exception {
		final byte type = stream.readSignedByte();
		if(type == NBTValue.TYPE_END) return new NBTTag(null, NBTValue.newInstance(NBTValue.TYPE_END));
		final String name = NBTValueString.readString(stream, order);
		final NBTValue<?> value = NBTValue.newInstance(type);
		
		if(!(value instanceof NBTNumericValue))
			value.read(stream);
		else
			((NBTNumericValue<?>) value).read(stream, order);
		
		return new NBTTag(name, value);
	}
}
