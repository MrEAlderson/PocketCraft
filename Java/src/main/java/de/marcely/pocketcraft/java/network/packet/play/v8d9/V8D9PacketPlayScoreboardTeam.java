package de.marcely.pocketcraft.java.network.packet.play.v8d9;

import java.util.ArrayList;
import java.util.List;

import de.marcely.pocketcraft.java.component.chat.ChatColor;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.PlayPacket;
import de.marcely.pocketcraft.java.util.EByteBuf;

public class V8D9PacketPlayScoreboardTeam extends PlayPacket {

	public static final PacketProperties PROPERTIES = new PacketProperties();
	
	public static final byte MODE_CREATE = 0;
	public static final byte MODE_REMOVE = 1;
	public static final byte MODE_INFO_UPDATE = 2;
	public static final byte MODE_ADD_PLAYERS = 3;
	public static final byte MODE_REMOVE_PLAYERS = 3;
	
	public static final byte FRIENDLY_FIRE_OFF = 0;
	public static final byte FRIENDLY_FIRE_ON = 1;
	public static final byte FRIENDLY_FIRE_SEE_FRIENDLY_INVISIBLES = 3;
	
	public static final String NAME_TAG_VISIBILITY_ALWAYS = "always";
	public static final String NAME_TAG_VISIBILITY_HIDE_FOR_OTHER_TEAMS = "hideForOtherTeams";
	public static final String NAME_TAG_VISIBILITY_HIDE_FOR_OWN_TEAM = "hideForOwnTeam";
	public static final String NAME_TAG_VISIBILITY_NEVER = "never";
	
	
	public String name;
	public byte mode;
	
	// only if create or info update
	public String teamDisplayName;
	public String teamPrefix;
	public String teamSuffix;
	public byte friendlyFire;
	public String nameTagVisibility;
	public ChatColor color;
	
	// only when create or add/remove players
	public List<String> players;
	
	@Override
	public void write(EByteBuf stream) throws Exception {
		stream.writeString(this.name, 16);
		stream.writeByte(this.mode);
		
		if(mode == MODE_CREATE || mode == MODE_INFO_UPDATE){
			stream.writeString(this.teamDisplayName, 32);
			stream.writeString(this.teamPrefix, 16);
			stream.writeString(this.teamSuffix, 16);
			stream.writeByte(this.friendlyFire);
			stream.writeString(this.nameTagVisibility, 32);
			stream.writeByte(this.color.getId());
		}
		
		if(mode == MODE_CREATE || mode == MODE_ADD_PLAYERS || mode == MODE_REMOVE_PLAYERS){
			stream.writeVarInt(this.players.size());
			
			for(String player:this.players)
				stream.writeString(player, 40);
		}
	}

	@Override
	public void read(EByteBuf stream) throws Exception {
		this.name = stream.readString(16);
		this.mode = stream.readByte();
		
		if(mode == MODE_CREATE || mode == MODE_INFO_UPDATE){
			this.teamDisplayName = stream.readString(32);
			this.teamPrefix = stream.readString(16);
			this.teamSuffix = stream.readString(16);
			this.friendlyFire = stream.readByte();
			this.nameTagVisibility = stream.readString(32);
			this.color = ChatColor.getById(stream.readByte());
		}
		
		if(mode == MODE_CREATE || mode == MODE_ADD_PLAYERS || mode == MODE_REMOVE_PLAYERS){
			final int size = stream.readVarInt();
			
			this.players = new ArrayList<>(size);
			
			for(int i=0; i<size; i++)
				this.players.add(stream.readString());
		}
	}

	@Override
	public PacketProperties getProperties(){
		return PROPERTIES;
	}
}