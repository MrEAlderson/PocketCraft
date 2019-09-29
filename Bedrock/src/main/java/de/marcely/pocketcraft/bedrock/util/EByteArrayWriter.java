package de.marcely.pocketcraft.bedrock.util;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map.Entry;
import java.util.UUID;

import de.marcely.pocketcraft.bedrock.world.entity.EntityAttribute;
import de.marcely.pocketcraft.bedrock.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.world.entity.EntityMetadata;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

public class EByteArrayWriter extends ByteArrayWriter {
	
	public void writeUUID(UUID id) throws IOException {
    	writeLLong(id.getMostSignificantBits());
    	writeLLong(id.getLeastSignificantBits());
    }
    
	 public void writeAddress(InetSocketAddress sAddress) throws IOException {
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
    	
    	writeUnsignedByte(version); 
        
    	{
	        switch(version){
	        case 0x04:
	            for(String b:address.getHostAddress().split("\\."))
	                writeUnsignedByte((byte) ((~Integer.valueOf(b)) & 0xFF));
	            
	            writeUnsignedShort(sAddress.getPort());
	            
	            break;
	            
	        case 0x06:
	        	writeLShort(23 /* AF_INET6 */);
	        	writeUnsignedShort(sAddress.getPort());
	        	writeUnsignedInt(0);
	        	writeShortString(address.getHostAddress());
	        	writeUnsignedInt(0);
	        	
	        	break;
	        }
    	}
    }
	
    public void writeFloat(float f, ByteOrder order) throws Exception {
    	write(ByteBuffer.allocate(4).putInt(Float.floatToIntBits(f)).order(order).array());
    }
    
    public void writeDouble(double d, ByteOrder order) throws Exception {
    	write(ByteBuffer.allocate(8).putLong(Double.doubleToLongBits(d)).order(order).array());
    }
    
    public void writeMetadata(EntityMetadata m) throws IOException {
    	writeUnsignedVarInt(m.getValues().size());
    	
    	for(Entry<EntityDataType, Object> e:m.getValues().entrySet()){
    		writeUnsignedVarInt(e.getKey().id);
    		writeUnsignedVarInt(e.getKey().valueType.id);
    		e.getKey().valueType.write(e.getValue(), this);
    	}
    }
    
    public void writeAttributes(EntityAttribute[] list) throws IOException {
    	writeUnsignedVarInt(list.length);
    	
    	for(EntityAttribute attr:list){
    		writeString(attr.type.name);
    		writeLFloat(attr.type.minValue);
    		writeLFloat(attr.value);
    		writeLFloat(attr.type.maxValue);
    	}
    }
}
