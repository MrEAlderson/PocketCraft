package de.marcely.pocketcraft.java.component.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagFloat extends NBTNumber<Float> {
	
	public NBTTagFloat(){
		this(0F);
	}
	
	public NBTTagFloat(float data){
		super(data);
	}
	
	@Override
	public byte getType(){
		return FLOAT;
	}

	@Override
	public void write(DataOutput stream) throws IOException {
		stream.writeFloat(this.data);
	}

	@Override
	public void read(DataInput stream) throws IOException {
		this.data = stream.readFloat();
	}

	@Override
	public String toString(){
		return this.data + "f";
	}
}
