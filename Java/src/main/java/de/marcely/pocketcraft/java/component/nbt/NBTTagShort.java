package de.marcely.pocketcraft.java.component.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagShort extends NBTNumber<Short> {
	
	public NBTTagShort(){
		this((short) 0);
	}
	
	public NBTTagShort(short data){
		super(data);
	}
	
	@Override
	public byte getType(){
		return SHORT;
	}

	@Override
	public void write(DataOutput stream) throws IOException {
		stream.writeShort(this.data);
	}

	@Override
	public void read(DataInput stream) throws IOException {
		this.data = stream.readShort();
	}

	@Override
	public String toString(){
		return this.data + "s";
	}
}
