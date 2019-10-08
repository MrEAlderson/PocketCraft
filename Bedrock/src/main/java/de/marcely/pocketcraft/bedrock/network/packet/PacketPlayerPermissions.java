package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.permission.CommandPermissionLevel;
import de.marcely.pocketcraft.bedrock.component.permission.PermissionLevel;
import de.marcely.pocketcraft.bedrock.component.permission.PlayerPermissions;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketPlayerPermissions extends PCPacket {
	
	public long entityUID;
	public long customFlags;
	public PlayerPermissions permissions;
	
	public PacketPlayerPermissions(){
		super(PacketType.PlayerPermissions);
	}
	
	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarInt(this.permissions.getFlags1());
		writer.writeUnsignedVarInt(this.permissions.getCommandLevel().getId());
		writer.writeUnsignedVarInt(this.permissions.getFlags2());
		writer.writeUnsignedVarInt(this.permissions.getLevel().getId());
		writer.writeUnsignedVarInt(this.customFlags);
		writer.writeLLong(this.entityUID);
	}
	
	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.permissions = new PlayerPermissions();
		
		this.permissions.setFlags1((short) reader.readUnsignedVarInt());
		this.permissions.setCommandLevel(CommandPermissionLevel.getById((int) reader.readUnsignedVarInt()));
		this.permissions.setFlags2((short) reader.readUnsignedVarInt());
		this.permissions.setLevel(PermissionLevel.getById((int) reader.readUnsignedVarInt()));
		this.customFlags = reader.readUnsignedVarInt();
		this.entityUID = reader.readLLong();
	}
}
