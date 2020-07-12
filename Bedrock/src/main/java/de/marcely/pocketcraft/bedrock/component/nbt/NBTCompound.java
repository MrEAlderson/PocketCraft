package de.marcely.pocketcraft.bedrock.component.nbt;

import java.io.IOException;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValue;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValueByte;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValueByteArray;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValueCompound;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValueDouble;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValueFloat;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValueInt;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValueIntArray;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValueList;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValueLong;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValueShort;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValueString;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import lombok.Getter;

public class NBTCompound {
	
	@Getter private final Map<String, NBTTag> tags = new HashMap<>();
	
	public void write(EByteArrayWriter stream, ByteOrder order, boolean isNetwork){
		final NBTByteBuf buf = new NBTByteBuf(order, isNetwork);
		
		try{
			write(buf);
			
			try{
				stream.write(buf.array());
			}catch(IOException e){
				e.printStackTrace();
			}
		}finally{
			buf.release();
		}
	}
	
	public void write(NBTByteBuf stream){
		for(NBTTag tag:tags.values())
			tag.write(stream);
		
		stream.writeByte(NBTValue.TYPE_END);
	}
	
	public void read(NBTByteBuf stream){
		this.tags.clear();
		
		NBTTag tag = null;
		
		while((tag = NBTTag.read(stream)) != null && tag.getValue().getType() != NBTValue.TYPE_END)
			add(tag);
	}
	
	public void add(NBTTag tag){
		this.tags.put(tag.getName(), tag);
	}
	
	public void remove(NBTTag tag){
		this.tags.remove(tag.getName());
	}
	
	public @Nullable NBTTag remove(String name){
		return this.tags.remove(name);
	}
	
	public boolean contains(String name){
		return this.tags.containsKey(name);
	}
	
	public @Nullable NBTTag get(String name){
		return this.tags.get(name);
	}
	
	public void addByte(String name, int value){
		add(new NBTTag(name, new NBTValueByte((byte) value)));
	}
	
	public void addShort(String name, int value){
		add(new NBTTag(name, new NBTValueShort((short) value)));
	}
	
	public void addInt(String name, int value){
		add(new NBTTag(name, new NBTValueInt(value)));
	}
	
	public void addLong(String name, long value){
		add(new NBTTag(name, new NBTValueLong(value)));
	}
	
	public void addFloat(String name, float value){
		add(new NBTTag(name, new NBTValueFloat(value)));
	}
	
	public void addDouble(String name, double value){
		add(new NBTTag(name, new NBTValueDouble(value)));
	}
	
	public void addByteArray(String name, byte[] value){
		add(new NBTTag(name, new NBTValueByteArray(value)));
	}
	
	public void addString(String name, String value){
		add(new NBTTag(name, new NBTValueString(value)));
	}
	
	public <T extends NBTValue<?>> void addList(String name, List<T> value){
		add(new NBTTag(name, new NBTValueList(value)));
	}
	
	public void addCompound(String name, NBTCompound value){
		add(new NBTTag(name, new NBTValueCompound(value)));
	}
	
	public void addIntArray(String name, int[] value){
		add(new NBTTag(name, new NBTValueIntArray(value)));
	}
	
	@SuppressWarnings("unchecked")
	private @Nullable <T>T getValue(String name){
		final NBTTag tag = get(name);
		
		return tag != null ? (T) tag.getValue().getData() : null;
	}
	
	public @Nullable Byte getByte(String name){
		return getValue(name);
	}
	
	public @Nullable Short getShort(String name){
		return getValue(name);
	}
	
	public @Nullable Integer getInt(String name){
		return getValue(name);
	}
	
	public @Nullable Long getLong(String name){
		return getValue(name);
	}
	
	public @Nullable Float getFloat(String name){
		return getValue(name);
	}
	
	public @Nullable Double getDouble(String name){
		return getValue(name);
	}
	
	public @Nullable byte[] getByteArray(String name){
		return getValue(name);
	}
	
	public @Nullable String getString(String name){
		return getValue(name);
	}
	
	public @Nullable <T extends NBTValue<?>> List<T> getList(String name){
		return getValue(name);
	}
	
	public @Nullable NBTCompound getCompound(String name){
		return getValue(name);
	}
	
	public @Nullable int[] getIntArray(String name){
		return getValue(name);
	}
	
	public void clear(){
		this.tags.clear();
	}
	
	@Override
	public String toString(){
		final StringBuilder builder = new StringBuilder("{");
		
		for(NBTTag tag:this.tags.values()){
			if(builder.length() != 1)
				builder.append(",");
			
			builder.append(tag.getName() + ":").append(tag.getValue().toString());
		}
		
		return builder.append("}").toString();
	}
}
