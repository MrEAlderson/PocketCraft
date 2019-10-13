package de.marcely.pocketcraft.java.component.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class NBTTagLongArray extends NBTBase<long[]> {
	
	public NBTTagLongArray(){
		this(new long[0]);
	}
	
	public NBTTagLongArray(long[] data){
		super(data);
	}
	
	@Override
	public byte getType(){
		return LONG_ARRAY;
	}

	@Override
	public void write(DataOutput stream) throws IOException {
		stream.writeInt(this.data.length);
		
		for(int i=0; i<this.data.length; i++)
			stream.writeLong(this.data[i]);
	}

	@Override
	public void read(DataInput stream) throws IOException {
		{
			final int length = stream.readInt();
	
			if(length >= 16777216)
				throw new IOException("Long array is too large! (" + this.data.length + ")");
			
			this.data = new long[length];
		}
		
		for(int i=0; i<this.data.length; i++)
			this.data[i] = stream.readLong();
	}

	@Override
	public String toString(){
		return "[" + Arrays.stream(this.data).mapToObj(String::valueOf).collect(Collectors.joining(",")) + "]";
	}
}
