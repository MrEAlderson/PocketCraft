package de.marcely.pocketcraft.java.component.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jetbrains.annotations.Nullable;

public class NBTTagCompound extends NBTBase<Map<String, NBTBase<?>>> {
	
	public NBTTagCompound(){
		this(new HashMap<>());
	}
	
	public NBTTagCompound(Map<String, NBTBase<?>> data){
		super(data);
	}
	
	@Override
	public byte getType(){
		return COMPOUND;
	}

	@Override
	public void write(DataOutput stream) throws IOException {
		for(Entry<String, NBTBase<?>> e:this.data.entrySet()){
			final String key = e.getKey();
			final NBTBase<?> tag = e.getValue();
			
			stream.writeByte(tag.getType());
			
			if(tag.getType() == END)
				return;
			
			stream.writeUTF(key);
			tag.write(stream);
		}
		
		stream.writeByte(END);
	}

	@Override
	public void read(DataInput stream) throws IOException {
		this.data.clear();
		
		while(true){
			final byte type = stream.readByte();
			
			if(type == END)
				return;
			
			final String key = stream.readUTF();
			final NBTBase<?> tag = NBTBase.newInstance(type);
			
			tag.read(stream);
			
			this.data.put(key, tag);
		}
	}

	@Override
	public String toString(){
		final StringBuilder builder = new StringBuilder("{");
		
		for(Entry<String, NBTBase<?>> e:this.data.entrySet()){
			if(builder.length() != 1)
				builder.append(",");
			
			builder.append(e.getKey() + ":").append(e.getValue().toString());
		}
		
		return builder.append("}").toString();
	}
	
	public Set<Entry<String, NBTBase<?>>> entrySet(){
		return this.data.entrySet();
	}
	
	public void add(String key, NBTBase<?> entry){
		this.data.put(key, entry);
	}
	
	public void addString(String key, String value){
		this.data.put(key, new NBTTagString(value));
	}
	
	public void addByte(String key, byte value){
		this.data.put(key, new NBTTagByte(value));
	}
	
	public void addShort(String key, short value){
		this.data.put(key, new NBTTagShort(value));
	}
	
	public void addInt(String key, int value){
		this.data.put(key, new NBTTagInt(value));
	}
	
	public void addLong(String key, long value){
		this.data.put(key, new NBTTagLong(value));
	}
	
	public void addFloat(String key, float value){
		this.data.put(key, new NBTTagFloat(value));
	}
	
	public void addDouble(String key, double value){
		this.data.put(key, new NBTTagDouble(value));
	}
	
	public void addByteArray(String key, byte[] value){
		this.data.put(key, new NBTTagByteArray(value));
	}
	
	public void addIntArray(String key, int[] value){
		this.data.put(key, new NBTTagIntArray(value));
	}
	
	public void addLongArray(String key, long[] value){
		this.data.put(key, new NBTTagLongArray(value));
	}
	
	public void addCompound(String key, NBTTagCompound value){
		this.data.put(key, value);
	}
	
	public void addList(String key, List<NBTBase<?>> value){
		this.data.put(key, new NBTTagList(value));
	}
	
	public @Nullable NBTBase<?> remove(String key){
		return this.data.remove(key);
	}
	
	public @Nullable NBTBase<?> get(String key){
		return this.data.get(key);
	}
	
	public @Nullable String getString(String key){
		final NBTBase<?> base = this.data.get(key);
		
		return base != null ? (String) base.get() : null;
	}
	
	public @Nullable Byte getByte(String key){
		final NBTBase<?> base = this.data.get(key);
		
		return base != null ? (Byte) base.get() : null;
	}
	
	public @Nullable Short getShort(String key){
		final NBTBase<?> base = this.data.get(key);
		
		return base != null ? (Short) base.get() : null;
	}
	
	public @Nullable Integer getInt(String key){
		final NBTBase<?> base = this.data.get(key);
		
		return base != null ? (Integer) base.get() : null;
	}
	
	public @Nullable Long getLong(String key){
		final NBTBase<?> base = this.data.get(key);
		
		return base != null ? (Long) base.get() : null;
	}
	
	public @Nullable Float getFloat(String key){
		final NBTBase<?> base = this.data.get(key);
		
		return base != null ? (Float) base.get() : null;
	}
	
	public @Nullable Double getDouble(String key){
		final NBTBase<?> base = this.data.get(key);
		
		return base != null ? (Double) base.get() : null;
	}
	
	public @Nullable byte[] getByteArray(String key){
		final NBTBase<?> base = this.data.get(key);
		
		return base != null ? (byte[]) base.get() : null;
	}
	
	public @Nullable int[] getIntArray(String key){
		final NBTBase<?> base = this.data.get(key);
		
		return base != null ? (int[]) base.get() : null;
	}
	
	public @Nullable long[] getLongArray(String key){
		final NBTBase<?> base = this.data.get(key);
		
		return base != null ? (long[]) base.get() : null;
	}
	
	public @Nullable NBTTagCompound getCompound(String key){
		final NBTBase<?> base = this.data.get(key);
		
		return base != null ? (NBTTagCompound) base : null;
	}
	
	@SuppressWarnings("unchecked")
	public @Nullable List<NBTBase<?>> getList(String key){
		final NBTBase<?> base = this.data.get(key);
		
		return base != null ? (List<NBTBase<?>>) base.get() : null;
	}
	
	public boolean has(String key){
		return this.data.containsKey(key);
	}
	
	public void clear(){
		this.data.clear();
	}
	
	public int size(){
		return this.data.size();
	}
}
