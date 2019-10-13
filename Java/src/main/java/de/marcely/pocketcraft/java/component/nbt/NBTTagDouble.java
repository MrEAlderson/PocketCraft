package de.marcely.pocketcraft.java.component.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagDouble extends NBTNumber<Double> {
	
	public NBTTagDouble(){
		this(0D);
	}
	
	public NBTTagDouble(double data){
		super(data);
	}
	
	@Override
	public byte getType(){
		return DOUBLE;
	}

	@Override
	public void write(DataOutput stream) throws IOException {
		stream.writeDouble(this.data);
	}

	@Override
	public void read(DataInput stream) throws IOException {
		this.data = stream.readDouble();
	}

	@Override
	public String toString(){
		return this.data + "d";
	}
}
