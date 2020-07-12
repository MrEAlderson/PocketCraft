package de.marcely.pocketcraft.bedrock.component.nbt.value;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTByteBuf;

public class BNBTValueString extends BNBTValue<String> {

	public BNBTValueString(String value){
		super(value);
	}

	@Override
	public byte getType(){ return TYPE_STRING; }

	@Override
	public void write(BNBTByteBuf stream){
		stream.writeUTF(this.data);
	}

	@Override
	public void read(BNBTByteBuf stream){
		this.data = stream.readUTF();
	}

	@Override
	public String toString(){
		return "\"" + this.data.replace("\"", "\\\"") + "\"";
	}
}
