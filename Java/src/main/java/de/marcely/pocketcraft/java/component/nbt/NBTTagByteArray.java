package de.marcely.pocketcraft.java.component.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagByteArray extends NBTBase<byte[]> {
	
	public NBTTagByteArray(){
		this(new byte[0]);
	}
	
	public NBTTagByteArray(byte[] data){
		super(data);
	}
	
	@Override
	public byte getType(){
		return BYTE_ARRAY;
	}

	@Override
	public void write(DataOutput stream) throws IOException {
		stream.writeInt(this.data.length);
		stream.write(this.data);
	}

	@Override
	public void read(DataInput stream) throws IOException {
		{
			final int length = stream.readInt();
	
			if(length >= 16777216)
				throw new IOException("Byte array is too large! (" + this.data.length + ")");
			
			this.data = new byte[length];
		}
		
		stream.readFully(this.data);
	}

	@Override
	public String toString(){
		return "[" + this.data.length + " bytes]";
	}
}
