package de.marcely.pocketcraft.bedrock.component.nbt;

import java.io.IOException;
import java.nio.ByteOrder;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTTag;
import de.marcely.pocketcraft.bedrock.component.nbt.value.BNBTValue;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import lombok.Getter;

public class BNBTTag {
	
	@Getter private final String name;
	@Getter private final BNBTValue<?> value;
	
	public BNBTTag(String name, BNBTValue<?> value){
		this.name = name;
		this.value = value;
	}
	
	public void write(EByteArrayWriter stream, ByteOrder order, boolean isNetwork){
		final BNBTByteBuf buf = new BNBTByteBuf(order, isNetwork);
		
		try{
			write(buf);
			
			try{
				stream.write(buf.cutArray());
			}catch(IOException e){
				e.printStackTrace();
			}
		}finally{
			buf.release();
		}
	}
	
	public void write(BNBTByteBuf stream){
		stream.writeByte(this.value.getType());
		
		if(this.value.getType() == BNBTValue.TYPE_END)
			return;
		
		stream.writeUTF(this.name);
		this.value.write(stream);
	}
	
	public byte[] write(ByteOrder order, boolean isNetwork){
		final BNBTByteBuf buf = new BNBTByteBuf(order, isNetwork);
		
		try{
			write(buf);
			
			return buf.array();
		}finally{
			buf.release();
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T>T getValueData(){
		return (T) this.value.getData();
	}
	
	public static BNBTTag read(EByteArrayReader stream, ByteOrder order, boolean isNetwork){
		final BNBTByteBuf buf = new BNBTByteBuf(stream.getRemainingBuffer(), order, isNetwork);
		
		try{
			return read(buf);
		}finally{
			buf.release();
		}
	}
	
	public static BNBTTag read(BNBTByteBuf stream){
		final byte type = stream.readByte();
		
		if(type == BNBTValue.TYPE_END)
			return new BNBTTag(null, BNBTValue.newInstance(BNBTValue.TYPE_END));
		
		final String name = stream.readUTF();
		final BNBTValue<?> value = BNBTValue.newInstance(type);
		
		value.read(stream);
		
		return new BNBTTag(name, value);
	}

	@Override
	public String toString(){
		return this.name + ":" + this.value.toString();
	}
}
