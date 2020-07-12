package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.BBlockMapping;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.java.component.v8.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityEnderman extends V8EntityInsentient {
	
	private short carriedBlockId;
	private byte carriedBlockData;
	
	public V8EntityEnderman(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 58;
	}

	@Override
	public EntityType getType(){
		return EntityType.ENDERMAN;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		switch(key){
		case 16:
		{
			this.carriedBlockId = meta.readShort(key);
			
			this.metadata.setShort(EntityDataType.ENDERMAN_HELD_ITEM_RUNTIME_ID,
					(short) BBlockMapping.INSTANCE.getRuntimeId(this.carriedBlockId, this.carriedBlockData));
		}
		break;
		
		case 17:
		{
			this.carriedBlockData = meta.readByte(key);
			
			this.metadata.setShort(EntityDataType.ENDERMAN_HELD_ITEM_RUNTIME_ID,
					(short) BBlockMapping.INSTANCE.getRuntimeId(this.carriedBlockId, this.carriedBlockData));
		}
		break;
		
		case 18:
			this.setDataFlag(EntityDataType.FLAG_ANGRY, meta.readBoolean(18));
			break;
			
		default:
			super.read(meta, key);
			break;
		}
	}
}
