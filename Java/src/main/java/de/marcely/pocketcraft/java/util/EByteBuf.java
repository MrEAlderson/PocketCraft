package de.marcely.pocketcraft.java.util;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import de.marcely.pocketcraft.java.component.chat.ChatBaseComponent;
import de.marcely.pocketcraft.java.component.nbt.NBTBase;
import de.marcely.pocketcraft.java.component.nbt.NBTTagCompound;
import de.marcely.pocketcraft.utils.math.Vector3;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

public class EByteBuf {
	
	private final ByteBuf buffer;
	
	public EByteBuf(ByteBuf buf){
		this.buffer = buf;
	}
	
	public EByteBuf(){
		this(Unpooled.buffer());
	}
	
	public EByteBuf(int initCapacity){
		this(Unpooled.buffer(initCapacity));
	}
	
	public EByteBuf(byte[] src){
		this(Unpooled.wrappedBuffer(src));
	}
	
	public void write(byte[] src){
		this.buffer.writeBytes(src);
	}
	
	public void write(byte[] src, int srcIndex, int length){
		this.buffer.writeBytes(src, srcIndex, length);
	}
	
	public void writeByte(int value){
		this.buffer.writeByte(value);
	}
	
	public void writeUnsignedByte(int value){
		writeByte(value);
	}
	
	public void writeBoolean(boolean value){
		this.buffer.writeBoolean(value);
	}
	
	public void writeShort(int value){
		this.buffer.writeShort(value);
	}
	
	public void writeUnsignedShort(int value){
		writeShort(value);
	}
	
	public void writeInt(int value){
		this.buffer.writeInt(value);
	}
	
	public void writeLong(long value){
		this.buffer.writeLong(value);
	}
	
	public void writeFloat(float value){
		this.buffer.writeFloat(value);
	}
	
	public void writeDouble(double value){
		this.buffer.writeDouble(value);
	}
	
	public void writeVarInt(int value){
		writeVarLong(value);
	}
	
	public void writeVarLong(long value){
		do {
			byte temp = (byte)(value & 0b01111111);
			
			value >>>= 7;
			
			if(value != 0)
				temp |= 0b10000000;
			
			writeByte(temp);
		}while(value != 0);
	}
	
	public void writeByteArray(byte[] value){
		writeVarInt(value.length);
		write(value);
	}
	
	public void writeString(String value){
		writeString(value, 32767);
	}
	
	public void writeString(String value, int maxLength){
		if(value.length() > maxLength)
			throw new IllegalStateException("String is larger than expected");
		
		writeByteArray(value.getBytes(StandardCharsets.UTF_8));
	}
	
	public void writeIdentifier(String value){
		writeString(value);
	}
	
	public void writeUUID(UUID id){
		writeLong(id.getMostSignificantBits());
		writeLong(id.getLeastSignificantBits());
	}
	
	/* 26 bits: x
	 * 16 bits: y
	 * 26 bits: z */
	public void writeBlockPosition(int x, int y, int z){
		writeLong(((x & 0x3FFFFFF) << 38) | (y & 0xFFF << 26) | (z & 0x3FFFFFF));
	}
	
	public void writeBlockPosition(Vector3 vec){
		writeBlockPosition(vec.getFloorX(), vec.getFloorY(), vec.getFloorZ());
	}
	
	public void writeChatJson(ChatBaseComponent chat){
		writeString(chat.writeAsJsonString());
	}
	
	public void writeChatPlain(ChatBaseComponent chat){
		writeString(chat.writeAsPlainString());
	}
	
	public void writeNBT(NBTTagCompound nbt){
		if(nbt == null){
			writeByte(NBTTagCompound.END);
			return;
		}
		
		try{
			final DataOutput stream = new DataOutputStream(new ByteBufOutputStream(this.buffer));
			
			stream.writeByte(nbt.getType());
			stream.writeUTF("");
			nbt.write(stream);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void read(byte[] dst){
		this.buffer.readBytes(dst);
	}
	
	public byte[] read(int length){
		final byte[] dst = new byte[length];
		
		read(dst);
		
		return dst;
	}
	
	public byte readByte(){
		return this.buffer.readByte();
	}
	
	public boolean readBoolean(){
		return this.buffer.readBoolean();
	}
	
	public short readUnsignedByte(){
		return this.buffer.readUnsignedByte();
	}
	
	public short readShort(){
		return this.buffer.readShort();
	}
	
	public int readUnsignedShort(){
		return this.buffer.readUnsignedShort();
	}
	
	public int readInt(){
		return this.buffer.readInt();
	}
	
	public long readLong(){
		return this.buffer.readLong();
	}
	
	public float readFloat(){
		return this.buffer.readFloat();
	}
	
	public double readDouble(){
		return this.buffer.readDouble();
	}
	
	public int readVarInt(){
		int numRead = 0;
		int result = 0;
		byte read;
		
		do {
			final int value = ((read = readByte()) & 0b01111111);
			
			result |= (value << (7 * numRead));
			
			numRead++;
			
			if(numRead > 5)
				throw new RuntimeException("VarNumber is too big");
		}while((read & 0b10000000) != 0);
		
		return result;
	}
	
	public long readVarLong(){
		return readVarNumber(10);
	}
	
	private long readVarNumber(int maxSize){
		int numRead = 0;
		long result = 0;
		byte read;
		
		do {
			final int value = ((read = readByte()) & 0b01111111);
			
			result |= (value << (7 * numRead));
			
			numRead++;
			
			if(numRead > maxSize)
				throw new RuntimeException("VarNumber is too big");
		}while((read & 0b10000000) != 0);
		
		return result;
	}
	
	public byte[] readByteArray(){
		return read(readVarInt());
	}
	
	public byte[] readByteArray(int maxLength){
		final int size = readVarInt();
		
		if(size > maxLength)
			throw new IllegalStateException("ByteArray is larger than expected");
		
		return read(size);
	}
	
	public String readString(){
		return readString(32767);
	}
	
	public String readString(int maxLength){
		final String str = new String(readByteArray(maxLength*4), StandardCharsets.UTF_8);
		
		if(str.length() > maxLength)
			throw new IllegalStateException("String is larger than expected");
		
		return str;
	}
	
	public String readIdentifier(){
		return readString();
	}
	
	public UUID readUUID(){
		return new UUID(readLong(), readLong());
	}
	
	/* 26 bits: x
	 * 16 bits: y
	 * 26 bits: z */
	public Vector3 readBlockPosition(){
		final long val = readLong();
		
		return new Vector3(val >> 38, val << 26 >> 52, (val << 38 >> 38));
	}
	
	public ChatBaseComponent readChatJson(){
		return ChatBaseComponent.parseJson(readString());
	}
	
	public ChatBaseComponent readChatPlain(){
		return ChatBaseComponent.parsePlain(readString());
	}
	
	public NBTTagCompound readNBT(){
		final NBTBase<?> tag = NBTBase.newInstance(this.readByte());
		
		if(tag == null || tag.getType() != NBTBase.COMPOUND)
			return null;
		
		try{
			final DataInput stream = new DataInputStream(new ByteBufInputStream(this.buffer));
			
			stream.readUTF();
			tag.read(stream);
			
			return (NBTTagCompound) tag;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public EByteBuf readAsBuf(int length){
		return new EByteBuf(this.buffer.readBytes(length));
	}
	
	public int readableBytes(){
		return this.buffer.readableBytes();
	}
	
	public int readerIndex(){
		return this.buffer.readerIndex();
	}
	
	public EByteBuf markReaderIndex(){
		this.buffer.markReaderIndex();
		
		return this;
	}
	
	public EByteBuf resetReaderIndex(){
		this.buffer.resetReaderIndex();
		
		return this;
	}
	
	public boolean isReadable(){
		return this.buffer.isReadable();
	}
	
	public boolean isReadable(int size){
		return this.buffer.isReadable(size);
	}
	
	public boolean isReadOnly(){
		return this.buffer.isReadOnly();
	}
	
	public int writerIndex(){
		return this.buffer.writerIndex();
	}
	
	public EByteBuf markWriterIndex(){
		this.buffer.markWriterIndex();
		
		return this;
	}
	
	public EByteBuf resetWriterIndex(){
		this.buffer.resetWriterIndex();
		
		return this;
	}
	
	public boolean isWriteable(){
		return this.buffer.isWritable();
	}
	
	public boolean isWriteable(int size){
		return this.buffer.isWritable(size);
	}
	
	public EByteBuf copy(){
		return new EByteBuf(this.buffer.copy());
	}
	
	public byte[] array(){
		return this.buffer.array();
	}
	
	public boolean release(){
		return this.buffer.release();
	}
	
	public void skipBytes(int length){
		this.buffer.skipBytes(length);
	}
}
