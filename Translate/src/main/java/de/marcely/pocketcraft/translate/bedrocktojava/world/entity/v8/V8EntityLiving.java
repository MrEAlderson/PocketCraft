package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;

public abstract class V8EntityLiving extends V8Entity {

	public V8EntityLiving(int id){
		super(id);
	}
	
	@Override
	public void write(V8EntityMetadata meta){
		super.write(meta);
		
		meta.writeString(2, this.metadata.getString(EntityDataType.NAMETAG));
		meta.writeBoolean(3, this.getDataFlag(EntityDataType.FLAG_ALWAYS_SHOW_NAMETAG));
		meta.writeFloat(6, this.metadata.getInt(EntityDataType.HEALTH));
		meta.writeInt(7, this.metadata.getInt(EntityDataType.POTION_COLOR));
		meta.writeBoolean(8, this.metadata.getBoolean(EntityDataType.POTION_AMBIENT));
		meta.writeByte(9, 0); // number of arrows in entity
		meta.writeBoolean(15, this.getDataFlag(EntityDataType.FLAG_NO_AI));
	}
	
	@Override
	public void read(V8EntityMetadata meta){
		super.read(meta);
		
		this.metadata.setString(EntityDataType.NAMETAG, meta.readString(2));
		this.setDataFlag(EntityDataType.FLAG_ALWAYS_SHOW_NAMETAG, meta.readBoolean(3));
		this.metadata.setInt(EntityDataType.HEALTH, (int) meta.readFloat(6));
		this.metadata.setInt(EntityDataType.POTION_COLOR, meta.readInt(7));
		this.metadata.setBoolean(EntityDataType.POTION_AMBIENT, meta.readBoolean(8));
		// 9 = number of arrows in entity
		this.setDataFlag(EntityDataType.FLAG_NO_AI, meta.readBoolean(15));
	}
}
