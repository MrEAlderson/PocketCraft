package de.marcely.pocketcraft.translate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import com.whirvis.jraknet.RakNetException;

import de.marcely.pocketcraft.bedrock.network.packet.*;
import de.marcely.pocketcraft.bedrock.server.BedrockServer;
import de.marcely.pocketcraft.java.client.ClientAdapter;
import de.marcely.pocketcraft.java.client.JavaClient;
import de.marcely.pocketcraft.java.network.Connection;
import de.marcely.pocketcraft.java.network.LoginGoal;
import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.PacketProperties;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.*;
import de.marcely.pocketcraft.java.network.protocol.Protocol;
import de.marcely.pocketcraft.translate.bedrocktojava.BedrockPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.BedrockServerInterface;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.bedrock.*;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.java.*;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Entity;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.World;
import de.marcely.pocketcraft.translate.bedrocktojava.world.v8.entity.*;
import de.marcely.pocketcraft.utils.scheduler.Scheduler;
import lombok.Getter;

public class BedrockToJavaTranslator extends Translator {
	
	@Getter private final BedrockServer bedrockServer;
	@Getter private final Connection javaConnection;
	@Getter private final Protocol javaProtocol;
	
	private final Map<InetSocketAddress, Player> players = new HashMap<>();
	private Map<String, Class<? extends Entity>> registredEntities = new HashMap<>();
	
	public BedrockToJavaTranslator(BedrockServer bedrockServer, Connection javaConnection, Protocol javaProtocol){
		this.bedrockServer = bedrockServer;
		this.javaConnection = javaConnection;
		this.javaProtocol = javaProtocol;
	}
	
	@Override
	public boolean run() throws IllegalStateException, RakNetException {
		final boolean result = this.bedrockServer.run();
		
		if(!result)
			return false;
		
		this.bedrockServer.registerListener(new BedrockServerInterface(this));
		
		defineRegistry();
		
		Scheduler.runAsyncRepeated(() -> {
			for(Player player:getPlayers()){
				player.tick();
			}
		}, 0, 1000/20);
		
		return true;
	}
	
	public void openConnection(Consumer<JavaClient> callback, LoginGoal goal){
		openConnection(callback, goal, "Steve");
	}
	
	public void openConnection(Consumer<JavaClient> callback, LoginGoal goal, String username){
		final JavaClient client = new JavaClient(this.javaConnection.clone(), this.javaProtocol, goal, username);
		
		client.registerListener(new ClientAdapter(){
			public void onReady(){
				client.unregisterListener(this);
				
				callback.accept(client);
			}
		});
		
		try{
			if(!client.connect())
				throw new IOException("Failed for unkown reason");
		}catch(IOException e){
			e.printStackTrace();
			callback.accept(null);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T extends Packet>JavaPacketTranslator<T> getTranslator(T packet){
		return (JavaPacketTranslator<T>) packet.getProperties().getMetadata(getJavaTranslatorMetaName());
	}
	
	@SuppressWarnings("unchecked")
	public <T extends PCPacket>BedrockPacketTranslator<T> getTranslator(T packet){
		return (BedrockPacketTranslator<T>) packet.getProperties().getMetadata(getBedrockTranslatorMetaName());
	}
	
	public @Nullable Entity newEntityInstance(Object type, int id, World world, boolean isObject){
		final Class<? extends Entity> clazz = this.registredEntities.get((isObject ? "0" : "1") + type);
		
		if(clazz == null)
			return null;
		
		try{
			return clazz.getConstructor(World.class, int.class).newInstance(world, id);
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	private void defineRegistry(){
		registerJavaPacket(V8D9PacketPlayMapChunk.class, TV8D9PacketPlayMapChunk.class);
		registerJavaPacket(V8D9PacketPlayMapChunkBulk.class, TV8D9PacketPlayMapChunkBulk.class);
		registerJavaPacket(V8D9PacketPlayLogin.class, TV8D9PacketPlayLogin.class);
		registerJavaPacket(V8D9PacketPlayKeepAlive.class, TV8D9PacketPlayKeepAlive.class);
		registerJavaPacket(V8D9PacketPlayWorldTime.class, TV8D9PacketPlayWorldTime.class);
		registerJavaPacket(V8D9PacketPlayTeleport.class, TV8D9PacketPlayTeleport.class);
		registerJavaPacket(V8D9PacketPlayKick.class, TV8D9PacketPlayKick.class);
		registerJavaPacket(V8D9PacketPlayChangeGameState.class, TV8D9PacketPlayChangeGameState.class);
		registerJavaPacket(V8D9PacketPlayBlockChange.class, TV8D9PacketPlayBlockChange.class);
		registerJavaPacket(V8D9PacketPlayMultiBlockChange.class, TV8D9PacketPlayMultiBlockChange.class);
		registerJavaPacket(V8D9PacketPlayRespawn.class, TV8D9PacketPlayRespawn.class);
		registerJavaPacket(V8D9PacketPlayServerChatMessage.class, TV8D9PacketPlayServerChatMessage.class);
		registerJavaPacket(V8D9PacketPlayUpdateHealth.class, TV8D9PacketPlayUpdateHealth.class);
		registerJavaPacket(V8D9PacketPlayAbilities.class, TV8D9PacketPlayAbilities.class);
		registerJavaPacket(V8D9PacketPlaySpawnPosition.class, TV8D9PacketPlaySpawnPosition.class);
		registerJavaPacket(V8D9PacketPlayUpdateSignText.class, TV8D9PacketPlayUpdateSignText.class);
		registerJavaPacket(V8D9PacketPlayUpdateBlockEntity.class, TV8D9PacketPlayUpdateBlockEntity.class);
		registerJavaPacket(V8D9PacketPlayPlayerList.class, TV8D9PacketPlayPlayerList.class);
		registerJavaPacket(V8D9PacketPlaySpawnMob.class, TV8D9PacketPlaySpawnMob.class);
		registerJavaPacket(V8D9PacketPlayEntityRelMove.class, TV8D9PacketPlayEntityRelMove.class);
		registerJavaPacket(V8D9PacketPlayEntityLook.class, TV8D9PacketPlayEntityLook.class);
		registerJavaPacket(V8D9PacketPlayEntityRelMoveLook.class, TV8D9PacketPlayEntityRelMoveLook.class);
		registerJavaPacket(V8D9PacketPlayEntityTeleport.class, TV8D9PacketPlayEntityTeleport.class);
		registerJavaPacket(V8D9PacketPlayDestroyEntities.class, TV8D9PacketPlayDestroyEntities.class);
		registerJavaPacket(V8D9PacketPlayEntityVelocity.class, TV8D9PacketPlayEntityVelocity.class);
		registerJavaPacket(V8D9PacketPlayEntityHeadLook.class, TV8D9PacketPlayEntityHeadLook.class);
		registerJavaPacket(V8D9PacketPlayEntityMetadata.class, TV8D9PacketPlayEntityMetadata.class);
		registerJavaPacket(V8D9PacketPlaySpawnPlayer.class, TV8D9PacketPlaySpawnPlayer.class);
		registerJavaPacket(V8D9PacketPlaySpawnPainting.class, TV8D9PacketPlaySpawnPainting.class);
		registerJavaPacket(V8D9PacketPlayExplosion.class, TV8D9PacketPlayExplosion.class);
		registerJavaPacket(V8D9PacketPlayEntityEvent.class, TV8D9PacketPlayEntityEvent.class);
		registerJavaPacket(V8D9PacketPlaySpawnObject.class, TV8D9PacketPlaySpawnObject.class);
		registerJavaPacket(V8D9PacketPlayEntityAttach.class, TV8D9PacketPlayEntityAttach.class);
		registerJavaPacket(V8D9PacketPlayCollectItem.class, TV8D9PacketPlayCollectItem.class);
		registerJavaPacket(V8D9PacketPlayEntityAnimation.class, TV8D9PacketPlayEntityAnimation.class);
		registerJavaPacket(V8D9PacketPlaySpawnExperienceOrb.class, TV8D9PacketPlaySpawnExperienceOrb.class);
		registerJavaPacket(V8D9PacketPlayWindowItems.class, TV8D9PacketPlayWindowItems.class);
		registerJavaPacket(V8D9PacketPlayWindowSetItem.class, TV8D9PacketPlayWindowSetItem.class);
		registerJavaPacket(V8D9PacketPlaySetExperience.class, TV8D9PacketPlaySetExperience.class);
		registerJavaPacket(V8D9PacketPlayBlockBreakAnimation.class, TV8D9PacketPlayBlockBreakAnimation.class);
		registerJavaPacket(V8D9PacketPlayBlockAction.class, TV8D9PacketPlayBlockAction.class);
		registerJavaPacket(V8D9PacketPlaySound.class, TV8D9PacketPlaySound.class);
		registerJavaPacket(V8D9PacketPlayWorldEvent.class, TV8D9PacketWorldEvent.class);
		registerJavaPacket(V8D9PacketPlayPlayerEnterBed.class, TV8D9PacketPlayPlayerEnterBed.class);
		registerJavaPacket(V8D9PacketPlayParticles.class, TV8D9PacketPlayParticles.class);
		
		registerBedrockPacket(PacketPlayerMove.class, TPacketPlayerMove.class);
		registerBedrockPacket(PacketChunkRadiusChangeRequest.class, TPacketChunkRadiusChangeRequest.class);
		registerBedrockPacket(PacketRespawn.class, TPacketRespawn.class);
		registerBedrockPacket(PacketText.class, TPacketText.class);
		registerBedrockPacket(PacketPlayerAction.class, TPacketPlayerAction.class);
		registerBedrockPacket(PacketCommandRequest.class, TPacketCommandRequest.class);
		registerBedrockPacket(PacketPlayerPermissions.class, TPacketPlayerPermissions.class);
		registerBedrockPacket(PacketShowCredits.class, TPacketShowCredits.class);
		registerBedrockPacket(PacketEntityEquipment.class, TPacketEntityEquipment.class);
		registerBedrockPacket(PacketEntityAnimate.class, TPacketEntityAnimate.class);
		registerBedrockPacket(PacketInventoryAction.class, TPacketInventoryAction.class);
		registerBedrockPacket(PacketBlockEntityData.class, TPacketBlockEntityData.class);
		
		registerEntity(V8EntityPig.class);
		registerEntity(V8EntityCaveSpider.class);
		registerEntity(V8EntityChicken.class);
		registerEntity(V8EntityCow.class);
		registerEntity(V8EntityMooshroom.class);
		registerEntity(V8EntityRabbit.class);
		registerEntity(V8EntitySheep.class);
		registerEntity(V8EntitySnowman.class);
		registerEntity(V8EntitySpider.class);
		registerEntity(V8EntitySquid.class);
		registerEntity(V8EntityVillager.class);
		registerEntity(V8EntityOcelot.class);
		registerEntity(V8EntityWolf.class);
		registerEntity(V8EntityEnderDragon.class);
		registerEntity(V8EntityCreeper.class);
		registerEntity(V8EntityGhast.class);
		registerEntity(V8EntityGiant.class);
		registerEntity(V8EntityZombie.class);
		registerEntity(V8EntityWitch.class);
		registerEntity(V8EntitySilverfish.class);
		registerEntity(V8EntityEndermite.class);
		registerEntity(V8EntityBat.class);
		registerEntity(V8EntityBlaze.class);
		registerEntity(V8EntityEnderman.class);
		registerEntity(V8EntityZombiePigman.class);
		registerEntity(V8EntitySkeleton.class);
		registerEntity(V8EntityHorse.class);
		registerEntity(V8EntityWither.class);
		registerEntity(V8EntitySlime.class);
		registerEntity(V8EntityMagmaCube.class);
		registerEntity(V8EntityGuardian.class);
		registerEntity(V8EntityIronGolem.class);
		
		registerObject(V8EntityEgg.class);
		registerObject(V8EntityArrow.class);
		registerObject(V8EntityLeashKnot.class);
		registerObject(V8EntityEnderPearl.class);
		registerObject(V8EntityArmorStand.class);
		registerObject(V8EntityEnderCrystal.class);
		registerObject(V8EntityMinecart.class);
		registerObject(V8EntityFallingBlock.class);
		registerObject(V8EntityItem.class);
		registerObject(V8EntityBoat.class);
		registerObject(V8EntityExpBottle.class);
		registerObject(V8EntityPotion.class);
	}
	
	protected void registerJavaPacket(Class<? extends Packet> packet, Class<? extends JavaPacketTranslator<?>> translatorClazz){
		try{
			final PacketProperties properties = (PacketProperties) packet.getField("PROPERTIES").get(null);
			
			properties.setMetadata(getJavaTranslatorMetaName(), translatorClazz.newInstance());
		}catch(IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e){
			e.printStackTrace();
			throw new IllegalStateException("Missing or invalid PROPERTIES field for " + packet.getName());
		}catch(InstantiationException e){
			e.printStackTrace();
			throw new IllegalStateException("Failed to create instance of " + translatorClazz.getClass());
		}
	}
	
	protected void registerBedrockPacket(Class<? extends PCPacket> packet, Class<? extends BedrockPacketTranslator<?>> translatorClazz){
		try{
			packet.newInstance().getProperties().setMetadata(getBedrockTranslatorMetaName(), translatorClazz.newInstance());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected void registerEntity(Class<? extends Entity> clazz){
		registerEntity(clazz, false);
	}
	
	protected void registerObject(Class<? extends Entity> clazz){
		registerEntity(clazz, true);
	}
	
	protected void registerEntity(Class<? extends Entity> clazz, boolean isObject){
		try{
			if(this.registredEntities.containsValue(clazz))
				throw new RuntimeException(clazz.getName() + " is already registred");
			
			if(V8Entity.class.isAssignableFrom(clazz)){
				int type = ((V8Entity) clazz.getConstructor(World.class, int.class).newInstance(null, 0)).getTypeId();
				
				if(type < 0)
					return;
				
				this.registredEntities.put((isObject ? "0" : "1") + type, clazz);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private String getJavaTranslatorMetaName(){
		return this.javaProtocol.getProtocolVersion() + "_translator";
	}
	
	private String getBedrockTranslatorMetaName(){
		return "translator";
	}
	
	public Collection<Player> getPlayers(){
		return this.players.values();
	}
	
	public @Nullable Player getPlayer(InetSocketAddress address){
		return this.players.get(address);
	}
	
	public void addPlayer(Player player){
		this.players.put(player.getBedrock().getClient().getAddress(), player);
	}
	
	public boolean removePlayer(Player player){
		return this.players.remove(player.getBedrock().getClient().getAddress()) != null;
	}
}