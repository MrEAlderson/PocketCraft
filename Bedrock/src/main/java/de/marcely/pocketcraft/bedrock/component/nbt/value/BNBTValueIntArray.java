package de.marcely.pocketcraft.bedrock.component.nbt.value;

import java.util.Arrays;
import java.util.stream.Collectors;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTByteBuf;

public class BNBTValueIntArray extends BNBTValue<int[]> {

	public BNBTValueIntArray(int[] value){
		super(value);
	}

	@Override
	public byte getType(){ return TYPE_BYTE_ARRAY; }

	@Override
	public void write(BNBTByteBuf stream){
		stream.writeInt(this.data.length);
		
		for(int e:this.data)
			stream.writeInt(e);
	}

	@Override
	public void read(BNBTByteBuf stream){
		this.data = new int[stream.readInt()];
		
		for(int i=0; i<this.data.length; i++)
			this.data[i] = stream.readInt();
	}

	@Override
	public String toString(){
		return "[" + Arrays.stream(this.data).mapToObj(String::valueOf).collect(Collectors.joining(",")) + "]";
	}
}
