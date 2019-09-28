package de.marcely.pocketcraft.raknet.packet.data;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.marcely.pocketcraft.raknet.packet.RakNetPacket;
import de.marcely.pocketcraft.raknet.packet.encapsulated.RakNetEncapsulatedPacket;
import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

/**
 * 
 * @author Marcel S.
 * @date 02.12.2017 (dd/MM/yyyy)
 * @website https://marcely.de/
 */
public class RakNetDataPacket extends RakNetPacket {
	
	public static final byte TYPE_0 = (byte) 0x80;
	public static final byte TYPE_1 = (byte) 0x81;
	public static final byte TYPE_2 = (byte) 0x82;
	public static final byte TYPE_3 = (byte) 0x83;
	public static final byte TYPE_4 = (byte) 0x84;
	public static final byte TYPE_5 = (byte) 0x85;
	public static final byte TYPE_6 = (byte) 0x86;
	public static final byte TYPE_7 = (byte) 0x87;
	public static final byte TYPE_8 = (byte) 0x88;
	public static final byte TYPE_9 = (byte) 0x89;
	public static final byte TYPE_A = (byte) 0x8A;
	public static final byte TYPE_B = (byte) 0x8B;
	public static final byte TYPE_C = (byte) 0x8C;
	public static final byte TYPE_D = (byte) 0x8D;
	public static final byte TYPE_E = (byte) 0x8E;
	public static final byte TYPE_F = (byte) 0x8F;
	
	public static final byte TYPE_DATA_CONNECT = (byte) 0x09;
	public static final byte TYPE_DATA_HANDSHAKE1 = (byte) 0x10;
	public static final byte TYPE_DATA_HANDSHAKE2 = (byte) 0x13;
	public static final byte TYPE_DATA_PING = (byte) 0x00;
	public static final byte TYPE_DATA_PONG = (byte) 0x03;
	public static final byte TYPE_DATA_DISCONNECT = (byte) 0x15;
	
	public Queue<Object> packets = new ConcurrentLinkedQueue<>();
    public int seqNumber;
	
	public RakNetDataPacket(byte id){
		super(id);
	}
	
	public void decode(ByteArrayReader reader) throws Exception {
		this.seqNumber = reader.readLTriad();
		
		int pos = reader.getPos();
		while(reader.available() >= 1){
			final byte[] data = reader.read(reader.available());
			final RakNetEncapsulatedPacket packet = RakNetEncapsulatedPacket.fromBinary(data, false);
			
			pos += packet.offset;
			reader.setPos(pos);
			
			this.packets.add(packet);
		}
	}
	
	public void encode(ByteArrayWriter writer) throws Exception {
		writer.writeLTriad(seqNumber);
		
        for(Object packet:this.packets)
            writer.write(packet instanceof RakNetEncapsulatedPacket ? ((RakNetEncapsulatedPacket) packet).toBinary() : (byte[]) packet);	
	}
	
    public int length(){
        int length = 4;
        
        for (Object packet:this.packets){
            length += packet instanceof RakNetEncapsulatedPacket ?
            		((RakNetEncapsulatedPacket) packet).getTotalLength() :
            			((byte[]) packet).length;
        }
        
        return length;
    }
} 