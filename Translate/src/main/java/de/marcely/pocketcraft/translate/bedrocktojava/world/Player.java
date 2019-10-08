package de.marcely.pocketcraft.translate.bedrocktojava.world;

import java.util.Map.Entry;

import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
import de.marcely.pocketcraft.bedrock.network.packet.PacketLoginStatus;
import de.marcely.pocketcraft.bedrock.network.packet.PacketNetworkChunkPublisherUpdate;
import de.marcely.pocketcraft.bedrock.network.packet.PacketPlayerMove;
import de.marcely.pocketcraft.bedrock.network.packet.PacketType;
import de.marcely.pocketcraft.bedrock.network.packet.PacketPlayerMove.PlayerMoveType;
import de.marcely.pocketcraft.bedrock.server.player.BedrockClient;
import de.marcely.pocketcraft.java.client.JavaClient;
import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientLook;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientPosition;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientPositionLook;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientStanding;
import de.marcely.pocketcraft.translate.BedrockToJavaTranslator;
import lombok.Getter;
import lombok.Setter;

public class Player {
	
	@Getter private final BedrockToJavaTranslator translator;
	@Getter private final BedrockClient bedrock;
	@Getter private final JavaClient java;
	
	@Getter private final World world = new World();
	
	private float oldX, oldY, oldZ, oldYaw, oldPitch;
	@Getter @Setter private float x, y, z, yaw, pitch;
	@Getter @Setter private boolean isOnGround;
	
	@Getter @Setter private byte viewDistance = 8, serverViewDistance;
	@Getter private boolean spawning = false;
	
	private Integer chunkX = null, chunkZ = null;
	private int currentTick = 0;
	
	public Player(BedrockToJavaTranslator translator, BedrockClient bedrock, JavaClient java){
		this.translator = translator;
		this.bedrock = bedrock;
		this.java = java;
	}
	
	public void sendPacket(Packet packet){
		this.java.sendPacket(packet);
	}
	
	public void sendPacket(PCPacket packet){
		this.bedrock.sendPacket(packet);
	}
	
	public int getEntityId(){
		return (int) this.bedrock.getEntity().getId();
	}
	
	private boolean isChunkInDistance(int chunkX, int chunkZ, int viewerX, int viewerZ, int distance){
		return (chunkX >= viewerX - distance) && (chunkX <= viewerX + distance) && (chunkZ >= viewerZ - distance) && (chunkZ <= viewerZ + distance);
	}
	
	public void tick(){
		this.currentTick++;
		
		// send chunks
		{
			final int newChunkX = ((int) this.x) >> 4;
			final int newChunkZ = ((int) this.z) >> 4;
			
			if(this.currentTick % 40 == 0 && (this.chunkX == null || (this.chunkX != newChunkX || this.chunkZ != newChunkZ))){
				this.chunkX = newChunkX;
				this.chunkZ = newChunkZ;
				
				int sentChunks = 0;
				
				{
					for(Entry<Long, Chunk> e:this.world.getChunksMap().entrySet()){
						final Chunk chunk = e.getValue();
						final int x = (int) (long) e.getKey();
						final int z = (int) (e.getKey() >> 32L);
						final boolean inDistance = isChunkInDistance(x, z, newChunkX, newChunkZ, this.viewDistance-1);
						
						if(!chunk.isSent() && inDistance){	
							sendPacket(chunk.buildPacket(x, z));
							chunk.setSent(true);
							sentChunks++;
						
						}else if(chunk.isSent() && !inDistance){
							chunk.setSent(false);
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
				    
				    // spawn him to the world
				    if(this.isSpawning()){
						final PacketLoginStatus out = (PacketLoginStatus) PacketType.LoginStatus.newInstance();
						
						out.result = PacketLoginStatus.PLAYER_SPAWN;
						
						sendPacket(out);
						setSpawning(false);
				    }
				}
			}
		}
		
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
			
			
			}else if(this.currentTick % 20 == 0){ // only every second
				final V8D9PacketPlayClientStanding out = new V8D9PacketPlayClientStanding();
				
				out.isOnGround = this.isOnGround;
				
				sendPacket(out);
			}
		}
	}
	
	public void setSpawning(boolean spawning){
		this.spawning = spawning;
		
		if(spawning){
			// resend chunks
			this.chunkX = null;
			this.chunkZ = null;
		}
	}
	
	public void receivedChunk(int x, int z){
		if(isChunkInDistance(x, z, (int) this.x >> 4, (int) this.z >> 4, this.viewDistance)){
			this.chunkX = null;
			this.chunkZ = null;
		}
	}
}