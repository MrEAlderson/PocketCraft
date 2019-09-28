package de.marcely.pocketcraft.raknet.packet;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.raknet.packet.data.RakNetData0Packet;
import de.marcely.pocketcraft.raknet.packet.data.RakNetData1Packet;
import de.marcely.pocketcraft.raknet.packet.data.RakNetData2Packet;
import de.marcely.pocketcraft.raknet.packet.data.RakNetData3Packet;
import de.marcely.pocketcraft.raknet.packet.data.RakNetData4Packet;
import de.marcely.pocketcraft.raknet.packet.data.RakNetData5Packet;
import de.marcely.pocketcraft.raknet.packet.data.RakNetData6Packet;
import de.marcely.pocketcraft.raknet.packet.data.RakNetData7Packet;
import de.marcely.pocketcraft.raknet.packet.data.RakNetData8Packet;
import de.marcely.pocketcraft.raknet.packet.data.RakNetData9Packet;
import de.marcely.pocketcraft.raknet.packet.data.RakNetDataAPacket;
import de.marcely.pocketcraft.raknet.packet.data.RakNetDataBPacket;
import de.marcely.pocketcraft.raknet.packet.data.RakNetDataCPacket;
import de.marcely.pocketcraft.raknet.packet.data.RakNetDataDPacket;
import de.marcely.pocketcraft.raknet.packet.data.RakNetDataEPacket;
import de.marcely.pocketcraft.raknet.packet.data.RakNetDataFPacket;
import de.marcely.pocketcraft.raknet.packet.data.RakNetDataPacket;
import de.marcely.pocketcraft.raknet.packet.encapsulated.RakNetDataConnectPacket;
import de.marcely.pocketcraft.raknet.packet.encapsulated.RakNetDataDisconnectPacket;
import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

/**
 * 
 * @author Marcel S.
 * @date 01.12.2017 (dd/MM/yyyy)
 * @website https://marcely.de/
 */
public abstract class RakNetPacket {
	
	public static final byte[] MAGIC = new byte[]{ 0x00, (byte) 0xFF, (byte) 0xFF, 0x00, (byte) 0xFE, (byte) 0xFE, (byte) 0xFE, (byte) 0xFE,
												   (byte) 0xFD, (byte) 0xFD, (byte) 0xFD, (byte) 0xFD, 0x12, 0x34, 0x56, 0x78 };
  
	public static final byte PRIORITY_NORMAL = 0;
    public static final byte PRIORITY_IMMEDIATE = 1;
	
    public static final byte FLAG_NEED_ACK = 0b00001000;
    
	public static final byte TYPE_CONNECTED_PING = 0x00;
	public static final byte TYPE_CONNECTED_PONG = 0x03;
	public static final byte TYPE_UNCONNECTED_PING = 0x01;
	public static final byte TYPE_UNCONNECTED_PONG = 0x01C;
	public static final byte TYPE_CONNECT_REQUEST1 = 0x05;
	public static final byte TYPE_CONNECT_RESPONSE1 = 0x06;
	public static final byte TYPE_CONNECT_REQUEST2 = 0x07;
	public static final byte TYPE_CONNECT_RESPONSE2 = 0x08;
	public static final byte TYPE_ACK = (byte) 0xC0;
	public static final byte TYPE_NACK = (byte) 0xA0;
	
	public static final byte STREAM_PACKET_ENCAPSULATED = 0x01;
	public static final byte STREAM_PACKET_OPEN_SESSION = 0x02;
	public static final byte STREAM_PACKET_CLOSE_SESSION = 0x03;
	
	public final byte _id;
	public long sendTime;
	public byte[] receive_rawData;
	
	public RakNetPacket(byte id){
		this._id = id;
	}
	
	public abstract void decode(ByteArrayReader reader) throws Exception;
	
	public abstract void encode(ByteArrayWriter writer) throws Exception;
	
	
	public static @Nullable RakNetPacket newInstance(int id){
		switch(id){
		case TYPE_UNCONNECTED_PING:
			return new RakNetUnconnectedPingPacket();
		case TYPE_UNCONNECTED_PONG:
			return new RakNetUnconnectedPongPacket();
		case TYPE_CONNECT_REQUEST1:
			return new RakNetConnectRequest1Packet();
		case TYPE_CONNECT_RESPONSE1:
			return new RakNetConnectResponse1Packet();
		case TYPE_CONNECT_REQUEST2:
			return new RakNetConnectRequest2Packet();
		case TYPE_CONNECT_RESPONSE2:
			return new RakNetConnectResponse2Packet();
		case RakNetDataPacket.TYPE_0:
			return new RakNetData0Packet();
		case RakNetDataPacket.TYPE_1:
			return new RakNetData1Packet();
		case RakNetDataPacket.TYPE_2:
			return new RakNetData2Packet();
		case RakNetDataPacket.TYPE_3:
			return new RakNetData3Packet();
		case RakNetDataPacket.TYPE_4:
			return new RakNetData4Packet();
		case RakNetDataPacket.TYPE_5:
			return new RakNetData5Packet();
		case RakNetDataPacket.TYPE_6:
			return new RakNetData6Packet();
		case RakNetDataPacket.TYPE_7:
			return new RakNetData7Packet();
		case RakNetDataPacket.TYPE_8:
			return new RakNetData8Packet();
		case RakNetDataPacket.TYPE_9:
			return new RakNetData9Packet();
		case RakNetDataPacket.TYPE_A:
			return new RakNetDataAPacket();
		case RakNetDataPacket.TYPE_B:
			return new RakNetDataBPacket();
		case RakNetDataPacket.TYPE_C:
			return new RakNetDataCPacket();
		case RakNetDataPacket.TYPE_D:
			return new RakNetDataDPacket();
		case RakNetDataPacket.TYPE_E:
			return new RakNetDataEPacket();
		case RakNetDataPacket.TYPE_F:
			return new RakNetDataFPacket();
		case RakNetDataPacket.TYPE_DATA_CONNECT:
			return new RakNetDataConnectPacket();
		case RakNetDataPacket.TYPE_DATA_DISCONNECT:
			return new RakNetDataDisconnectPacket();
		case RakNetPacket.TYPE_ACK:
			return new RakNetAckPacket();
		case RakNetPacket.TYPE_NACK:
			return new RakNetNackPacket();
		}
		
		return null;
	}
}