package de.marcely.pocketcraft.bedrock.util;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.UUID;

import de.marcely.pocketcraft.utils.io.ByteArrayReader;

public class EByteArrayReader extends ByteArrayReader {

	public EByteArrayReader(byte[] array){
		super(array);
	}
	
	public UUID readUUID() throws IOException {
		return null;
	}
	
    public InetSocketAddress readAddress() throws IOException {
        final int version = readUnsignedByte();
        
        switch(version){
        case 4:
	        {
	            final String addr = ((~readSignedByte()) & 0xFF) + "." + ((~readSignedByte()) & 0xFF) + "." + ((~readSignedByte()) & 0xFF) + "." + ((~readSignedByte()) & 0xFF);
	            final int port = readUnsignedShort();
	            
	            return new InetSocketAddress(addr, port);
	        }
        
        case 6:
	        {
	        	readLShort(); //Family, AF_INET6
	        	final int port = readUnsignedShort();
	        	readUnsignedInt();
	        	final String addr = new String(read(16));
	        	readUnsignedInt();
	        	
	        	return new InetSocketAddress(addr, port);
	        }
	        
	    default:
	    	throw new IOException("Unsupported address version: " + version);
        }
    }
}
