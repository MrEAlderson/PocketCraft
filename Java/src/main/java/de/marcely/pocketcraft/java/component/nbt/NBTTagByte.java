package de.marcely.pocketcraft.java.component.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagByte extends NBTNumber<Byte> {
	
	public NBTTagByte(){
		this((byte) 0);
	}
	
	public NBTTagByte(byte data){
		super(data);
	}
	
	@Override
	public byte getType(){
		return BYTE;
	}

	@Override
	public void write(DataOutput stream) throws IOException {
		stream.writeByte(this.data);
	}

	@Override
	public void read(DataInput stream) throws IOException {
		this.data = stream.readByte();
	}

	@Override
	public String toString(){
		return this.data + "b";
	}
}
