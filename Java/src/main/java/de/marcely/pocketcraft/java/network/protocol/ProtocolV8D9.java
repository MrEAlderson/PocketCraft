package de.marcely.pocketcraft.java.network.protocol;

import de.marcely.pocketcraft.java.network.packet.handshake.v1.*;
import de.marcely.pocketcraft.java.network.packet.login.v1.*;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.*;
import de.marcely.pocketcraft.java.network.packet.status.v1.*;
import de.marcely.pocketcraft.java.network.sequence.ClientSequenceHolder;
import de.marcely.pocketcraft.java.network.sequence.Sequence;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;
import de.marcely.pocketcraft.java.network.sequence.ServerSequenceHolder;

public class ProtocolV8D9 extends Protocol {
	
	@Override
	public int getProtocolVersion(){
		return 47;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void defineIds(){
		setPacketIds(SequenceType.HANDSHAKE, CLIENT, new Class[]{
				V1PacketHandshake.class
		});
		
		setPacketIds(SequenceType.STATUS, CLIENT, new Class[]{
				V1PacketStatusRequest.class,
				V1PacketStatusPing.class
		});
		
		setPacketIds(SequenceType.STATUS, SERVER, new Class[]{
				V1PacketStatusResponse.class,
				V1PacketStatusPong.class
		});
		
		setPacketIds(SequenceType.LOGIN, CLIENT, new Class[]{
				V1PacketLoginStart.class,
				V1PacketLoginEncryptionResponse.class
		});
		
		setPacketIds(SequenceType.LOGIN, SERVER, new Class[]{
				V1PacketLoginDisconnect.class,
				V1PacketLoginEncryptionRequest.class,
				V1PacketLoginSuccess.class,
				V1PacketLoginSetCompression.class
		});
		
		setPacketIds(SequenceType.PLAY, CLIENT, new Class[]{
				V8D9PacketPlayKeepAlive.class,
				V8D9PacketPlayClientChatMessage.class,
				null, // use entity
				null, // flying
				null, // flying.position
				null, // flying.look
				null, // flying.positionlook
				null, // block dig
				null, // block place
				null, // held item slot
				null, // arm animation
				null, // entity action
				null, // steer vehicle
				null, // close window
				null, // window click
				null, // transaction
				null, // set creative slot
				null, // enchant item
				null, // update sign
				null, // abilities
				null, // tab complete
				null, // settings
				null, // client command
				V8D9PacketPlayCustomPayload.class,
				null, // spectate
				null  // resource pack status
		});
		
		setPacketIds(SequenceType.PLAY, SERVER, new Class[]{
				V8D9PacketPlayKeepAlive.class,
				V8D9PacketPlayLogin.class,
				V8D9PacketPlayServerChatMessage.class,
				null, // update time
				null, // entity equipment
				V8D9PacketPlaySpawnPosition.class,
				null, // update health
				null, // respawn
				null, // position
				V8D9PacketPlaySetHeldItemSlot.class,
				null, // bed
				null, // animation
				null, // named entity spawn
				null, // collect
				null, // spawn entity
				null, // spawn entity living
				null, // spawn painting
				null, // spawn exp orb
				null, // entity velocity
				null, // entity destroy
				null, // entity
				null, // entity.relmove
				null, // entity.look
				null, // entity.relmovelook
				null, // entity teleport
				null, // entity head rotation
				null, // entity status
				null, // attach entity
				null, // entity metadata
				null, // entity effect
				null, // remove entity effect
				null, // experience
				null, // update attributes
				null, // map chunk
				null, // multi block change
				null, // block change
				null, // block action
				null, // block break animation
				null, // map chunk bulk
				null, // explosion
				null, // world event
				null, // named sound effect
				null, // world particles
				null, // game state change
				null, // spawn entity weather
				null, // open window
				null, // close window
				null, // set slot
				null, // window items
				null, // window data
				null, // transaction
				null, // update sign
				null, // map
				null, // tile entity data
				null, // open sign editor
				V8D9PacketPlayStatistics.class,
				null, // player info
				V8D9PacketPlayAbilities.class,
				null, // tab complete
				null, // scoreboard objective
				null, // scoreboard score
				null, // scoreboard display objective
				null, // scoreboard team
				V8D9PacketPlayCustomPayload.class,
				null, // kick
				V8D9PacketPlayGameDifficulty.class,
				null, // combat event
				null, // camera
				null, // world border
				null, // title
				V8D9PacketPlaySetCompression.class,
				null, // player list header footer
				null, // resource pack send
				null  // update entity nbt
		});
	}

	@Override
	public Sequence<ClientSequenceHolder> newClientSequenceInstance(SequenceType type, ClientSequenceHolder holder){
		return type.newV1ClientInstance(holder);
	}
	
	@Override
	public Sequence<ServerSequenceHolder> newServerSequenceInstance(SequenceType type, ServerSequenceHolder holder){
		return type.newV1ServerInstance(holder);
	}
}