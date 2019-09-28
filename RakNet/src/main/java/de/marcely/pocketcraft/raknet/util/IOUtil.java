package de.marcely.pocketcraft.raknet.util;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

public class IOUtil {
	
	public static InetSocketAddress readAddress(ByteArrayReader stream) throws IOException {
        final int version = stream.readUnsignedByte();
        
        switch(version){
        case 4:
	        {
	            final String addr = ((~stream.readSignedByte()) & 0xFF) + "." + ((~stream.readSignedByte()) & 0xFF) + "." + ((~stream.readSignedByte()) & 0xFF) + "." + ((~stream.readSignedByte()) & 0xFF);
	            final int port = stream.readUnsignedShort();
	            
	            return new InetSocketAddress(addr, port);
	        }
        
        case 6:
	        {
	        	stream.readLShort(); //Family, AF_INET6
	        	final int port = stream.readUnsignedShort();
	        	stream.readUnsignedInt();
	        	final String addr = new String(stream.read(16));
	        	stream.readUnsignedInt();
	        	
	        	return new InetSocketAddress(addr, port);
	        }
	        
	    default:
	    	throw new IOException("Unsupported address version: " + version);
        }
    }
	
	 public static void writeAddress(InetSocketAddress sAddress, ByteArrayWriter stream) throws IOException {
	        final InetAddress address = sAddress.getAddress();
	    	byte version = 0;
	        
	    	// detect version
	    	{
	    		if(address instanceof Inet4Address)
	    			version = 4;
	    		else if(address instanceof Inet6Address)
	    			version = 6;
	    		else
	    			throw new IOException("Unsupported address: " + address.getClass().getName());
	    	}
	    	
	    	stream.writeUnsignedByte(version); 
	        
	    	{
		        switch(version){
		        case 0x04:
		            for(String b:address.getHostAddress().split("\\."))
		            	stream.writeUnsignedByte((byte) ((~Integer.valueOf(b)) & 0xFF));
		            
		            stream.writeUnsignedShort(sAddress.getPort());
		            
		            break;
		            
		        case 0x06:
		        	stream.writeLShort(23 /* AF_INET6 */);
		        	stream.writeUnsignedShort(sAddress.getPort());
		        	stream.writeUnsignedInt(0);
		        	stream.writeShortString(address.getHostAddress());
		        	stream.writeUnsignedInt(0);
		        	
		        	break;
		        }
	    	}
	    }
}
