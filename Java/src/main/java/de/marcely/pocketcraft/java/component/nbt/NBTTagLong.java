package de.marcely.pocketcraft.java.component.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagLong extends NBTNumber<Long> {
	
	public NBTTagLong(){
		this(0L);
	}
	
	public NBTTagLong(long data){
		super(data);
	}
	
	@Override
	public byte getType(){
		return LONG;
	}

	@Override
	public void write(DataOutput stream) throws IOException {
		stream.writeLong(this.data);
	}

	@Override
	public void read(DataInput stream) throws IOException {
		this.data = stream.readLong();
	}

	@Override
	public String toString(){
		return this.data + "L";
	}
}
