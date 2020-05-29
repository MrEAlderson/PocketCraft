package de.marcely.pocketcraft.utils.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * 
 * @author Marcel S.
 * @date 20.12.2017 (dd/MM/yyyy)
 * @website https://marcely.de/
 */
public class ByteArrayReader extends ByteArrayInputStream {

	public ByteArrayReader(byte[] buf){
		super(buf);
	}
	
	public ByteArrayReader(byte[] buf, int offset, int length){
		super(buf, offset, length);
	}
	
	public byte[] read(int length) throws IOException {
		final byte[] array = new byte[length];
		
		read(array);
		
		return array;
	}
	
	public byte[] readByteArray() throws IOException {
		return read((int) readUnsignedVarInt());
	}
	
	public byte[] readShortByteArray() throws IOException {
		return read((int) readUnsignedShort());
	}
	
	public String readString() throws IOException {
		return new String(readByteArray(), StandardCharsets.UTF_8);
	}
	
	public String readShortString() throws IOException {
		return new String(readShortByteArray(), StandardCharsets.UTF_8);
	}
	
	public byte readSignedByte() throws IOException {
		return (byte) read();
	}
	
	public byte readSignedByte(ByteOrder order) throws IOException {
		return order == ByteOrder.BIG_ENDIAN ? (byte) read() : ByteBuffer.allocate(1).put((byte) read()).array()[0];
	}
	
	public int readUnsignedByte() throws IOException {
		return Byte.toUnsignedInt(readSignedByte());
	}
	
	public short readSignedShort() throws IOException {
		return ByteBuffer.wrap(read(2)).getShort();
	}
	
	public short readSignedShort(ByteOrder order) throws IOException {
		return ByteBuffer.wrap(read(2)).order(order).getShort();
	}
	
	public int readUnsignedShort() throws IOException {
		return Short.toUnsignedInt(readSignedShort());
	}
	
	public int readUnsignedShort(ByteOrder order) throws IOException {
		return Short.toUnsignedInt(readSignedShort(order));
	}
	
	public int readSignedInt() throws IOException {
		return ByteBuffer.wrap(read(4)).getInt();
	}
	
	public int readSignedInt(ByteOrder order) throws IOException {
		return ByteBuffer.wrap(read(4)).order(order).getInt();
	}
	
	public long readSignedLong() throws IOException {
		return ByteBuffer.wrap(read(8)).getLong();
	}
	
	public long readSignedLong(ByteOrder order) throws IOException {
		return ByteBuffer.wrap(read(8)).order(order).getLong();
	}
	
	public long readUnsignedInt() throws IOException {
		return Integer.toUnsignedLong(ByteBuffer.wrap(read(4)).getInt());
	}
	
	public long readUnsignedLong() throws IOException {
		final byte[] data = read(8);
		
        return (((long) data[0] << 56) +
                ((long) (data[1] & 0xFF) << 48) +
                ((long) (data[2] & 0xFF) << 40) +
                ((long) (data[3] & 0xFF) << 32) +
                ((long) (data[4] & 0xFF) << 24) +
                ((data[5] & 0xFF) << 16) +
                ((data[6] & 0xFF) << 8) +
                ((data[7] & 0xFF)));
	}
	
	public boolean readBoolean() throws IOException {
		return readSignedByte() == 0x1;
	}
    
    public int readTriad() throws IOException {
    	return ByteBuffer.wrap(new byte[]{
                (byte) 0x00,
                readSignedByte(),
                readSignedByte(),
                readSignedByte() }).getInt();
    }
    
    public int readLTriad() throws IOException {
    	return readLIntFromBytes(new byte[]{
                readSignedByte(),
                readSignedByte(),
                readSignedByte(),
                (byte) 0x00 });
    }
    
    private static int readLIntFromBytes(byte[] bytes){
        return ((bytes[3] & 0xFF) << 24) +
               ((bytes[2] & 0xFF) << 16) +
               ((bytes[1] & 0xFF) << 8) +
               (bytes[0] & 0xFF);
    }
    
    private static int readLLongFromBytes(byte[] bytes){
        return ((bytes[7] & 0xFF) << 64) +
        	   ((bytes[6] & 0xFF) << 48) +
        	   ((bytes[5] & 0xFF) << 40) +
        	   ((bytes[4] & 0xFF) << 32) +
        	   ((bytes[3] & 0xFF) << 24) +
               ((bytes[2] & 0xFF) << 16) +
               ((bytes[1] & 0xFF) << 8) +
               (bytes[0] & 0xFF);
    }
    
    public int readLInt() throws IOException {
    	return readLIntFromBytes(read(4));
    }
    
    public int readLLong() throws IOException {
    	return readLLongFromBytes(read(8));
    }
    
    public int getMark(){
    	return this.mark;
    }
    
    public long readUnsignedVarInt() throws IOException {
        return VarInt.readUnsignedVarInt(this);
    }
    
    public long readUnsignedVarInt(ByteOrder order) throws IOException {
    	long l = VarInt.readUnsignedVarInt(this);
    	
    	if(order == ByteOrder.LITTLE_ENDIAN)
    		l = Long.reverseBytes(l) >> 16;
    	
        return l;
    }
    
    public int readSignedVarInt() throws IOException {
        return VarInt.readVarInt(this);
    }
    
    public int readSignedVarInt(ByteOrder order) throws IOException {
    	int i = VarInt.readVarInt(this);
    	
    	if(order == ByteOrder.LITTLE_ENDIAN)
    		i = Integer.reverseBytes(i);
    	
        return i;
    }
    
    public long readUnsignedVarLong() throws IOException {
        return VarInt.readUnsignedVarLong(this);
    }
    
    public long readSignedVarLong() throws IOException {
        return VarInt.readVarLong(this);
    }
    
    public long readSignedVarLong(ByteOrder order) throws IOException {
    	long l = VarInt.readVarLong(this);
    	
    	if(order == ByteOrder.LITTLE_ENDIAN)
    		l = Long.reverseBytes(l);
    	
        return l;
    }
    
    public int readLShort() throws IOException {
        final byte[] data = read(2);
    	
    	return (data[1] & 0xFF << 8) + (data[0] & 0xFF);
    }
    
    public float readLFloat() throws IOException {
    	return Float.intBitsToFloat(readLInt());
    }
    
    public byte[] getBuffer(){
    	return this.buf;
    }
    
    public byte[] getRemainingBuffer(){
    	return Arrays.copyOfRange(this.buf, this.pos, this.buf.length);
    }
    
    public void setPos(int pos){
    	this.pos = pos;
    }
    
    public int getPos(){
    	return this.pos;
    }
    
    public float readFloat(ByteOrder order) throws IOException {
    	return Float.intBitsToFloat(ByteBuffer.wrap(read(4)).order(order).getInt());
    }
    
    public double readDouble(ByteOrder order) throws IOException {
    	return Double.longBitsToDouble(ByteBuffer.wrap(read(8)).order(order).getLong());
    }
}