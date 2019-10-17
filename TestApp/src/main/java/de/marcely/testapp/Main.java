package de.marcely.testapp;

import java.io.File;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.whirvis.jraknet.RakNet;
import com.whirvis.jraknet.RakNetException;
import com.whirvis.jraknet.RakNetPacket;
import com.whirvis.jraknet.client.RakNetClient;
import com.whirvis.jraknet.client.RakNetClientListener;
import com.whirvis.jraknet.identifier.MinecraftIdentifier;
import com.whirvis.jraknet.peer.RakNetClientPeer;
import com.whirvis.jraknet.peer.RakNetServerPeer;
import com.whirvis.jraknet.protocol.Reliability;
import com.whirvis.jraknet.protocol.message.CustomPacket;
import com.whirvis.jraknet.protocol.message.EncapsulatedPacket;
import com.whirvis.jraknet.server.RakNetServer;
import com.whirvis.jraknet.server.RakNetServerListener;

import de.marcely.pocketcraft.bedrock.network.Protocol;
import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.bedrock.network.packet.PacketBatch;
import de.marcely.pocketcraft.bedrock.network.packet.PacketType;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import fr.bmartel.pcapdecoder.PcapDecoder;
import fr.bmartel.pcapdecoder.structure.types.IPcapngType;
import fr.bmartel.pcapdecoder.structure.types.inter.IEnhancedPacketBLock;

public class Main {
	
	public static void main(String[] args) throws Exception {
		final File output = new File("output.json");
		final Map<RakNetClientPeer, RakNetClient> clients = new HashMap<>();
		final RakNetServer server = new RakNetServer(InetAddress.getByName(args[0]), Integer.parseInt(args[1]), RakNetServer.INFINITE_CONNECTIONS);
		final InetSocketAddress serverAddress = new InetSocketAddress(InetAddress.getByName(args[0]), Integer.parseInt(args[2]));
		
		server.setIdentifier(new MinecraftIdentifier("cool server", Protocol.VERSION, "1.12.1", 0, 12, server.getGloballyUniqueId(), "cool world", "creative"));
		
		// open server
		{
			server.addListener(new RakNetServerListener(){
				@Override
				public void onLogin(RakNetServer server, RakNetClientPeer peer1){
					final RakNetClient client = new RakNetClient();
					
					client.addListener(new RakNetClientListener(){
						@Override
						public void handleMessage(RakNetClient client, RakNetServerPeer peer, RakNetPacket rawPacket, int channel){
							final RakNetPacket out = new RakNetPacket(rawPacket.getId());
							final byte[] data = rawPacket.read(rawPacket.remaining());
							
							out.write(data);
							
							peer1.sendMessage(Reliability.RELIABLE_ORDERED, 2, out);
							
							{
								final PacketType type = PacketType.TYPES.get(rawPacket.getId());
								
								if(type == null){
									System.out.println("Received weird packet " + RakNet.toHexStringId(rawPacket));
									return;
								}
								
								if(type != PacketType.Batch){
									System.out.println("Expected a Batch Packet but received " + type + " instead");
									return;
								}
								
								final PacketBatch packet = new PacketBatch();
								
								try{
									packet.decode(new EByteArrayReader(data));
									
									for(PCPacket child:packet.readPayload()){
										try{
											System.out.println("SERVER: " + child.getType());
										}catch(Exception e){
											e.printStackTrace();
										}
									}
								}catch(Exception e){
									e.printStackTrace();
								}
							}
						}
					});
					
					try{
						client.connect(InetAddress.getByName(args[0]), Integer.parseInt(args[2]));
					}catch(NullPointerException | IllegalArgumentException | IllegalStateException | UnknownHostException | RakNetException e){
						e.printStackTrace();
						peer1.disconnect();
						return;
					}
					
					clients.put(peer1, client);
				}
				
				@Override
				public void onDisconnect(RakNetServer server, InetSocketAddress address, RakNetClientPeer peer, String reason){
					System.out.println("disconnect: " + clients.get(peer) + " reason " + reason);
					clients.remove(peer).disconnect(reason);
				}
				
				@Override
				public void handleMessage(RakNetServer server, RakNetClientPeer peer, RakNetPacket rawPacket, int channel){
					final RakNetPacket out = new RakNetPacket(rawPacket.getId());
					final byte[] data = rawPacket.read(rawPacket.remaining());
					
					out.write(data);
					
					clients.get(peer).sendMessage(Reliability.RELIABLE_ORDERED, 2, out);
					
					{
						final PacketType type = PacketType.TYPES.get(rawPacket.getId());
						
						if(type == null){
							System.out.println("Received weird packet " + RakNet.toHexStringId(rawPacket));
							return;
						}
						
						if(type != PacketType.Batch){
							System.out.println("Expected a Batch Packet but received " + type + " instead");
							return;
						}
						
						final PacketBatch packet = new PacketBatch();
						
						try{
							packet.decode(new EByteArrayReader(data));
							
							for(PCPacket child:packet.readPayload()){
								try{
									System.out.println("CLIENT: " + child.getType());
								}catch(Exception e){
									e.printStackTrace();
								}
							}
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
			});
			
			server.start();
		}
	}
	
	public static void main1(String[] args) throws Exception {
		final File input = new File("input.pcapng");
		
		{
			final PcapDecoder decoder = new PcapDecoder(Files.readAllBytes(input.toPath()));
			
			decoder.decode();
			
			final List<IPcapngType> sections = decoder.getSectionList();
			
			for(IPcapngType rawSection:sections){
				if(!(rawSection instanceof IEnhancedPacketBLock))
					continue;
				
				
				final IEnhancedPacketBLock section = (IEnhancedPacketBLock) rawSection;
				final RakNetPacket fullPacket = new RakNetPacket(Arrays.copyOfRange(section.getPacketData(), 42 /* packet offset; skip udp header */, section.getPacketLength()));
				
				if(fullPacket.getId() < RakNetPacket.ID_CUSTOM_0 || fullPacket.getId() > RakNetPacket.ID_CUSTOM_F)
					continue;
				
				final CustomPacket custom = new CustomPacket(fullPacket);
				
				custom.decode();
				
				for(EncapsulatedPacket enc:custom.messages){
					if(enc.split){
						System.out.println("Skipped split packet");
						continue;
					}
					
					final RakNetPacket payload = new RakNetPacket(enc.payload);
					
					if(payload.getId() == RakNetPacket.ID_CONNECTED_PING || payload.getId() == RakNetPacket.ID_CONNECTED_PONG)
						continue;
					
					// we've decoded the packet: now parse it
					{
						final PacketType type = PacketType.TYPES.get(payload.getId());
						
						if(type != PacketType.Batch){
							System.out.println("Expected a Batch Packet but received " + type + " instead");
							continue;
						}
						
						{
							final PacketBatch packet = new PacketBatch();
							
							try{
								packet.decode(new EByteArrayReader(payload.read(payload.remaining())));
								
								for(PCPacket child:packet.readPayload()){
									try{
										System.out.println(child.getType());
									}catch(Exception e){
										e.printStackTrace();
									}
								}
							}catch(Exception e){
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		
		System.out.println("done");
	}
}
