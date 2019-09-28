package de.marcely.pocketcraft.pocket.nbt.value;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

public class NBTValueList extends NBTNumericValue<List<NBTValue<?>>>  /* Numeric because we need to send a number*/ {

	public NBTValueList(List<NBTValue<?>> value){
		super(value);
	}

	@Override
	public byte getID(){ return TYPE_LIST; }

	@Override
	public void write(ByteArrayWriter stream, ByteOrder order) throws Exception {
		if(this.value.size() >= 1) stream.writeSignedByte(this.value.get(0).getID());
		else stream.writeSignedByte((byte) 0x01);
		
		stream.writeSignedVarInt(this.value.size(), order);
		
		for(NBTValue<?> val:this.value)
			val.write(stream);
	}

	@Override
	public void read(ByteArrayReader stream, ByteOrder order) throws Exception {
		final byte type = stream.readSignedByte();
		final int length = stream.readSignedVarInt(order);
		
		this.value = new ArrayList<>(length);
		
		for(int i=0; i<length; i++){
			final NBTValue<?> val = NBTValue.newInstance(type);
			
			if(!(val instanceof NBTNumericValue))
				val.read(stream);
			else
				((NBTNumericValue<?>) val).read(stream, order);
			
			this.value.add(val);
		}
	}
}
