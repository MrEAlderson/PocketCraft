package de.marcely.pocketcraft.bedrock.component.nbt;

import java.io.IOException;
import java.nio.ByteOrder;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTTag;
import de.marcely.pocketcraft.bedrock.component.nbt.value.NBTValue;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import lombok.Getter;

public class NBTTag {
	
	@Getter private final String name;
	@Getter private final NBTValue<?> value;
	
	public NBTTag(String name, NBTValue<?> value){
		this.name = name;
		this.value = value;
	}
	
	public void write(EByteArrayWriter stream, ByteOrder order, boolean isNetwork){
		final NBTByteBuf buf = new NBTByteBuf(order, isNetwork);
		
		try{
			write(buf);
			
			try{
				stream.write(buf.array());
			}catch(IOException e){
				e.printStackTrace();
			}
		}finally{
			buf.release();
		}
	}
	
	public void write(NBTByteBuf stream){
		stream.writeByte(this.value.getType());
		
		if(this.value.getType() == NBTValue.TYPE_END) return;
		
		stream.writeUTF(this.name);
		this.value.write(stream);
	}
	
	@SuppressWarnings("unchecked")
	public <T>T getValueData(){
		return (T) this.value.getData();
	}
	
	public static NBTTag read(EByteArrayReader stream, ByteOrder order, boolean isNetwork){
		final NBTByteBuf buf = new NBTByteBuf(stream.getBuffer(), order, isNetwork);
		
		try{
			return read(buf);
		}finally{
			buf.release();
		}
	}
	
	public static NBTTag read(NBTByteBuf stream){
		final byte type = stream.readByte();
		if(type == NBTValue.TYPE_END) return new NBTTag(null, NBTValue.newInstance(NBTValue.TYPE_END));
		final String name = stream.readUTF();
		final NBTValue<?> value = NBTValue.newInstance(type);
		
		value.read(stream);
		
		return new NBTTag(name, value);
	}
}
