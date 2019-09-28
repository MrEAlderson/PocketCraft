package de.marcely.pocketcraft.raknet.packet.encapsulated;

import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

/**
 * 
 * @author Marcel S.
 * @date 02.12.2017 (dd/MM/yyyy)
 * @website https://marcely.de/
 */
public class RakNetEncapsulatedPacket {
	
    public int reliability;
    public boolean hasSplit = false;
    public int length = 0;
    public Integer messageIndex = null;
    public Integer orderIndex = null;
    public Integer orderChannel = null;
    public Integer splitCount = null;
    public Integer splitID = null;
    public Integer splitIndex = null;
    public byte[] buffer;
    public boolean needACK = false;
    public Integer identifierACK = null;
    public int offset;

    public static RakNetEncapsulatedPacket fromBinary(byte[] binary) throws Exception {
        return fromBinary(binary, false);
    }

    public static RakNetEncapsulatedPacket fromBinary(byte[] binary, boolean internal) throws Exception {
    	final RakNetEncapsulatedPacket packet = new RakNetEncapsulatedPacket();
    	final ByteArrayReader reader = new ByteArrayReader(binary);
    	
        final int flags = reader.readUnsignedByte();

        packet.reliability = ((flags & 0B11100000) >> 5);
        packet.hasSplit = (flags & 0B00010000) > 0;
       
        int length, offset;
        if(internal){
            length = (int) reader.readUnsignedInt();
            packet.identifierACK = (int) reader.readUnsignedInt();
            offset = 9;
        }else{
        	length = (int) Math.ceil(((double) reader.readUnsignedShort() / 8));
            offset = 3;
            packet.identifierACK = null;
        }

        reader.setPos(offset);
        
        if(packet.reliability > 0){
            if(packet.reliability >= 2 && packet.reliability != 5)
                packet.messageIndex = reader.readLTriad();

            if(packet.reliability <= 4 && packet.reliability != 2){
                packet.orderIndex = reader.readLTriad();
                packet.orderChannel = reader.readUnsignedByte();
            }
        }

        if(packet.hasSplit){
            packet.splitCount = (int) reader.readUnsignedInt();
            packet.splitID = reader.readUnsignedShort();
            packet.splitIndex = (int) reader.readUnsignedInt();
        }

        packet.buffer = reader.read(length);
        packet.offset = reader.getPos();
        
        reader.close();
        
        return packet;
    }

    public int getTotalLength() {
        return 3 + this.buffer.length + (this.messageIndex != null ? 3 : 0) + (this.orderIndex != null ? 4 : 0) + (this.hasSplit ? 10 : 0);
    }

    public byte[] toBinary() throws Exception {
        return toBinary(false);
    }

    public byte[] toBinary(boolean internal) throws Exception {
    	final ByteArrayWriter writer = new ByteArrayWriter();
       
        writer.write((reliability << 5) | (hasSplit ? 0b00010000 : 0));
        
        if(internal){
        	writer.writeUnsignedInt(buffer.length);
        	writer.writeUnsignedInt(identifierACK == null ? 0 : identifierACK);
        }else
        	writer.writeUnsignedShort(buffer.length << 3);
        
        if(reliability > 0){
            if(reliability >= 2 && reliability != 5)
                writer.writeLTriad(messageIndex == null ? 0 : messageIndex);
               
            if(reliability <= 4 && reliability != 2){
            	writer.writeLTriad(orderIndex);
                writer.writeUnsignedByte(orderChannel);
            }
        }

        if(hasSplit){
            writer.writeUnsignedInt(splitCount);
            writer.writeUnsignedShort(splitID);
            writer.writeUnsignedInt(splitIndex);
        }

        writer.write(buffer);
        
        final byte[] array = writer.toByteArray();
        writer.close();
        
        return array;
    }
}
