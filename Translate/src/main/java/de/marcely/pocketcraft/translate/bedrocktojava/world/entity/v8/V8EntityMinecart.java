package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityMinecart extends V8Entity implements V8EntityObject {
	
	private byte type;
	
	public V8EntityMinecart(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 10;
	}

	@Override
	public EntityType getType(){
		switch(type){
		case 0:
		default:
			return EntityType.MINECART;
		}
	}
	
	@Override
	public void readData(int data){
		this.type = (byte) data;
	}
}
