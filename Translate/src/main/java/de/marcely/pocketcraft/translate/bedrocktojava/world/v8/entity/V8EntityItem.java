package de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity;

import de.marcely.pocketcraft.bedrock.component.inventory.item.BItem;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.bedrock.network.packet.PacketDestroyEntity;
import de.marcely.pocketcraft.bedrock.network.packet.PacketSpawnEntityItem;
import de.marcely.pocketcraft.java.component.v8.entity.meta.V8EntityMetadata;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;

public class V8EntityItem extends V8EntityProjectile implements V8EntityObject {

	private BItem item;
	
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
	public void tick(){
		
	}
	
	@Override
	public void read(V8EntityMetadata meta, int key){
		if(key == 10){
			BItem newItem = this.getWorld().getTranslateComponents().toBedrock(meta.readItem(key), TranslateComponents.ITEM);
			
			if(newItem == null)
				newItem = new BItem(248); // error item
			
			if(this.item != null && this.item.equals(newItem))
				return;
			
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
				out.veloX = this.getVeloX();
				out.veloY = this.getVeloY();
				out.veloZ = this.getVeloZ();
				out.metadata = this.getMetadata();
				out.item = newItem;
				
				this.getWorld().getPlayer().sendPacket(out);
			}
			
		}else
			super.read(meta, key);
	}
}
