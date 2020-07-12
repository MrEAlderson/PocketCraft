package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTByteBuf;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTValue;

public class BNBTValueEnd extends BNBTValue<Void> {

	public BNBTValueEnd(){ super(null); }

	@Override
	public byte getType(){ return TYPE_END; }

	@Override
	public void write(BNBTByteBuf stream){ }

	@Override
	public void read(BNBTByteBuf stream){ }
	
	@Override
	public String toString(){
		return "END";
	}
}
