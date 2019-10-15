package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityDataType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityAnimate;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityEvent;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;
import de.marcely.pocketcraft.utils.scheduler.Scheduler;

public class V8EntityGhast extends V8EntityInsentient {

	byte i = 0;
	
	public V8EntityGhast(World world, int id){
		super(world, id);
		
		if(world == null)
			return;
		
		Scheduler.runAsyncRepeated(() -> {
			if(i >= 254)
				return;
			
			if(i == 3)
				i++;
			
			System.out.println(i);
			
			final PacketEntityEvent out = new PacketEntityEvent();
			
			out.entityId = id;
			out.type = i;
			
			world.getPlayer().sendPacket(out);
			
			i++;
		}, 2000, 1500);
	}

	@Override
	public int getTypeId(){
		return 56;
	}

	@Override
	public EntityType getType(){
		return EntityType.GHAST;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		if(key == 16)
			this.setDataFlag(EntityDataType.FLAG_ROARING, meta.readBoolean(key));
		else
			super.read(meta, key);
	}
}
