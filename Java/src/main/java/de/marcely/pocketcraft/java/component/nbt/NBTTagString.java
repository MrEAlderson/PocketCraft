package de.marcely.pocketcraft.java.component.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagString extends NBTBase<String> {
	
	public NBTTagString(){
		this("");
	}
	
	public NBTTagString(String data){
		super(data);
	}
	
	@Override
	public byte getType(){
		return STRING;
	}

	@Override
	public void write(DataOutput stream) throws IOException {
		stream.writeUTF(this.data);
	}

	@Override
	public void read(DataInput stream) throws IOException {
		this.data = stream.readUTF();
	}

	@Override
	public String toString(){
		return "\"" + this.data.replace("\"", "\\\"") + "\"";
	}
}
