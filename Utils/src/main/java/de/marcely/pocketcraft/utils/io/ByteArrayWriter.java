package de.marcely.pocketcraft.utils.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class ByteArrayWriter extends ByteArrayOutputStream {
	
	public void writeByteArray(byte[] data) throws IOException {
		writeUnsignedVarInt(data.length);
		write(data);
	}
	
	public void writeShortByteArray(byte[] data) throws IOException {
		writeUnsignedShort(data.length);
		write(data);
	}
	
	public void writeString(String str) throws IOException {
		writeByteArray(str.getBytes(StandardCharsets.UTF_8));
	}
	
	public void writeShortString(String str) throws IOException {
		writeShortByteArray(str.getBytes(StandardCharsets.UTF_8));
	}
	
	public void writeSignedByte(byte b)  throws IOException {
		write(b);
	}
	
	public void writeSignedByte(byte b, ByteOrder order)  throws IOException {
		write(order == ByteOrder.BIG_ENDIAN ? b : ByteBuffer.allocate(1).put(b).order(order).array()[0]);
	}
	
	public void writeUnsignedByte(int b)  throws IOException {
		writeSignedByte((byte) (b & 0xFF));
	}
	
	public void writeSignedShort(short s) throws IOException {
		write(ByteBuffer.allocate(2).putShort(s).array());
	}
	
	public void writeSignedShort(short s, ByteOrder order) throws IOException {
		write(ByteBuffer.allocate(2).putShort(s).order(order).array());
	}
	
    public void writeUnsignedShort(int s) throws IOException {
        write(new byte[]{
                (byte) ((s >>> 8) & 0xFF),
                (byte) (s & 0xFF)
        });
    }
    
    public void writeUnsignedShort(int s, ByteOrder order) throws IOException {
        if(order == ByteOrder.LITTLE_ENDIAN)
        	s = Integer.reverseBytes(s) >> 16;
    	
    	write(new byte[]{
                (byte) ((s >>> 8) & 0xFF),
                (byte) (s & 0xFF)
        });
    }
	
	public void writeSignedInt(int i) throws IOException {
		write(ByteBuffer.allocate(4).putInt(i).array());
	}
	
	public void writeSignedInt(int i, ByteOrder order) throws IOException {
		write(ByteBuffer.allocate(4).putInt(i).order(order).array());
	}
	
	public void writeUnsignedInt(long l) throws IOException {
        write(new byte[]{
                (byte) ((l >>> 24) & 0xFF),
                (byte) ((l >>> 16) & 0xFF),
                (byte) ((l >>> 8) & 0xFF),
                (byte) (l & 0xFF)
        });
	}
	
	public void writeSignedLong(long l) throws IOException {
		write(ByteBuffer.allocate(8).putLong(l).array());
	}
	
	public void writeSignedLong(long l, ByteOrder order) throws IOException {
		write(ByteBuffer.allocate(8).putLong(l).order(order).array());
	}
	
    public void writeUnsignedLong(long l) throws IOException {
        write(new byte[]{
                (byte) (l >>> 56),
                (byte) (l >>> 48),
                (byte) (l >>> 40),
                (byte) (l >>> 32),
                (byte) (l >>> 24),
                (byte) (l >>> 16),
                (byte) (l >>> 8),
                (byte) (l)
        });
    }
    
    public void writeLShort(int s) throws IOException {
        s &= 0xffff;
        
        write(new byte[]{
                (byte) (s & 0xFF),
                (byte) ((s >>> 8) & 0xFF)
        });
    }
    
    public void writeLLong(long l) throws IOException {
        write(new byte[]{
                (byte) (l),
                (byte) (l >>> 8),
                (byte) (l >>> 16),
                (byte) (l >>> 24),
                (byte) (l >>> 32),
                (byte) (l >>> 40),
                (byte) (l >>> 48),
                (byte) (l >>> 56),
        });
    }
	
	public void writeBoolean(boolean b) throws IOException {
		writeSignedByte((byte) (b == true ? 0x1 : 0x0));
	}
    
    public void writeTriad(int t) throws IOException {
    	write(new byte[]{
                (byte) ((t >>> 16) & 0xFF),
                (byte) ((t >>> 8) & 0xFF),
                (byte) (t & 0xFF)
        });
    }
    
    public void writeLTriad(int t) throws IOException {
    	write(new byte[]{
    			(byte) (t & 0xFF),
    			(byte) ((t >>> 8) & 0xFF),
                (byte) ((t >>> 16) & 0xFF)
        });
    }
    
    public void writeUnsignedVarInt(long v) throws IOException {
        VarInt.writeUnsignedVarInt(this, v);
    }
    
    public void writeUnsignedVarInt(long v, ByteOrder order) throws IOException {
        if(order == ByteOrder.LITTLE_ENDIAN)
        	v = Long.reverseBytes(v) >> 32;
    	
        VarInt.writeUnsignedVarInt(this, v);
    }
    
    public void writeSignedVarInt(int v) throws IOException {
        VarInt.writeVarInt(this, v);
    }
    
    public void writeSignedVarInt(int v, ByteOrder order) throws IOException {
        if(order == ByteOrder.LITTLE_ENDIAN)
        	v = Integer.reverseBytes(v);
    	
    	VarInt.writeVarInt(this, v);
    }
    
    public void writeBlockPosition(int x, int y, int z) throws IOException {
        writeSignedVarInt(x);
        writeUnsignedVarInt(y);
        writeSignedVarInt(z);
    }
    
    public void writeLFloat(float f) throws IOException {
        writeLInt(Float.floatToIntBits(f));
    }
    
    public void writeLInt(int i) throws IOException {
        write(new byte[]{
                (byte) (i & 0xFF),
                (byte) ((i >>> 8) & 0xFF),
                (byte) ((i >>> 16) & 0xFF),
                (byte) ((i >>> 24) & 0xFF)
        });
    }
    
    public void writeVector(float x, float y, float z) throws IOException {
        writeLFloat(x);
        writeLFloat(y);
        writeLFloat(z);
    }
    
    public void writeUnsignedVarLong(long v) throws IOException {
        VarInt.writeUnsignedVarLong(this, v);
    }
    
    public void writeSignedVarLong(long v) throws IOException {
        VarInt.writeVarLong(this, v);
    }
    
    public void writeSignedVarLong(long v, ByteOrder order) throws IOException {
        if(order == ByteOrder.LITTLE_ENDIAN)
        	v = Long.reverseBytes(v);
    	
    	VarInt.writeVarLong(this, v);
    }
    
    public void writeFloat(float f, ByteOrder order) throws Exception {
    	write(ByteBuffer.allocate(4).putInt(Float.floatToIntBits(f)).order(order).array());
    }
    
    public void writeDouble(double d, ByteOrder order) throws Exception {
    	write(ByteBuffer.allocate(8).putLong(Double.doubleToLongBits(d)).order(order).array());
    }
}
