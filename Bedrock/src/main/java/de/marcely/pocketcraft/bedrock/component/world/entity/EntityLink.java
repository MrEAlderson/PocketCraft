package de.marcely.pocketcraft.bedrock.component.world.entity;

import java.io.IOException;

import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

public class EntityLink {
	
	public final EntityLinkType type;
	public final long vehicleUniqueId, riderUniqueId;
	public final boolean immediate;
	
	public EntityLink(EntityLinkType type, long fromEntityUniqueId, long toEntityUniqueId, boolean immediate){
		this.type = type;
		this.vehicleUniqueId = fromEntityUniqueId;
		this.riderUniqueId = toEntityUniqueId;
		this.immediate = immediate;
	}
	
	public void write(ByteArrayWriter writer) throws IOException {
		writer.writeSignedVarLong(this.vehicleUniqueId);
		writer.writeSignedVarLong(this.riderUniqueId);
		writer.writeSignedByte((byte) type.ordinal());
		writer.writeBoolean(this.immediate);
	}
	
	
	
	public static enum EntityLinkType {
		
		REMOVE,
		RIDER,
		PASSANGER;
	}
}