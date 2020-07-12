package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTByteBuf;
import de.marcely.pocketcraft.bedrock.component.nbt.BNBTCompound;

public class BNBTValueCompound extends BNBTValue<BNBTCompound> {
	
	public BNBTValueCompound(){
		this(new BNBTCompound());
	}
	
	public BNBTValueCompound(BNBTCompound value){
		super(value);
	}

	@Override
	public byte getType(){ return TYPE_COMPOUND; }

	@Override
	public void write(BNBTByteBuf stream){
		this.data.write(stream);
	}

	@Override
	public void read(BNBTByteBuf stream){
		this.data = new BNBTCompound();
		
		this.data.read(stream);
	}
	
	@Override
	public String toString(){
		return this.data.toString();
	}
}
