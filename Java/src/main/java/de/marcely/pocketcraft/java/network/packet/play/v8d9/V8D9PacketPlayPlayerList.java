package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.java.component.GameMode;
import de.marcely.pocketcraft.java.component.chat.ChatBaseComponent;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayPlayerList extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public static final byte ACTION_ADD = 0;
	public static final byte ACTION_UPDATE_GAMEMODE = 1;
	public static final byte ACTION_UPDATE_LATENCY = 2;
	public static final byte ACTION_UPDATE_DISPLAY_NAME = 3;
	public static final byte ACTION_REMOVE = 4;
	
	
	public byte action;
	public PlayerListEntry[] entries;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeVarInt(this.action);
		stream.writeVarInt(this.entries.length);
		
		for(PlayerListEntry entry:this.entries){
			stream.writeUUID(entry.id);
			
			switch(action){
			case ACTION_ADD:
				stream.writeString(entry.name);
				stream.writeVarInt(entry.properties.length);
				
				for(PlayerListEntry.Property property:entry.properties){
					stream.writeString(property.name);
					stream.writeString(property.value);
					stream.writeBoolean(property.signature != null);
					
					if(property.signature != null)
						stream.writeString(property.signature);
				}
				
				stream.writeVarInt(entry.mode.getId());
				stream.writeVarInt(entry.ping);
				stream.writeBoolean(entry.displayName != null);
				
				if(entry.displayName != null)
					stream.writeChatPlain(entry.displayName);
				
				break;
				
			case ACTION_UPDATE_GAMEMODE:
				stream.writeVarInt(entry.mode.getId());
				
				break;
				
			case ACTION_UPDATE_LATENCY:
				stream.writeVarInt(entry.ping);
				
				break;
				
			case ACTION_UPDATE_DISPLAY_NAME:
				stream.writeBoolean(entry.displayName != null);
				
				if(entry.displayName != null)
					stream.writeChatPlain(entry.displayName);
				
				break;
				
			case ACTION_REMOVE:
				break;
			}
		}
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.action = (byte) stream.readVarInt();
		this.entries = new PlayerListEntry[stream.readVarInt()];
		
		for(int i=0; i<this.entries.length; i++){
			final PlayerListEntry entry = this.entries[i] = new PlayerListEntry(stream.readUUID());
			
			switch(this.action){
			case ACTION_ADD:
				entry.name = stream.readString();
				entry.properties = new PlayerListEntry.Property[stream.readVarInt()];
				
				for(int i1=0; i1<entry.properties.length; i1++){
					final PlayerListEntry.Property property = entry.properties[i1] = new PlayerListEntry.Property();
					
					property.name = stream.readString();
					property.value = stream.readString();
					
					if(stream.readBoolean())
						property.signature = stream.readString();
				}
				
				entry.mode = GameMode.ofId(stream.readVarInt());
				entry.ping = stream.readVarInt();
				
				if(stream.readBoolean())
					entry.displayName = stream.readChatPlain();
				
				break;
				
			case ACTION_UPDATE_GAMEMODE:
				entry.mode = GameMode.ofId(stream.readVarInt());
				
				break;
				
			case ACTION_UPDATE_LATENCY:
				entry.ping = stream.readVarInt();
				
				break;
				
			case ACTION_UPDATE_DISPLAY_NAME:
				if(stream.readBoolean())
					entry.displayName = stream.readChatPlain();
				
				break;
				
			case ACTION_REMOVE:
				break;
			}
		}
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
	
	
	public static class PlayerListEntry {
		
		public UUID id;
		public String name;
		public Property[] properties;
		public GameMode mode;
		public int ping;
		@Nullable public ChatBaseComponent displayName;
		
		// add
		public PlayerListEntry(UUID id, String name, Property[] properties, GameMode mode, int ping, @Nullable ChatBaseComponent displayName){
			this.id = id;
			this.name = name;
			this.properties = properties;
			this.mode = mode;
			this.ping = ping;
			this.displayName = displayName;
		}
		
		// update gamemode
		public PlayerListEntry(UUID id, GameMode mode){
			this.id = id;
			this.mode = mode;
		}
		
		// update latency
		public PlayerListEntry(UUID id, int ping){
			this.id = id;
			this.ping = ping;
		}
		
		// update display name
		public PlayerListEntry(UUID id, @Nullable ChatBaseComponent displayName){
			this.id = id;
			this.displayName = displayName;
		}
		
		// remove
		public PlayerListEntry(UUID id){
			this.id = id;
		}
		
		
		public static class Property {
			
			public String name;
			public String value;
			@Nullable public String signature;
			
			public Property(){ }
			
			public Property(String name, String value, String signature){
				this.name = name;
				this.value = value;
				this.signature = signature;
			}
		}
	}
}
