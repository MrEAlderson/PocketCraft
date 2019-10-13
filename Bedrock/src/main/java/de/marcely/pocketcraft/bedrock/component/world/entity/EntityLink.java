package de.marcely.pocketcraft.bedrock.component.world.entity;

import java.io.IOException;

import de.marcely.pocketcraft.utils.io.ByteArrayWriter;

public class EntityLink {
	
	public final EntityLinkType type;
	public final long fromEntityUniqueID, toEntityUniqueID;
	public final boolean immediate;
	
	public EntityLink(EntityLinkType type, long fromEntityUniqueID, long toEntityUniqueID, boolean immediate){
		this.type = type;
		this.fromEntityUniqueID = fromEntityUniqueID;
		this.toEntityUniqueID = toEntityUniqueID;
		this.immediate = immediate;
	}
	
	public void write(ByteArrayWriter writer) throws IOException {
		writer.writeSignedVarLong(this.fromEntityUniqueID);
		writer.writeSignedVarLong(this.toEntityUniqueID);
		writer.writeSignedByte((byte) type.ordinal());
		writer.writeBoolean(this.immediate);
	}
	
	
	
	public static enum EntityLinkType {
		
		REMOVE,
		RIDER,
		PASSANGER;
	}
}