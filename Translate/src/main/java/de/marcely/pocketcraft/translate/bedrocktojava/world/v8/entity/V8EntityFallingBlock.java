package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.BBlockMapping;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;
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
		final Pair<Short, Byte> material = this.getTranslateComponents().toBedrock(
				new Pair<Short, Byte>((short) (data & 0x0FFF), (byte) ((data & 0xF000) >> 12)),
				TranslateComponents.BLOCK);
		
		this.getMetadata().setInt(EntityDataType.VARIANT, BBlockMapping.INSTANCE.getRuntimeId(material.getEntry1(), material.getEntry2()));
	}
}
