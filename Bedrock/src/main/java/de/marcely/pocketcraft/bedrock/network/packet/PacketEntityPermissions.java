package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketEntityPermissions extends PCPacket {
	
	public static final int FLAG1_WORLD_IMMUTABLE = 0x001;
	public static final int FLAG1_NO_PVP = 0x002;
	public static final int FLAG1_AUTO_JUMP = 0x020;
	public static final int FLAG1_ALLOW_FLIGHT = 0x040;
	public static final int FLAG1_NO_CLIP = 0x080;
	public static final int FLAG1_WORLD_BUILDER = 0x100;
	public static final int FLAG1_FLYING = 0x200;
	public static final int FLAG1_MUTED = 0x400;
	
	public static final int FLAG2_BUILD_AND_MINE = 0x001;
	public static final int FLAG2_DOORS_AND_SWITCHES = 0x002;
	public static final int FLAG2_OPEN_CONTAINERS = 0x004;
	public static final int FLAG2_ATTACK_PLAYERS = 0x008;
	public static final int FLAG2_ATTACK_MOBS = 0x010;
	public static final int FLAG2_OPERATOR = 0x020;
	public static final int FLAG2_TELEPORT = 0x080;
	
	public long entityUID;
	public long flags1 = 0, flags2 = 0, customFlags = 0;
	public PermissionLevel permLevel;
	public CommandPermissionLevel cmdPermLevel;
	
	public PacketEntityPermissions(){
		super(PacketType.EntityPermissions);
	}
	
	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarInt(flags1);
		writer.writeUnsignedVarInt(cmdPermLevel.id);
		writer.writeUnsignedVarInt(flags2);
		writer.writeUnsignedVarInt(permLevel.id);
		writer.writeUnsignedVarInt(customFlags);
		writer.writeLLong(entityUID);
	}
	
	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
	
	public static PacketEntityPermissions getDefault(long entityUID){
		final PacketEntityPermissions nPacket = (PacketEntityPermissions) PacketType.EntityPermissions.newInstance();
		
		nPacket.flags1 = PacketEntityPermissions.FLAG1_AUTO_JUMP | PacketEntityPermissions.FLAG1_WORLD_BUILDER;
		nPacket.flags2 = PacketEntityPermissions.FLAG2_BUILD_AND_MINE | PacketEntityPermissions.FLAG2_DOORS_AND_SWITCHES |
				PacketEntityPermissions.FLAG2_OPEN_CONTAINERS | PacketEntityPermissions.FLAG2_ATTACK_PLAYERS | PacketEntityPermissions.FLAG2_ATTACK_MOBS;
		nPacket.cmdPermLevel = CommandPermissionLevel.NORMAL;
		nPacket.permLevel = PermissionLevel.CUSTOM;
		nPacket.entityUID = entityUID;
		
		return nPacket;
	}
	
	
	
	public static enum CommandPermissionLevel {
		NORMAL(0),
		OPERATOR(1),
		HOST(2),
		AUTOMATION(3),
		ADMIN(4);
		
		public final int id;
		
		private CommandPermissionLevel(int id){
			this.id = id;
		}
	}
	
	public static enum PermissionLevel {
		VISITOR(0),
		MEMBER(1),
		OPERATOR(2),
		CUSTOM(3);
		
		public final int id;
		
		private PermissionLevel(int id){
			this.id = id;
		}
	}
}
