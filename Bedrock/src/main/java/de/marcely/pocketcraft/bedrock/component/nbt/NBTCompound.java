package de.marcely.pocketcraft.bedrock.component.nbt;

import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import de.marcely.pocketcraft.bedrock.component.nbt.NBTTag;
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
import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;
import lombok.Getter;
import lombok.Setter;

public class NBTCompound {
	
	@Getter @Setter private ByteOrder order;
	@Getter private final String name;
	
	@Getter private final Map<String, NBTTag> tags = new HashMap<>();
	
	public NBTCompound(ByteOrder order){
		this(order, "");
	}
	
	public NBTCompound(ByteOrder order, String name){
		this.order = order;
		this.name = name;
	}
	
	public void add(NBTTag tag){
		this.tags.put(tag.getName(), tag);
	}
	
	public void remove(NBTTag tag){
		this.tags.remove(tag.getName());
	}
	
	public void remove(String name){
		this.tags.remove(name);
	}
	
	public boolean contains(String name){
		return this.tags.containsKey(name);
	}
	
	public @Nullable NBTTag get(String name){
		return this.tags.get(name);
	}
	
	public void addByte(String name, byte value){
		add(new NBTTag(name, new NBTValueByte(value)));
	}
	
	public void addShort(String name, short value){
		add(new NBTTag(name, new NBTValueShort(value)));
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
	
	public void addList(String name, List<NBTValue<?>> value){
		add(new NBTTag(name, new NBTValueList(value)));
	}
	
	public void addCompound(String name, NBTCompound value){
		add(new NBTTag(name, new NBTValueCompound(value)));
	}
	
	public void addIntArray(String name, int[] value){
		add(new NBTTag(name, new NBTValueIntArray(value)));
	}
	
	public void clear(){
		this.tags.clear();
	}
	
	public void write(ByteArrayWriter stream) throws Exception {
		stream.writeSignedByte(NBTValue.TYPE_COMPOUND);
		NBTValueString.writeString(stream, order, this.name);
		
		for(NBTTag tag:tags.values())
			tag.write(stream, order);
		
		stream.writeSignedByte(NBTValue.TYPE_END);
	}
	
	public static NBTCompound read(ByteArrayReader stream, ByteOrder order) throws Exception {
		final NBTCompound c = new NBTCompound(order);
		NBTTag tag = null;
		
		while((tag = NBTTag.read(stream, order)) != null && tag.getValue().getID() != NBTValue.TYPE_END)
			c.add(tag);
		
		return c;
	}
}
