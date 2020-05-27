package de.marcely.pocketcraft.bedrock.component.nbt;

import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import de.marcely.pocketcraft.utils.io.VarInt;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class NBTByteBuf {
	
	private final ByteBuf buffer;
	private final ByteOrder order;
	private final boolean isNetwork;
	
	public NBTByteBuf(ByteBuf buf, ByteOrder order, boolean isNetwork){
		this.buffer = buf;
		this.order = order;
		this.isNetwork = isNetwork;
	}
	
	public NBTByteBuf(ByteOrder order, boolean isNetwork){
		this(Unpooled.buffer(), order, isNetwork);
	}
	
	public NBTByteBuf(int initCapacity, ByteOrder order, boolean isNetwork){
		this(Unpooled.buffer(initCapacity), order, isNetwork);
	}
	
	public NBTByteBuf(byte[] src, ByteOrder order, boolean isNetwork){
		this(Unpooled.wrappedBuffer(src), order, isNetwork);
	}
	
	public void writeBytes(byte[] src){
		this.buffer.writeBytes(src);
	}
	
	public void writeBoolean(boolean value){
		this.buffer.writeBoolean(value);
	}
	
	public void writeByte(int value){
		this.buffer.writeByte(value);
	}
	
	public void writeUnsignedByte(int value){
		writeByte(value);
	}
	
	public void writeShort(int value){
		if(this.order == ByteOrder.LITTLE_ENDIAN)
			this.buffer.writeShortLE(value);
		else
			this.buffer.writeShort(value);
	}
	
	public void writeUnsignedShort(int value){
		writeShort(value);
	}
	
	public void writeInt(int value){
		if(this.isNetwork){
			VarInt.writeVarInt(this.buffer, value);
			return;
		}
		
		if(this.order == ByteOrder.LITTLE_ENDIAN)
			this.buffer.writeIntLE(value);
		else
			this.buffer.writeInt(value);
	}
	
	public void writeLong(long value){
		if(this.isNetwork){
			VarInt.writeVarLong(this.buffer, value);
			return;
		}
		
		if(this.order == ByteOrder.LITTLE_ENDIAN)
			this.buffer.writeLongLE(value);
		else
			this.buffer.writeLong(value);
	}
	
	public void writeFloat(float value){
		if(this.order == ByteOrder.LITTLE_ENDIAN)
			this.buffer.writeFloatLE(value);
		else
			this.buffer.writeFloat(value);
	}
	
	public void writeDouble(double value){
		if(this.order == ByteOrder.LITTLE_ENDIAN)
			this.buffer.writeDoubleLE(value);
		else
			this.buffer.writeDouble(value);
	}
	
	public void writeUTF(String value){
		final byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
		
		if(this.isNetwork)
			VarInt.writeUnsignedVarInt(this.buffer, bytes.length);
		else
			this.writeUnsignedShort(bytes.length);
		
		this.writeBytes(bytes);
	}
	
	public void readBytes(byte[] dst){
		this.buffer.readBytes(dst);
	}
	
	public boolean readBoolean(){
		return this.buffer.readBoolean();
	}
	
	public byte readByte(){
		return this.buffer.readByte();
	}
	
	public short readUnsignedByte(){
		return this.buffer.readUnsignedByte();
	}
	
	public short readShort(){
		return this.order == ByteOrder.LITTLE_ENDIAN ?
				this.buffer.readShortLE() :
				this.buffer.readShort();
	}
	
	public int readUnsignedShort(){
		return this.order == ByteOrder.LITTLE_ENDIAN ?
				this.buffer.readUnsignedShortLE() :
				this.buffer.readUnsignedShort();
	}
	
	public int readInt(){
		if(this.isNetwork)
			return VarInt.readVarInt(this.buffer);
		
		return this.order == ByteOrder.LITTLE_ENDIAN ?
				this.buffer.readIntLE() :
				this.buffer.readInt();
	}
	
	public long readLong(){
		if(this.isNetwork)
			return VarInt.readVarLong(this.buffer);
		
		return this.order == ByteOrder.LITTLE_ENDIAN ?
				this.buffer.readLongLE() :
				this.buffer.readLong();
	}
	
	public float readFloat(){
		return this.order == ByteOrder.LITTLE_ENDIAN ?
				this.buffer.readFloatLE() :
				this.buffer.readFloat();
	}
	
	public double readDouble(){
		return this.order == ByteOrder.LITTLE_ENDIAN ?
				this.buffer.readDoubleLE() :
				this.buffer.readDouble();
	}
	
	public String readUTF(){
		final int length = (int) (this.isNetwork ? VarInt.readUnsignedVarInt(this.buffer) : this.readUnsignedShort());
		final byte[] bytes = new byte[length];
		
		this.readBytes(bytes);
		
		return new String(bytes, StandardCharsets.UTF_8);
	}
	
	public byte[] array(){
		return this.buffer.array();
	}
	
	// same as array(), but cuts the unwritten bytes out
	public byte[] cutArray(){
		return Arrays.copyOf(this.buffer.array(), this.buffer.writerIndex());
	}
	
	public boolean release(){
		return this.buffer.release();
	}
}
