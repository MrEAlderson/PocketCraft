package de.marcely.pocketcraft.raknet.packet;

import java.util.Map;
import java.util.TreeMap;

import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

/**
 * 
 * @author Marcel S.
 * @date 02.12.2017 (dd/MM/yyyy)
 * @website https://marcely.de/
 */
public class RakNetAckPacket extends RakNetPacket {
	
	public Map<Integer, Integer> packets = new TreeMap<Integer, Integer>();
	
	public RakNetAckPacket(){
		this(RakNetPacket.TYPE_ACK);
	}
	
	public RakNetAckPacket(byte id){
		super(id);
	}

	@Override
	public void decode(ByteArrayReader reader) throws Exception { }

	@Override
	public void encode(ByteArrayWriter writer) throws Exception {
		if(this.packets.size() == 0) return;
		
		final int[] packets = new int[this.packets.size()];
		
		int i = 0;
        for(int v:this.packets.values())
            packets[i++] = v;
        
        final ByteArrayWriter writer2 = new ByteArrayWriter();
        short records = 0;

        if(packets.length >= 1){
            int pointer = 1;
            int start = packets[0];
            int last = packets[0];
        	
	        while(pointer < packets.length){
	            final int current = packets[pointer++];
	            final int diff = current - last;
	            
	            if(diff == 1)
	                last = current;
	            else if(diff > 1){
	                if(start == last){
	                    writer2.writeSignedByte((byte) 0x01);
	                    writer2.writeLTriad(start);
	                }else{
	                	 writer2.writeSignedByte((byte) 0x00);
	                	 writer2.writeLTriad(start);
	                	 writer2.writeLTriad(last);
	                	 start = last = current;
	                }
	                
	                records++;
	            }
	        }
	
	        if(start == last){
	        	writer2.writeSignedByte((byte) 0x01);
	        	writer2.writeLTriad(start);
	        }else{
	        	writer2.writeSignedByte((byte) 0x00);
	            writer2.writeLTriad(start);
	            writer2.writeLTriad(last);
	        }
	        
	        records++;
        }
        
        writer.writeUnsignedShort(records);
        writer.write(writer2.toByteArray());
        
        writer2.close();
	}
}
