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
				V8D9PacketPlayClientStanding.class,
				V8D9PacketPlayClientPosition.class,
				V8D9PacketPlayClientLook.class,
				V8D9PacketPlayClientPositionLook.class,
				null, // block dig
				null, // block place
				null, // held item slot
				null, // arm animation
				V8D9PacketPlayClientEntityAction.class,
				null, // steer vehicle
				null, // close window
				null, // window click
				null, // transaction
				null, // set creative slot
				null, // enchant item
				null, // update sign
				V8D9PacketPlayAbilities.class,
				null, // tab complete
				V8D9PacketPlayClientSettings.class,
				V8D9PacketPlayClientCommand.class,
				V8D9PacketPlayCustomPayload.class,
				null, // spectate
				null  // resource pack status
		});
		
		setPacketIds(SequenceType.PLAY, SERVER, new Class[]{
				V8D9PacketPlayKeepAlive.class,
				V8D9PacketPlayLogin.class,
				V8D9PacketPlayServerChatMessage.class,
				V8D9PacketPlayWorldTime.class,
				null, // entity equipment
				V8D9PacketPlaySpawnPosition.class,
				V8D9PacketPlayUpdateHealth.class,
				V8D9PacketPlayRespawn.class,
				V8D9PacketPlayTeleport.class,
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
				V8D9PacketPlayMapChunk.class,
				V8D9PacketPlayMultiBlockChange.class,
				V8D9PacketPlayBlockChange.class,
				null, // block action
				null, // block break animation
				V8D9PacketPlayMapChunkBulk.class,
				V8D9PacketPlayExplosion.class,
				null, // world event
				null, // named sound effect
				null, // world particles
				V8D9PacketPlayChangeGameState.class,
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
				V8D9PacketPlayScoreboardObjective.class,
				V8D9PacketPlayScoreboardScore.class,
				V8D9PacketPlayScoreboardDisplay.class,
				V8D9PacketPlayScoreboardTeam.class,
				V8D9PacketPlayCustomPayload.class,
				V8D9PacketPlayKick.class,
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