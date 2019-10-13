package de.marcely.pocketcraft.java.component.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class NBTTagIntArray extends NBTBase<int[]> {
	
	public NBTTagIntArray(){
		this(new int[0]);
	}
	
	public NBTTagIntArray(int[] data){
		super(data);
	}
	
	@Override
	public byte getType(){
		return INT_ARRAY;
	}

	@Override
	public void write(DataOutput stream) throws IOException {
		stream.writeInt(this.data.length);
		
		for(int i=0; i<this.data.length; i++)
			stream.writeInt(this.data[i]);
	}

	@Override
	public void read(DataInput stream) throws IOException {
		{
			final int length = stream.readInt();
	
			if(length >= 16777216)
				throw new IOException("Int array is too large! (" + this.data.length + ")");
			
			this.data = new int[length];
		}
		
		for(int i=0; i<this.data.length; i++)
			this.data[i] = stream.readInt();
	}

	@Override
	public String toString(){
		return "[" + Arrays.stream(this.data).mapToObj(String::valueOf).collect(Collectors.joining(",")) + "]";
	}
}
