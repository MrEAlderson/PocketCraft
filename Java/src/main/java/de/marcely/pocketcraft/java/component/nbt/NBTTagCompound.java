package de.marcely.pocketcraft.java.component.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.HashMap;
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
	
	public @Nullable NBTBase<?> remove(String key){
		return this.data.remove(key);
	}
	
	public @Nullable NBTBase<?> get(String key){
		return this.data.get(key);
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
