package de.marcely.pocketcraft.translate.bedrocktojava.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.marcely.pocketcraft.bedrock.component.Dimension;
import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import de.marcely.pocketcraft.bedrock.component.permission.PlayerPermissions;
import de.marcely.pocketcraft.bedrock.component.world.blockentity.BlockEntity;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityAttribute;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityAttributeType;
import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.bedrock.network.packet.PacketBlockEntityData;
import de.marcely.pocketcraft.bedrock.network.packet.PacketChangeDimension;
import de.marcely.pocketcraft.bedrock.network.packet.PacketEntityAttributes;
import de.marcely.pocketcraft.bedrock.network.packet.PacketLoginStatus;
import de.marcely.pocketcraft.bedrock.network.packet.PacketPlayerPermissions;
import de.marcely.pocketcraft.bedrock.network.packet.PacketRespawn;
import de.marcely.pocketcraft.bedrock.network.packet.PacketShowCredits;
import de.marcely.pocketcraft.bedrock.network.packet.PacketText;
import de.marcely.pocketcraft.bedrock.network.packet.PacketNetworkChunkPublisherUpdate;
import de.marcely.pocketcraft.bedrock.network.packet.PacketPlayerMove;
import de.marcely.pocketcraft.bedrock.network.packet.PacketPlayerMove.PlayerMoveType;
import de.marcely.pocketcraft.bedrock.server.player.BedrockClient;
import de.marcely.pocketcraft.java.client.JavaClient;
import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientLook;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientPosition;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientPositionLook;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientStanding;
import de.marcely.pocketcraft.translate.BedrockToJavaTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.component.TranslateComponents;
import de.marcely.pocketcraft.translate.bedrocktojava.world.block.BlockCollision;
import de.marcely.pocketcraft.translate.bedrocktojava.world.block.BlockCollisionEvent;
import de.marcely.pocketcraft.utils.math.MathUtil;
import lombok.Getter;
import lombok.Setter;

public class Player {
	
	public static final byte SPAWN_STATE_SENDING_CHUNKS = 0;
	public static final byte SPAWN_STATE_WAITING_ACK = 1;
	public static final byte SPAWN_STATE_DONE = 2;
	
	@Getter private final BedrockToJavaTranslator translator;
	@Getter private final BedrockClient bedrock;
	@Getter private final JavaClient java;
	@Getter private final TranslateComponents translateComponents = new TranslateComponents();
	
	@Getter private final World world = new World(this);
	@Getter private final PlayerPermissions permissions = PlayerPermissions.newDefaultInstance();
	
	private float oldX, oldY, oldZ, oldYaw, oldPitch;
	@Getter @Setter private float x, y, z, yaw, pitch;
	@Getter @Setter private boolean isOnGround;
	
	@Getter @Setter private byte viewDistance = 8;
	@Getter @Setter private float walkSpeed, flySpeed;
	@Getter @Setter private boolean isSprinting;
	
	@Getter private boolean isDead = false;
	@Getter @Setter private byte spawnState = SPAWN_STATE_SENDING_CHUNKS;
	@Getter private Long loginTime = null;
	@Getter @Setter private Dimension currentDimension;
	private boolean queuedShowCreditsTask = false;
	private Queue<Long> sendingChunks = new ConcurrentLinkedQueue<>(); // chunks that we are about to send
	private Queue<Long> distantSendingChunks = new ConcurrentLinkedQueue<>(); // chunks that we want to send, but the player is too far
	private List<Long> sentChunks = Collections.synchronizedList(new ArrayList<>()); // chunks that have been sent
	private List<PCPacket> batchPackets = Collections.synchronizedList(new ArrayList<>()); // collecting packets and then flushing them at the end of a tick
	
	public Integer chunkX = null, chunkZ = null;
	private int currentTick = 0;
	
	public Player(BedrockToJavaTranslator translator, BedrockClient bedrock, JavaClient java){
		this.translator = translator;
		this.bedrock = bedrock;
		this.java = java;
	}
	
	public Entity getEntity(){
		return (Entity) this.getBedrock().getEntity();
	}
	
	public int getEntityId(){
		return (int) this.bedrock.getEntity().getLongId();
	}
	
	public UUID getUUID(){
		return this.java.getId();
	}
	
	public void tick(){
		this.currentTick++;
		
		///////////////////////////////////
		// HANDLES CHUNKS STUFF & PLAYER SPAWNING
		///////////////////////////////////
		{
			final int newChunkX = ((int) this.x) >> 4;
			final int newChunkZ = ((int) this.z) >> 4;
			
			if(true){
				int sentChunks = 0;
				
				{
					Long index = null;
					
					if(this.chunkX == null || (this.chunkX != newChunkX || this.chunkZ != newChunkZ)){
						// look for new interesting chunks
						{
    						final Iterator<Long> it = this.distantSendingChunks.iterator();
    						
        					while(it.hasNext()){
        						index = it.next();
        						final int x = (int) (long) index;
        						final int z = (int) (index >> 32L);
        						
        						if(isChunkInViewDistance(x, z, newChunkX, newChunkZ)){
        							this.sendingChunks.add(index);
        							it.remove();
        						}
        					}
						}
						
						// check if chunks got unloaded
						{
							final Iterator<Long> it = this.sentChunks.iterator();
							
							while(it.hasNext()){
								index = it.next();
								final Chunk chunk = this.world.getChunksMap().get(index);
								
								if(chunk == null)
									continue;
								
        						final int x = (int) (long) index;
        						final int z = (int) (index >> 32L);
        						
        						if(!isChunkInViewDistance(x, z, newChunkX, newChunkZ)){
        							this.distantSendingChunks.add(index);
        							chunk.setSent(false);
        							it.remove();
        						}
							}
						}
    					
    					this.chunkX = newChunkX;
    					this.chunkZ = newChunkZ;
					}
					
					
					// send chunks
					while((index = this.sendingChunks.poll()) != null){
						final Chunk chunk = this.world.getChunksMap().get(index);
						
						if(chunk == null)
							continue;
						
						final int x = (int) (long) index;
						final int z = (int) (index >> 32L);
						final boolean inDistance = isChunkInViewDistance(x, z, newChunkX, newChunkZ);
						
						if(inDistance){
							this.sentChunks.add(index);
							sendPacket(chunk.buildPacket(this.world, x, z));
							chunk.setSent(true);
							
							if(sentChunks++ == 3)
								break;
							
							if(this.spawnState == SPAWN_STATE_SENDING_CHUNKS && newChunkX == x && newChunkZ == z){
								System.out.println("========== SPAWNED");
								
								final PacketLoginStatus out = new PacketLoginStatus();
								
								out.result = PacketLoginStatus.PLAYER_SPAWN;
								
								sendPacket(out);
								
								this.spawnState = SPAWN_STATE_WAITING_ACK;
							}
							
						}else{
							this.distantSendingChunks.add(index);
						}
					}
				}
				
				if(sentChunks >= 1){
					// tell the client to add the new chunks to the scene
					{
						final PacketNetworkChunkPublisherUpdate out = new PacketNetworkChunkPublisherUpdate();
						
						out.x = (int) this.x;
						out.y = (int) this.y;
						out.z = (int) this.z;
						out.radius = this.viewDistance << 4;
						
					   sendPacket(out);
					}
				}
			}
			
			if(this.spawnState == SPAWN_STATE_DONE){
				// changing dimension screen
				if(getCurrentDimension() != getWorld().getDimension()){
					System.out.println("CHANGE DIMENSION");
					
					{
						final PacketChangeDimension out = new PacketChangeDimension();
						
						out.dimension = getWorld().getDimension();
						out.posX = getX();
						out.posY = getY();
						out.posZ = getZ();
						
						sendPacket(out);
					}
					
					this.spawnState = SPAWN_STATE_SENDING_CHUNKS;
					setCurrentDimension(getWorld().getDimension());
					this.queuedShowCreditsTask = false;
					
					for(Chunk chunk:getWorld().getChunks())
						chunk.setSent(false);
				
				// show credits
				}else if(this.queuedShowCreditsTask && (System.currentTimeMillis() - this.loginTime) >= 5000 /* wait a bit or client crashes */){
					{
						final PacketShowCredits out = new PacketShowCredits();
						
						out.entityId = getEntityId();
						out.status = PacketShowCredits.STATUS_START_CREDITS;
						
						sendPacket(out);
					}
					
					this.queuedShowCreditsTask = false;
				}
			}
		}
		
        ///////////////////////////////////
        // GAME TICK
        ///////////////////////////////////
		if(!isDead && isLoggedIn()){
			// drag him down if he's on void. bedrock players walk on it for whatever reason
			if(this.y <= -39){
				this.y -= 0.4F;
				
				// and tell it the client
				{
					final PacketPlayerMove out = new PacketPlayerMove();
					
					out.entityRuntimeID = getEntityId();
					out.posX = this.x;
					out.posY = this.y+1.62F /* eye height */;
					out.posZ = this.z;
					out.yaw = out.headYaw = this.yaw;
					out.pitch = this.pitch;
					out.mode = PlayerMoveType.NORMAL;
					out.onGround = false;
					
					sendPacket(out);
				}
			}
			
			// send move packets
			{
				// player may glide through a block
				// java server will teleport him back because of this. this causes that it's not possible to e.g. walk on stairs
				{
					this.getEntity().onNetworkPositionChange(this.x, this.y, this.z, false);
					
					final List<BlockCollisionEvent> events = this.getEntity().getCollidingBlocks();
					
					if(events != null){
						final BlockCollisionEvent event = Collections.max(events, (e1, e2) -> Integer.compare(e1.getY(), e2.getY()));
						final BlockCollision.Cube cube = Collections.max(event.getIntersecting(), (c1, c2) -> Float.compare(c1.getY() + c1.getHeight(), c2.getY() + c2.getHeight()));
						final float newY = event.getY() + cube.getY() + cube.getHeight();
						
						if(newY - this.y <= 0.7F && newY - this.y >= 0)
							this.getEntity().setY(this.y = newY);
					}
						
				}
				
				boolean isMoving = this.x != this.oldX || this.y != this.oldY || this.z != this.oldZ;
				boolean isLooking = this.yaw != this.oldYaw || this.pitch != this.oldPitch;
				
				if(isMoving && isLooking){
					final V8D9PacketPlayClientPositionLook out = new V8D9PacketPlayClientPositionLook();
					
					out.x = this.oldX = this.x;
					out.y = this.oldY = this.y;
					out.z = this.oldZ = this.z;
					out.yaw = this.oldYaw = this.yaw;
					out.pitch = this.oldPitch = this.pitch;
					out.isOnGround = this.isOnGround;
					
					sendPacket(out);
				
				}else if(isMoving && !isLooking){
					final V8D9PacketPlayClientPosition out = new V8D9PacketPlayClientPosition();
					
					out.x = this.oldX = this.x;
					out.y = this.oldY = this.y;
					out.z = this.oldZ = this.z;
					out.isOnGround = this.isOnGround;
					
					sendPacket(out);
				
				}else if(!isMoving && isLooking){
					final V8D9PacketPlayClientLook out = new V8D9PacketPlayClientLook();
					
					out.yaw = this.oldYaw = this.yaw;
					out.pitch = this.oldPitch = this.pitch;
					out.isOnGround = this.isOnGround;
					
					sendPacket(out);
				
				
				}else{ // we must send this every tick, otherwise the players air value isn't going correctly down and other issues may occur
					final V8D9PacketPlayClientStanding out = new V8D9PacketPlayClientStanding();
					
					out.isOnGround = this.isOnGround;
					
					sendPacket(out);
				}
			}
		}
		
		this.world.tick(this.currentTick);
		
        ///////////////////////////////////
        // FLUSH PACKETS
        ///////////////////////////////////
		if(this.batchPackets.size() >= 1){
			final List<PCPacket> send = new ArrayList<>(this.batchPackets);
			
			this.batchPackets.clear();
			
			this.bedrock.sendPackets(send);
		}
	}
	
	public void receivedJavaChunk(int x, int z, Chunk chunk){
		final long index = World.getChunkIndex(x, z);
		
		chunk.setSent(false);
		
		if(isChunkInViewDistance(x, z, (int) this.x >> 4, (int) this.z >> 4))
			this.sendingChunks.add(index);
		else
			this.distantSendingChunks.add(index);
	}
	
	public void unloadChunk(int x, int z){
		final long index = World.getChunkIndex(x, z);
		final Chunk chunk = this.world.unloadChunk(x, z);
		
		this.distantSendingChunks.remove(index);
		this.sendingChunks.remove(index);
		this.sentChunks.remove(index);
		
		if(chunk != null)
			chunk.setSent(false);
	}
	
	public void unloadChunks(){
		this.world.getChunksMap().clear();
		this.world.getEntitiesMap().clear();
		this.distantSendingChunks.clear();
		this.sendingChunks.clear();
		this.sentChunks.clear();
	}
	
	public void updatePermissions(){
		final PacketPlayerPermissions out = new PacketPlayerPermissions();
		
		out.entityUID = this.getEntityId();
		out.permissions = this.permissions;
		
		sendPacket(out);
	}
	
	public void updateSpeed(){
		float speed = -1;
		
		if(this.permissions.isFlying())
			speed = this.flySpeed;
		else
			speed = this.walkSpeed;
		
		{
			final PacketEntityAttributes out = new PacketEntityAttributes();
			
			out.entityRuntimeId = getEntityId();
			out.attributes = new EntityAttribute[]{
				new EntityAttribute(EntityAttributeType.MOVEMENT_SPEED, speed * (this.isSprinting ? 1.3F : 1))	
			};
			
			sendPacket(out);
		}
	}
	
	public void sendPacket(Packet packet){
		this.java.sendPacket(packet);
	}
	
	public void sendPacket(PCPacket packet){
		this.batchPackets.add(packet);
	}
	
	public void sendPackets(PCPacket... packets){
		this.batchPackets.addAll(Arrays.asList(packets));
	}
	
	public void sendPackets(List<PCPacket> packets){
		this.batchPackets.addAll(packets);
	}
	
	public void setDead(boolean dead){
		if(this.isDead == dead)
			return;
		
		this.isDead = dead;
		
		if(dead){
			final PacketRespawn out = new PacketRespawn();
			
			out.posX = this.x;
			out.posY = this.y;
			out.posZ = this.z;
			out.state = PacketRespawn.STATE_READY_TO_SPAWN;
			out.entityRuntimeId = getEntityId();
			
			sendPacket(out);
		}
	}
	
	public void showCredits(){
		this.queuedShowCreditsTask = true;
	}
	
	private boolean isChunkInViewDistance(int chunkX, int chunkZ, int viewerX, int viewerZ){
		return MathUtil.distance(chunkX, chunkZ, viewerX, viewerZ) < this.viewDistance;
	}
	
	public boolean isLoggedIn(){
		return this.loginTime != null;
	}
	
	public void logIn(Entity entity){
		if(isLoggedIn())
			return;
		
		this.loginTime = System.currentTimeMillis();
		
		// override bedrocks entity instance with our wrapped entity
		{
			this.bedrock.getEntity().applyTo(entity);
			
			this.bedrock.setEntity(entity);
		}
	}
	
	public void updateBlockEntity(BlockEntity entity){
		final PacketBlockEntityData out = new PacketBlockEntityData();
		
		out.x = entity.getX();
		out.y = entity.getY();
		out.z = entity.getZ();
		
		{
			final NBTCompound nbt = new NBTCompound();
			
			entity.write(nbt);
			
			out.data = nbt;
		}
		
		sendPacket(out);
	}
	
	public void sendChatMessage(String message){
		final PacketText out = new PacketText();
		
		out.type = PacketText.TYPE_CHAT;
		out.message = message;
		
		sendPacket(out);
	}
}