package de.marcely.pocketcraft.java.component.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.jetbrains.annotations.Nullable;

public class NBTTagList extends NBTBase<List<NBTBase<?>>> implements Iterable<NBTBase<?>> {
	
	public NBTTagList(){
		this(new ArrayList<>());
	}
	
	public NBTTagList(List<NBTBase<?>> data){
		super(data);
	}
	
	@Override
	public byte getType(){
		return LIST;
	}

	@Override
	public void write(DataOutput stream) throws IOException {
		stream.writeByte(this.data.isEmpty() ? 0 : this.data.get(0).getType());
		stream.writeInt(this.data.size());
		
		for(int i=0; i<this.data.size(); i++)
			this.data.get(i).write(stream);
	}

	@Override
	public void read(DataInput stream) throws IOException {
		this.data.clear();
		
		final byte typeId = stream.readByte();
		final int size = stream.readInt();
		
		for(int i=0; i<size; i++){
			final NBTBase<?> tag = NBTBase.newInstance(typeId);
			
			tag.read(stream);
			
			this.data.add(tag);
		}
	}

	@Override
	public String toString(){
		final StringBuilder builder = new StringBuilder("[");
		
		for(int i=0; i<this.data.size(); i++){
			if(i != 0)
				builder.append(",");
			
			builder.append(i + ":").append(this.data.get(i).toString());
		}
		
		return builder.append("]").toString();
	}
	
	@Override
	public Iterator<NBTBase<?>> iterator(){
		return this.data.iterator();
	}
	
	public void add(NBTBase<?> entry){
		this.data.add(entry);
	}
	
	public boolean remove(NBTBase<?> entry){
		return this.data.remove(entry);
	}
	
	public @Nullable NBTBase<?> get(int index){
		return this.data.get(index);
	}
	
	public void clear(){
		this.data.clear();
	}
	
	public int size(){
		return this.data.size();
	}
	
	public Stream<NBTBase<?>> stream(){
		return this.data.stream();
	}
}
