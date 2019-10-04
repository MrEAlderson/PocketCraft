package de.marcely.pocketcraft.java.network.protocol;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.sequence.Sequence;
import de.marcely.pocketcraft.java.network.sequence.SequenceHolder;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;

public abstract class Protocol {
	
	public static final byte CLIENT = 0;
	public static final byte SERVER = 1;
	
	
	private Class<Packet>[][] packets;
	
	public abstract int getProtocolVersion();
	
	public abstract Sequence newSequenceInstance(SequenceType type, SequenceHolder holder);
	
	public abstract void defineIds();
	
	
	@SuppressWarnings("unchecked")
	public Protocol(){
		this.packets = new Class[SequenceType.values().length*2][];
	}
	
	protected void setPacketIds(SequenceType sequence, byte source, Class<Packet>[] packets){
		this.packets[sequence.ordinal()*2+source] = packets;
		
		addIdToPackets(packets);
	}
	
	// TODO: Add packet pooling
	public @Nullable Packet getPacketById(int id, SequenceType sequence, byte source){
		final Class<Packet>[] packets = this.packets[sequence.ordinal()*2+source];
		
		if(id < 0 || id >= packets.length)
			return null;
		
		try{
			final Class<Packet> clazz = packets[id];
			
			return clazz.newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void addIdToPackets(Class<Packet>[] packets){
		for(int id=0; id<packets.length; id++){
			final Class<Packet> clazz = packets[id];
			
			if(clazz == null)
				continue;
			
			try{
				final PacketProperties properties = (PacketProperties) clazz.getField("PROPERTIES").get(null);
				
				properties.setId(getProtocolVersion(), id);
			}catch(Exception e){
				e.printStackTrace();
				throw new IllegalStateException("Missing or invalid PROPERTIES field for " + clazz.getName());
			}
		}
	}
}