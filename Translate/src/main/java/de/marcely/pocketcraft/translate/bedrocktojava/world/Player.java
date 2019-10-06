package de.marcely.pocketcraft.translate.bedrocktojava.world;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import de.marcely.pocketcraft.bedrock.network.packet.PCPacket;
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
	
	@Getter @Setter private byte viewDistance;
	
	public Queue<PCPacket> queuedChunks = new ConcurrentLinkedQueue<>();
	
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
	
	int i = 0;
	
	public void tick(){
		if((i++)%40 == 0){
			if(this.queuedChunks.size() >= 10){
				{
					final PacketNetworkChunkPublisherUpdate out = new PacketNetworkChunkPublisherUpdate();
					
					out.x = (int) this.x;
					out.y = (int) this.y;
					out.z = (int) this.z;
					out.radius = 32 << 4;
					
				    sendPacket(out);
				}
				
				{
					PCPacket packet = null;
					
					while((packet = this.queuedChunks.poll()) != null)
						sendPacket(packet);
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
			
			
			}else{
				final V8D9PacketPlayClientStanding out = new V8D9PacketPlayClientStanding();
				
				out.isOnGround = this.isOnGround;
				
				sendPacket(out);
			}
		}
	}
}