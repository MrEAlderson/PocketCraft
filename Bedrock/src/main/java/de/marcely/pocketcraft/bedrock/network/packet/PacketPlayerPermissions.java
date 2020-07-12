package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.permission.BCommandPermissionLevel;
import de.marcely.pocketcraft.bedrock.component.permission.BPermissionLevel;
import de.marcely.pocketcraft.bedrock.component.permission.BPlayerPermissions;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketPlayerPermissions extends PCPacket {
	
	public long entityUID;
	public long customFlags;
	public BPlayerPermissions permissions;
	
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
		this.permissions = new BPlayerPermissions();
		
		this.permissions.setFlags1((short) reader.readUnsignedVarInt());
		this.permissions.setCommandLevel(BCommandPermissionLevel.getById((int) reader.readUnsignedVarInt()));
		this.permissions.setFlags2((short) reader.readUnsignedVarInt());
		this.permissions.setLevel(BPermissionLevel.getById((int) reader.readUnsignedVarInt()));
		this.customFlags = reader.readUnsignedVarInt();
		this.entityUID = reader.readLLong();
	}
}
