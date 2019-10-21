package de.marcely.pocketcraft.translate.bedrocktojava.packet.java;

import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayPlayerList;
import de.marcely.pocketcraft.translate.bedrocktojava.JavaPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.PlayerList;

import static de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayPlayerList.*;

import java.util.ArrayList;
import java.util.List;

import de.marcely.pocketcraft.bedrock.network.packet.PacketPlayerList;
import de.marcely.pocketcraft.bedrock.network.packet.PacketPlayerList.PlayerListEntry;

public class TV8D9PacketPlayPlayerList extends JavaPacketTranslator<V8D9PacketPlayPlayerList> {

	@Override
	public void handle(V8D9PacketPlayPlayerList packet, Player player){
		List<PlayerListEntry> entries = new ArrayList<>();
		byte action = -1;
		
		// handle it
		switch(packet.action){
		case ACTION_ADD:
		{
			action = PacketPlayerList.TYPE_ADD;
			
			for(int i=0; i<packet.entries.length; i++){
				final de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayPlayerList.PlayerListEntry inEntry = packet.entries[i];
				
				player.getWorld().getPlayerList().add(
						new PlayerList.Entry(inEntry.id, inEntry.name, inEntry.mode, inEntry.ping));
				
				// skip duplicate of ourself
				if(inEntry.id.equals(player.getUUID()))
					continue;
				
				// send out
				{
					final PlayerListEntry outEntry = new PlayerListEntry(inEntry.id);
					
					outEntry.entityId = 0;
					outEntry.name = inEntry.name;
					outEntry.skin = player.getBedrock().getInfo().getSkin();
					
					entries.add(outEntry);
				}
			}
		}
		break;
		
		case ACTION_REMOVE:
		{
			action = PacketPlayerList.TYPE_REMOVE;
			
			for(int i=0; i<packet.entries.length; i++){
				final de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayPlayerList.PlayerListEntry entry = packet.entries[i];
				final PlayerList.Entry listEntry = player.getWorld().getPlayerList().remove(entry.id);
				
				if(listEntry == null)
					continue;
				
				// skip duplicate of ourself
				if(entry.id.equals(player.getUUID()))
					continue;
				
				entries.add(new PlayerListEntry(entry.id));
			}
		}
		break;
		
		case ACTION_UPDATE_GAMEMODE:
		{
			for(int i=0; i<packet.entries.length; i++){
				final de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayPlayerList.PlayerListEntry entry = packet.entries[i];
				final PlayerList.Entry listEntry = player.getWorld().getPlayerList().remove(entry.id);
				
				if(listEntry == null)
					continue;
				
				listEntry.setGameMode(entry.mode);
			}
		}
		break;
		
		case ACTION_UPDATE_LATENCY:
		{
			for(int i=0; i<packet.entries.length; i++){
				final de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayPlayerList.PlayerListEntry entry = packet.entries[i];
				final PlayerList.Entry listEntry = player.getWorld().getPlayerList().remove(entry.id);
				
				if(listEntry == null)
					continue;
				
				listEntry.setPing(entry.ping);
			}
		}
		break;
		
		case ACTION_UPDATE_DISPLAY_NAME:
		{
			for(int i=0; i<packet.entries.length; i++){
				final de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayPlayerList.PlayerListEntry entry = packet.entries[i];
				final PlayerList.Entry listEntry = player.getWorld().getPlayerList().remove(entry.id);
				
				if(listEntry == null)
					continue;
				
				listEntry.setName(entry.name);
			}
		}
		break;
		}
		
		// send packet
		if(action != -1 && entries.size() >= 1){
			final PacketPlayerList out = new PacketPlayerList();
			
			out.type = action;
			out.entries = entries.stream().toArray(PlayerListEntry[]::new);
			
			player.sendPacket(out);
		}
	}
}
