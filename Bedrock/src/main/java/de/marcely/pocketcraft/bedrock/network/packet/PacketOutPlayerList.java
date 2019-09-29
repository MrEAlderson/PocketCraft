package de.marcely.pocketcraft.bedrock.network.packet;

import java.util.UUID;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.SkinData;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutPlayerList extends PCPacket {
	
	public static final byte TYPE_ADD = 0x0;
	public static final byte TYPE_REMOVE = 0x1;
	
	public byte type;
	public PlayerListEntry[] entries;
	
	public PacketOutPlayerList(){
		super(PacketType.OutPlayerList);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedByte(type);
		writer.writeUnsignedVarInt(entries.length);
		
		if(type == TYPE_ADD){
			for(PlayerListEntry entry:entries){
				writer.writeUUID(entry.uuid);
				writer.writeSignedVarLong(entry.entityID);
				writer.writeString(entry.name);
				writer.writeString(entry.thirdPartyName);
				writer.writeSignedVarInt(entry.platform);
				
				// TODO writer.writeSkin(entry.skin);
				writer.writeString("");
				writer.writeLInt(1);
				writer.writeString("");
				writer.writeLInt(0);
				writer.writeString("");
				writer.writeString("");
				
				writer.writeString(entry.xboxID);
				writer.writeString(entry.platformChatID);
			}
		
		}else if(type == TYPE_REMOVE){
			for(PlayerListEntry entry:entries)
				writer.writeUUID(entry.uuid);
		}
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
	
	
	
	public static class PlayerListEntry {
		
		public final UUID uuid;
		public final long entityID;
		public final String name, thirdPartyName = "", platformChatID = "";
		public final int platform = 0;
		public final SkinData skin;
		public final String xboxID;
		
		public PlayerListEntry(UUID uuid){ // only for remove
			this(uuid, 0, null, null, null);
		}
		
		public PlayerListEntry(UUID uuid, long entityID, String name, SkinData skin){
			this(uuid, entityID, name, skin, "Hidden");
		}
		
		public PlayerListEntry(UUID uuid, long entityID, String name, SkinData skin, String xboxID){
			this.uuid = uuid;
			this.entityID = entityID;
			this.name = name;
			this.skin = skin;
			this.xboxID = xboxID;
		}
	}
}
