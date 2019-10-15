package de.marcely.pocketcraft.bedrock.network.packet;

import java.util.UUID;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.SkinData;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketPlayerList extends PCPacket {
	
	public static final byte TYPE_ADD = 0x0;
	public static final byte TYPE_REMOVE = 0x1;
	
	public byte type;
	public PlayerListEntry[] entries;
	
	public PacketPlayerList(){
		super(PacketType.PlayerList);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeSignedByte(this.type);
		writer.writeUnsignedVarInt(this.entries.length);
		
		if(type == TYPE_ADD){
			for(PlayerListEntry entry:this.entries){
				writer.writeUUID(entry.uuid);
				writer.writeSignedVarLong(entry.entityId);
				writer.writeString(entry.name);
				entry.skin.write(writer);
				writer.writeString(entry.xboxId);
				writer.writeString(entry.platformChatId);
			}
		
		}else if(type == TYPE_REMOVE){
			for(PlayerListEntry entry:entries)
				writer.writeUUID(entry.uuid);
		}
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
	
	
	
	public static class PlayerListEntry {
		
		public UUID uuid;
		public long entityId;
		public String name,  platformChatId = "";
		public SkinData skin;
		public String xboxId;
		
		public PlayerListEntry(UUID uuid){ // only for remove
			this(uuid, 0, null, null, "");
		}
		
		public PlayerListEntry(UUID uuid, long entityId, String name, SkinData skin){
			this(uuid, entityId, name, skin, "");
		}
		
		public PlayerListEntry(UUID uuid, long entityId, String name, SkinData skin, String xboxId){
			this.uuid = uuid;
			this.entityId = entityId;
			this.name = name;
			this.skin = skin;
			this.xboxId = xboxId;
		}
	}
}
