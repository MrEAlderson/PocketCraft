package de.marcely.pocketcraft.translate.bedrocktojava.world.entity.v8;

import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.bedrock.network.packet.PacketDestroyEntity;
import de.marcely.pocketcraft.bedrock.network.packet.PacketSpawnEntityItem;
import de.marcely.pocketcraft.java.component.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.component.v8.V8ItemTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityItem extends V8EntityProjectile implements V8EntityObject {

	public V8EntityItem(World world, int id){
		super(world, id);
	}

	@Override
	public int getTypeId(){
		return 2;
	}

	@Override
	public EntityType getType(){
		return EntityType.ITEM;
	}
	
	@Override
	public boolean hasCustomSpawning(){
		return true;
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		if(key == 10){
			// remove old one
			{
				final PacketDestroyEntity out = new PacketDestroyEntity();
				
				out.entityUniqueId = this.getLongId();
				
				this.getWorld().getPlayer().sendPacket(out);
			}
			
			// send new one
			{
				final PacketSpawnEntityItem out = new PacketSpawnEntityItem();
				
				out.entityRuntimeId = out.entityUniqueId = this.getLongId();
				out.x = this.getX();
				out.y = this.getY();
				out.z = this.getZ();
				out.veloX = 0;
				out.veloY = 0;
				out.veloZ = 0;
				out.metadata = this.getMetadata();
				out.item = V8ItemTranslator.toBedrock(meta.readItem(key));
				
				this.getWorld().getPlayer().sendPacket(out);
			}
			
		}else
			super.read(meta, key);
	}
}
