package de.marcely.pocketcraft.bedrock.component.nbt;

import java.io.IOException;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTCompound;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTValue;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTValueByte;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTValueByteArray;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTValueCompound;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTValueDouble;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTValueFloat;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTValueInt;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTValueIntArray;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTValueList;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTValueLong;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTValueShort;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTValueString;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import lombok.Getter;

public class BNBTCompound {
	
	@Getter private final Map<String, BNBTTag> tags = new HashMap<>();
	
	public void write(EByteArrayWriter stream, ByteOrder order, boolean isNetwork){
		final BNBTByteBuf buf = new BNBTByteBuf(order, isNetwork);
		
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
	
	public void write(BNBTByteBuf stream){
		for(BNBTTag tag:tags.values())
			tag.write(stream);
		
		stream.writeByte(BNBTValue.TYPE_END);
	}
	
	public void read(BNBTByteBuf stream){
		this.tags.clear();
		
		BNBTTag tag = null;
		
		while((tag = BNBTTag.read(stream)) != null && tag.getValue().getType() != BNBTValue.TYPE_END)
			add(tag);
	}
	
	public void add(BNBTTag tag){
		this.tags.put(tag.getName(), tag);
	}
	
	public void remove(BNBTTag tag){
		this.tags.remove(tag.getName());
	}
	
	public @Nullable BNBTTag remove(String name){
		return this.tags.remove(name);
	}
	
	public boolean contains(String name){
		return this.tags.containsKey(name);
	}
	
	public @Nullable BNBTTag get(String name){
		return this.tags.get(name);
	}
	
	public void addByte(String name, int value){
		add(new BNBTTag(name, new BNBTValueByte((byte) value)));
	}
	
	public void addShort(String name, int value){
		add(new BNBTTag(name, new BNBTValueShort((short) value)));
	}
	
	public void addInt(String name, int value){
		add(new BNBTTag(name, new BNBTValueInt(value)));
	}
	
	public void addLong(String name, long value){
		add(new BNBTTag(name, new BNBTValueLong(value)));
	}
	
	public void addFloat(String name, float value){
		add(new BNBTTag(name, new BNBTValueFloat(value)));
	}
	
	public void addDouble(String name, double value){
		add(new BNBTTag(name, new BNBTValueDouble(value)));
	}
	
	public void addByteArray(String name, byte[] value){
		add(new BNBTTag(name, new BNBTValueByteArray(value)));
	}
	
	public void addString(String name, String value){
		add(new BNBTTag(name, new BNBTValueString(value)));
	}
	
	public <T extends BNBTValue<?>> void addList(String name, List<T> value){
		add(new BNBTTag(name, new BNBTValueList(value)));
	}
	
	public void addCompound(String name, BNBTCompound value){
		add(new BNBTTag(name, new BNBTValueCompound(value)));
	}
	
	public void addIntArray(String name, int[] value){
		add(new BNBTTag(name, new BNBTValueIntArray(value)));
	}
	
	@SuppressWarnings("unchecked")
	private @Nullable <T>T getValue(String name){
		final BNBTTag tag = get(name);
		
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
	
	public @Nullable <T extends BNBTValue<?>> List<T> getList(String name){
		return getValue(name);
	}
	
	public @Nullable BNBTCompound getCompound(String name){
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
		
		for(BNBTTag tag:this.tags.values()){
			if(builder.length() != 1)
				builder.append(",");
			
			builder.append(tag.getName() + ":").append(tag.getValue().toString());
		}
		
		return builder.append("}").toString();
	}
}
