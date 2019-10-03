package de.marcely.pocketcraft.java.network.protocol;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.sequence.Sequence;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;

public abstract class Protocol {
	
	private Class<Packet>[] clientPlayPackets, serverPlayPackets;
	
	public abstract int getProtocolVersion();
	
	public abstract Sequence newSequenceInstance(SequenceType type);
	
	public abstract void defineIds();
	
	
	// TODO: Add packet pooling
	public @Nullable Packet getPacketByClientPlayId(int id){
		if(id < 0 || id >= this.clientPlayPackets.length)
			return null;
		
		try{
			final Class<Packet> clazz = this.clientPlayPackets[id];
			
			return clazz.newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public @Nullable Packet getPacketByServerPlayId(int id){
		if(id < 0 || id >= this.serverPlayPackets.length)
			return null;
		
		try{
			final Class<Packet> clazz = this.serverPlayPackets[id];
			
			return clazz.newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	protected void setClientPlayPackets(Class<Packet>... packets){
		this.clientPlayPackets = packets;
		
		addIdToPackets(packets);
	}
	
	@SuppressWarnings("unchecked")
	protected void setServerPlayPackets(Class<Packet>... packets){
		this.serverPlayPackets = packets;
		
		addIdToPackets(packets);
	}
	
	private void addIdToPackets(Class<Packet>[] packets){
		for(int id=0; id<packets.length; id++){

			final Class<Packet> clazz = packets[id];
			
			try{
				final PacketProperties properties = (PacketProperties) clazz.getField("PROPERTIES").get(null);
				
				properties.setId(getProtocolVersion(), id);
			}catch(Exception e){
				throw new IllegalStateException("Missing or invalid PROPERTIES field for " + clazz.getName());
			}
		}
	}
}