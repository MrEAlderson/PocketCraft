package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.java.component.nbt.NBTTagCompound;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayUpdateBlockEntity;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;

import static de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayUpdateBlockEntity.*;

import de.marcely.pocketcraft.bedrock.component.world.blockentity.*;

public class TV8D9PacketPlayUpdateBlockEntity extends JavaPacketTranslator<V8D9PacketPlayUpdateBlockEntity> {

	@Override
	public void handle(V8D9PacketPlayUpdateBlockEntity packet, Player player){
		final NBTTagCompound data = packet.data;
		final BlockEntity rawEntity = player.getWorld().getBlockEntity(packet.x, packet.y, packet.z);
		
		if(rawEntity == null)
			return;
		
		System.out.println("TYPE: " + rawEntity.getType());
		System.out.println(packet.data);
		
		switch(packet.action){
		case ACTION_BEACON:
		{
			final BlockEntityBeacon entity = (BlockEntityBeacon) rawEntity;
			
			entity.setLock(data.get("Lock").get(String.class));
			entity.setLevels(data.get("Levels").get(int.class));
			entity.setPrimary(data.get("Primary").get(int.class));
			entity.setSecondary(data.get("Secondary").get(int.class));
			
			player.updateBlockEntity(entity);
		}
		break;
		
		case ACTION_SKULL:
		{
			final BlockEntitySkull entity = (BlockEntitySkull) rawEntity;
			
			entity.setRotation(data.get("Rot").get(byte.class));
			entity.setSkullType(data.get("SkullType").get(byte.class));
			
			player.updateBlockEntity(entity);
		}
		break;
		
		case ACTION_FLOWER_POT:
		{
			final BlockEntityFlowerPot entity = (BlockEntityFlowerPot) rawEntity;
			
			entity.setItem((short) (int) data.get("Item").get(int.class));
			entity.setData(data.get("Data").get(int.class));
			
			player.updateBlockEntity(entity);
		}
		break;
		}
	}
}
