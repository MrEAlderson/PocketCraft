package de.marcely.pocketcraft.raknet.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListMap;

import de.marcely.pocketcraft.raknet.packet.RakNetAckPacket;
import de.marcely.pocketcraft.raknet.packet.RakNetConnectRequest2Packet;
import de.marcely.pocketcraft.raknet.packet.RakNetConnectResponse2Packet;
import de.marcely.pocketcraft.raknet.packet.RakNetNackPacket;
import de.marcely.pocketcraft.raknet.packet.RakNetPacket;
import de.marcely.pocketcraft.raknet.packet.data.RakNetData0Packet;
import de.marcely.pocketcraft.raknet.packet.data.RakNetData4Packet;
import de.marcely.pocketcraft.raknet.packet.data.RakNetDataPacket;
import de.marcely.pocketcraft.raknet.packet.encapsulated.RakNetDataConnectPacket;
import de.marcely.pocketcraft.raknet.packet.encapsulated.RakNetDataHandshake1Packet;
import de.marcely.pocketcraft.raknet.packet.encapsulated.RakNetDataHandshake2Packet;
import de.marcely.pocketcraft.raknet.packet.encapsulated.RakNetDataPingPacket;
import de.marcely.pocketcraft.raknet.packet.encapsulated.RakNetDataPongPacket;
import de.marcely.pocketcraft.raknet.packet.encapsulated.RakNetEncapsulatedPacket;
import de.marcely.pocketcraft.utils.io.ByteArrayReader;
import de.marcely.pocketcraft.utils.io.ByteArrayWriter;
import de.marcely.pocketcraft.utils.Util;

/**
 * 
 * @author Marcel S.
 * @date 02.12.2017 (dd/MM/yyyy)
 * @website https://marcely.de/
 */
public class ClientHandler {
	
	private static final int WINDOW_SIZE = 2048;
    private final static int MAX_SPLIT_SIZE = 128;
    private final static int MAX_SPLIT_COUNT = 4;
	private final static long TIMEOUT = 30*1000;
    
	private final Client client;
	
	private RakNetDataPacket sendDataQueue;
	private final Map<Integer, Map<Integer, Integer>> needACK = new TreeMap<>();
	private Queue<RakNetDataPacket> sendQueue = new ConcurrentLinkedQueue<>();
	private Map<Integer, Integer> sendACKQueue = new HashMap<>(), sendNACKQueue = new HashMap<>();
	private int lastSeqNumber = -1, sendSeqNumber = 0, messageIndex = 0;
	private final Map<Integer, RakNetDataPacket> recoveryQueue = new ConcurrentSkipListMap<>();
	private final Map<Integer, Integer> channelIndex = new ConcurrentHashMap<>();
	private int mtuSize = 548; //Min size
	private int splitID = 0;
	private final Map<Integer, Integer> receivedWindow = new TreeMap<>();
	private int windowStart = -1, windowEnd = WINDOW_SIZE;
    private int reliableWindowStart = 0;
    private int reliableWindowEnd = WINDOW_SIZE;
    private final Map<Integer, RakNetEncapsulatedPacket> reliableWindow = new TreeMap<>();
    private int lastReliableIndex = -1;
    private final Map<Integer, Map<Integer, RakNetEncapsulatedPacket>> splitPackets = new HashMap<>();
    private int sendACKID = 0;
    private long lastPingUpdate;
    private boolean isRunning = false;
	
    public boolean portChecking = true;
    
	public ClientHandler(Client client){
		this.client = client;
		this.sendDataQueue = new RakNetData4Packet();
		
        for(int i=0; i<32; i++)
            this.channelIndex.put(i, 0);
	}
	
	public boolean isRunning(){
		return isRunning;
	}
	
	public boolean run(){
		if(isRunning()) return false;
		
		lastPingUpdate = System.currentTimeMillis();
		isRunning = true;
		
		return true;
	}
	
	public void update(long time) throws IOException {
		RakNetPacket packet = null;
		
		// send ack
		if(sendACKQueue.size() >= 1){
			final RakNetAckPacket np = new RakNetAckPacket();
			
			np.packets.putAll(sendACKQueue);
			sendReadyPacket(np);
			sendACKQueue.clear();
			np.packets.clear();
		}
		
		// send nack
		if(sendNACKQueue.size() >= 1){
			final RakNetNackPacket np = new RakNetNackPacket();
			
			np.packets.putAll(sendNACKQueue);
			sendReadyPacket(np);
			sendNACKQueue.clear();
			np.packets.clear();
		}
		
		// send
		int amount = 0;
		while((packet = sendQueue.poll()) != null){
			final RakNetDataPacket dPacket = (RakNetDataPacket) packet;
			
			packet.sendTime = time;
			
			recoveryQueue.put(dPacket.seqNumber, dPacket);
			
			sendReadyPacket(packet);
			
			if(amount++ >= 10) break;
		}
		
		// recovery queue
        for(int seq:this.recoveryQueue.keySet()){
            final RakNetDataPacket pk = this.recoveryQueue.get(seq);
            
            if(pk.sendTime < System.currentTimeMillis() - 8000){
            	this.sendQueue.add(pk);
            	this.recoveryQueue.remove(seq);
            }else
                break;
        }

        // received windows
        for(int seq:new ArrayList<>(this.receivedWindow.keySet())){
            if(seq < this.windowStart){
                this.receivedWindow.remove(seq);
            }else
                break;
        }
        
        sendDataQueue();
	}
	
	public void update2(){
		if(System.currentTimeMillis() > lastPingUpdate + TIMEOUT)
			client.disconnect("Timeout");
	}
	
	public boolean stop(){
		if(!isRunning()) return false;
		
		isRunning = false;
		
		sendQueue.clear();
		
		return true;
	}
	
    public void sendDataQueue() throws IOException {
        if(!this.sendDataQueue.packets.isEmpty()){
            this.sendDataQueue.seqNumber = sendSeqNumber++;
            this.sendReadyPacket(sendDataQueue);
            this.sendDataQueue.sendTime = System.currentTimeMillis();
            this.recoveryQueue.put(this.sendDataQueue.seqNumber, this.sendDataQueue);
            this.sendDataQueue = new RakNetData4Packet();
        }
    }
	
	private void preSendEncapsulatedPacket(RakNetEncapsulatedPacket packet) throws Exception {
		preSendEncapsulatedPacket(packet, RakNetPacket.PRIORITY_NORMAL);
	}
	
	private void preSendEncapsulatedPacket(RakNetEncapsulatedPacket packet, int flags) throws Exception {
        final int priority = flags & 0b0000111;
        
        if(packet.needACK && packet.messageIndex != null){
            if(!this.needACK.containsKey(packet.identifierACK))
                this.needACK.put(packet.identifierACK, new HashMap<>());
            
            this.needACK.get(packet.identifierACK).put(packet.messageIndex, packet.messageIndex);
        }

        if(priority == RakNetPacket.PRIORITY_IMMEDIATE){ //Skip queues
            final RakNetData0Packet np = new RakNetData0Packet();
            np.seqNumber = this.sendSeqNumber++;
            
            if(packet.needACK){
                np.packets.add(packet);
                packet.needACK = false;
            }else
                np.packets.add(packet.toBinary());

            this.sendReadyPacket(np);
            np.sendTime = System.currentTimeMillis();
            this.recoveryQueue.put(np.seqNumber, np);

            return;
        }
        
        if(packet.getTotalLength() + this.sendDataQueue.length() > this.mtuSize)
        	sendDataQueue();

        if (packet.needACK){
            this.sendDataQueue.packets.add(packet);
            packet.needACK = false;
        }else
            this.sendDataQueue.packets.add(packet.toBinary());
	}
	
    public void sendEncapsulatedPacket(RakNetEncapsulatedPacket packet) throws Exception {
        sendEncapsulatedPacket(packet, RakNetPacket.PRIORITY_NORMAL);
    }
	
	public void sendEncapsulatedPacket(RakNetEncapsulatedPacket packet, int flags) throws Exception {
        if((packet.needACK = (flags & RakNetPacket.FLAG_NEED_ACK) > 0))
            this.needACK.put(packet.identifierACK, new HashMap<>());
        
        if(packet.reliability == 2 ||
                packet.reliability == 3 ||
                packet.reliability == 4 ||
                packet.reliability == 6 ||
                packet.reliability == 7) {
            packet.messageIndex = this.messageIndex++;
            
            if(packet.reliability == 3){
                final int index = this.channelIndex.get(packet.orderChannel) + 1;
                
                packet.orderIndex = index;
                channelIndex.put(packet.orderChannel, index);
            }
        }

        if(packet.getTotalLength() + 4 > this.mtuSize){
            final byte[][] buffers = Util.splitBytes(packet.buffer, this.mtuSize - 34);
            final int splitID = ++this.splitID % 65536;
            
            for (int count = 0; count < buffers.length; count++) {
                final byte[] buffer = buffers[count];
                final RakNetEncapsulatedPacket pk = new RakNetEncapsulatedPacket();
                
                pk.splitID = splitID;
                pk.hasSplit = true;
                pk.splitCount = buffers.length;
                pk.reliability = packet.reliability;
                pk.splitIndex = count;
                pk.buffer = buffer;
                if(count > 0)
                    pk.messageIndex = this.messageIndex++;
                else
                    pk.messageIndex = packet.messageIndex;
                
                if(pk.reliability == 3){
                    pk.orderChannel = packet.orderChannel;
                    pk.orderIndex = packet.orderIndex;
                }
                
                this.preSendEncapsulatedPacket(pk, flags | RakNetPacket.PRIORITY_IMMEDIATE);
            }
        }else
            this.preSendEncapsulatedPacket(packet, flags);
	}
	
	public void sendReadyPacket(RakNetPacket packet){
		client.server.sendPacket(client.address, client.port, packet);
	}
	
	public void handleNow(RakNetPacket rawPacket) throws Exception {
		if(rawPacket._id == RakNetPacket.TYPE_CONNECT_REQUEST2){
			final RakNetConnectResponse2Packet nPacket = new RakNetConnectResponse2Packet();
			final RakNetConnectRequest2Packet packet = (RakNetConnectRequest2Packet) rawPacket;
			nPacket.serverID = client.server.serverID;
			nPacket.ip = new InetSocketAddress(InetAddress.getLocalHost(), client.server.getPort());
			nPacket.mtuSize = packet.mtuSize;
			
			sendReadyPacket(nPacket);
			
			client.clientID = packet.clientID;
			mtuSize = Math.min(Math.abs((packet.mtuSize)), 1464);
			
		}else if(rawPacket instanceof RakNetDataPacket){
			if((rawPacket.receive_rawData[0] & 0xFF) < 0x80 || (rawPacket.receive_rawData[0] & 0xFF) > 0x8F)
				return;
			
			final RakNetDataPacket packet = (RakNetDataPacket) rawPacket;
			final int diff = packet.seqNumber - this.lastSeqNumber;
			
            if(packet.seqNumber < this.windowStart || packet.seqNumber > this.windowEnd || this.receivedWindow.containsKey(packet.seqNumber))
            	return;
			
			sendNACKQueue.remove(packet.seqNumber);
			sendACKQueue.put(packet.seqNumber, packet.seqNumber);
			receivedWindow.put(packet.seqNumber, packet.seqNumber);
			
			if(diff != 1){
				for(int i=lastSeqNumber+1; i<packet.seqNumber; i++){
                    if(!this.receivedWindow.containsKey(i))
                        this.sendNACKQueue.put(i, i);
				}
			}
			
			if(diff >= 1){
                this.lastSeqNumber = packet.seqNumber;
                this.windowStart += diff;
                this.windowEnd += diff;
			}
			
			for(Object raw:packet.packets){
				if(raw instanceof RakNetEncapsulatedPacket)
					handleEncapsulatedPacket((RakNetEncapsulatedPacket) raw);
			}
		
		}else if(rawPacket instanceof RakNetNackPacket){
			final RakNetNackPacket packet = (RakNetNackPacket) rawPacket;
			
            for(int seq : new ArrayList<>(packet.packets.values())){
                if(this.recoveryQueue.containsKey(seq)){
                    final RakNetDataPacket pk = this.recoveryQueue.get(seq);
                    pk.seqNumber = this.sendSeqNumber++;
                    
                    sendReadyPacket(pk);
                    this.recoveryQueue.remove(seq);
                }
            }
		
		}else if(rawPacket instanceof RakNetAckPacket){
			final RakNetAckPacket packet = (RakNetAckPacket) rawPacket;
			
            for(int seq:new ArrayList<>(packet.packets.values())){
                if(this.recoveryQueue.containsKey(seq)){
                    for(Object pk:this.recoveryQueue.get(seq).packets){
                        if(pk instanceof RakNetEncapsulatedPacket && ((RakNetEncapsulatedPacket) pk).needACK && ((RakNetEncapsulatedPacket) pk).messageIndex != null){
                            if(this.needACK.containsKey(((RakNetEncapsulatedPacket) pk).identifierACK))
                                this.needACK.get(((RakNetEncapsulatedPacket) pk).identifierACK).remove(((RakNetEncapsulatedPacket) pk).messageIndex);
                        }
                    }
                    
                    this.recoveryQueue.remove(seq);
                }
            }
		}
	}
	
	private void handleEncapsulatedPacket(RakNetEncapsulatedPacket packet) throws Exception {
		if(packet.messageIndex == null)
            this.handleEncapsulatedPacketRoute(packet);
		else{
            if(packet.messageIndex < this.reliableWindowStart || packet.messageIndex > this.reliableWindowEnd)
            	return;
            
            if((packet.messageIndex - this.lastReliableIndex) == 1){
                this.lastReliableIndex++;
                this.reliableWindowStart++;
                this.reliableWindowEnd++;
                this.handleEncapsulatedPacketRoute(packet);

                if(!this.reliableWindow.isEmpty()){
                    final TreeMap<Integer, RakNetEncapsulatedPacket> sortedMap = new TreeMap<>(this.reliableWindow);

                    for(int index:sortedMap.keySet()){
                        final RakNetEncapsulatedPacket pk = this.reliableWindow.get(index);

                        if((index - this.lastReliableIndex) != 1)
                            break;

                        this.lastReliableIndex++;
                        this.reliableWindowStart++;
                        this.reliableWindowEnd++;
                        this.handleEncapsulatedPacketRoute(pk);
                        this.reliableWindow.remove(index);
                    }
                }
            }else
                this.reliableWindow.put(packet.messageIndex, packet);
        }
	}
	
	private void handleEncapsulatedPacketRoute(RakNetEncapsulatedPacket packet) throws Exception {
		if(packet.hasSplit){
			if(client.state == ClientState.CONNECTED)
				handleSplittedEncapsulatedPacket(packet);
			
			return;
		}
		
		final ByteArrayReader reader = new ByteArrayReader(packet.buffer);
		final byte id = reader.readSignedByte();
		
		if((id & 0xFF) < 0x80){
			if(id == RakNetDataPacket.TYPE_DATA_DISCONNECT){
				client.disconnect("Disconnected");
			
			}else if(id == RakNetDataPacket.TYPE_DATA_CONNECT && client.state == ClientState.CONNECTING_1){
				final RakNetDataConnectPacket dPacket = new RakNetDataConnectPacket();
				dPacket.decode(reader);
				reader.close();
				
				final RakNetDataHandshake1Packet np = new RakNetDataHandshake1Packet();
				
				np.address = client.address;
				np.port = client.port;
				np.ping = dPacket.ping;
				np.pong = dPacket.ping+1000L;
				
				final ByteArrayWriter writer = new ByteArrayWriter();
				writer.writeSignedByte(np._id);
				np.encode(writer);
				
				final RakNetEncapsulatedPacket ep = new RakNetEncapsulatedPacket();
				ep.reliability = 0;
				ep.buffer = writer.toByteArray();
				
				writer.close();
				
				preSendEncapsulatedPacket(ep, RakNetPacket.PRIORITY_IMMEDIATE);
				client.state = ClientState.CONNECTING_2;
				
			}else if(id == RakNetDataPacket.TYPE_DATA_HANDSHAKE2 && client.state == ClientState.CONNECTING_2){
				final RakNetDataHandshake2Packet dPacket = new RakNetDataHandshake2Packet();
				dPacket.decode(reader);
				reader.close();
				
				if(dPacket.ip.getPort() == this.client.server.getPort() || !this.portChecking){
					client.state = ClientState.CONNECTED;
					client.server.onConnect(client);
				}
			
			}else if(client.state == ClientState.CONNECTED){
				if(id == RakNetDataPacket.TYPE_CONNECTED_PING){
					final RakNetDataPingPacket dpacket = new RakNetDataPingPacket();
					dpacket.decode(reader);
					reader.close();
					
					// pong
					final RakNetDataPongPacket out1 = new RakNetDataPongPacket();
					out1.ID = dpacket.ID;
					
					ByteArrayWriter writer = new ByteArrayWriter();
					writer.writeSignedByte(RakNetDataPacket.TYPE_CONNECTED_PONG);
					out1.encode(writer);
					
					RakNetEncapsulatedPacket eout = new RakNetEncapsulatedPacket();
					eout.reliability = 0;
					eout.buffer = writer.toByteArray();
					preSendEncapsulatedPacket(eout);
					
					// ping
					final RakNetDataPingPacket out2 = new RakNetDataPingPacket();
					out2.ID = System.currentTimeMillis();
					
					writer = new ByteArrayWriter();
					writer.writeSignedByte(RakNetDataPacket.TYPE_CONNECTED_PING);
					out2.encode(writer);
					
					eout = new RakNetEncapsulatedPacket();
					eout.reliability = 0;
					eout.buffer = writer.toByteArray();
					preSendEncapsulatedPacket(eout);
					
					lastPingUpdate = System.currentTimeMillis();
					
				}else if(id == RakNetDataPacket.TYPE_CONNECTED_PONG){
					final RakNetDataPongPacket dpacket = new RakNetDataPongPacket();
					reader.close();
					
					final RakNetDataPingPacket out = new RakNetDataPingPacket();
					out.ID = (System.currentTimeMillis()-dpacket.ID)/10;
					
					final ByteArrayWriter writer = new ByteArrayWriter();
					out.encode(writer);
					
					packet.buffer = writer.toByteArray();
					writer.close();
					
					sendReadyPacket(out);
					this.client.server.handleEncapsulated(client, packet, RakNetDataPacket.PRIORITY_NORMAL);
				}
			}
		}else if(client.state == ClientState.CONNECTED)
			this.client.server.handleEncapsulated(client, packet, RakNetDataPacket.PRIORITY_NORMAL);
	}
	
    @SuppressWarnings("serial")
	private void handleSplittedEncapsulatedPacket(RakNetEncapsulatedPacket packet) throws Exception {
        if(packet.splitCount >= MAX_SPLIT_SIZE || packet.splitIndex >= MAX_SPLIT_SIZE || packet.splitIndex < 0)
        	return;
        
        if(!this.splitPackets.containsKey(packet.splitID)){
            if(this.splitPackets.size() >= MAX_SPLIT_COUNT)
                return;
            
            this.splitPackets.put(packet.splitID, new HashMap<Integer, RakNetEncapsulatedPacket>(){{
                put(packet.splitIndex, packet);
            }});
            
        }else
            this.splitPackets.get(packet.splitID).put(packet.splitIndex, packet);
        
        if(this.splitPackets.get(packet.splitID).size() == packet.splitCount){
            final RakNetEncapsulatedPacket pk = new RakNetEncapsulatedPacket();
            final ByteArrayWriter writer = new ByteArrayWriter();
            
            for(int i=0; i<packet.splitCount; i++)
                writer.write(this.splitPackets.get(packet.splitID).get(i).buffer);
            
            pk.buffer = writer.toByteArray();
            writer.close();
            pk.length = pk.buffer.length;
            this.splitPackets.remove(packet.splitID);
            
            this.handleEncapsulatedPacketRoute(pk);
        }
    }
    
    public void sendPacket(byte payload, Integer channel, boolean needACK, boolean immediate) throws Exception {
		final ByteArrayWriter writer = new ByteArrayWriter();
		writer.writeSignedByte((byte) 0xFE);
		writer.write(payload);
		
		final RakNetEncapsulatedPacket rPacket = new RakNetEncapsulatedPacket();
		
		rPacket.reliability = 2;
		rPacket.buffer = writer.toByteArray();
		
		if(channel != null){
			rPacket.orderChannel = channel;
			rPacket.orderIndex = 0;
		}
		
		if(needACK)
			rPacket.identifierACK = this.sendACKID++;
		
		writer.close();
		
		sendEncapsulatedPacket(rPacket, (needACK ? RakNetPacket.FLAG_NEED_ACK : 0) | (!immediate ? RakNetPacket.PRIORITY_NORMAL : RakNetPacket.PRIORITY_IMMEDIATE));
    }
}
