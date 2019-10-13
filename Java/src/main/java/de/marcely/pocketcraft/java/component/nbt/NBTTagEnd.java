package de.marcely.pocketcraft.java.component.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagEnd extends NBTBase<Void> {
	
	public NBTTagEnd(){
		super();
	}
	
	@Override
	public byte getType(){
		return END;
	}

	@Override
	public void write(DataOutput stream) throws IOException { }

	@Override
	public void read(DataInput stream) throws IOException { }

	@Override
	public String toString(){
		return "END";
	}
}
