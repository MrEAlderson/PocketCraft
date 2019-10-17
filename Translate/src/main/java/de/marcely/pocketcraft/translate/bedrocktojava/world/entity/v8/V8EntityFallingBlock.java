package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.BlockMapping;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;
import de.marcely.pocketcraft.translate.world.V8BlockTranslator;
import de.marcely.pocketcraft.utils.Pair;

public class V8EntityFallingBlock extends V8Entity implements V8EntityObject {

	public V8EntityFallingBlock(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 70;
	}

	@Override
	public EntityType getType(){
		return EntityType.FALLING_BLOCK;
	}
	
	@Override
	public void readData(int data){
		final Pair<Short, Byte> material = V8BlockTranslator.toBedrock((short) (data & 0x0FFF), (byte) ((data & 0xF000) >> 12));
		
		this.getMetadata().setInt(EntityDataType.VARIANT, BlockMapping.INSTANCE.getRuntimeId(material.getEntry1(), material.getEntry2()));
	}
}
