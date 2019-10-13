package de.marcely.pocketcraft.java.component.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagInt extends NBTNumber<Integer> {
	
	public NBTTagInt(){
		this(0);
	}
	
	public NBTTagInt(int data){
		super(data);
	}
	
	@Override
	public byte getType(){
		return INT;
	}

	@Override
	public void write(DataOutput stream) throws IOException {
		stream.writeInt(this.data);
	}

	@Override
	public void read(DataInput stream) throws IOException {
		this.data = stream.readInt();
	}

	@Override
	public String toString(){
		return "" + this.data;
	}
}
