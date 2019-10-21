package de.marcely.pocketcraft.translate.bedrocktojava.world;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.java.component.v8.V8GameMode;
import lombok.Getter;
import lombok.Setter;

public class PlayerList {
	
	@Getter private final Map<UUID, PlayerList.Entry> map = new HashMap<>();
	
	public Collection<PlayerList.Entry> getAll(){
		return this.map.values();
	}
	
	public @Nullable PlayerList.Entry get(UUID id){
		return this.map.get(id);
	}
	
	public void add(PlayerList.Entry entry){
		this.map.put(entry.id, entry);
	}
	
	public @Nullable PlayerList.Entry remove(UUID id){
		return this.map.remove(id);
	}
	
	
	
	public static class Entry {
		
		@Getter private final UUID id;
		@Getter @Setter private String name;
		@Getter @Setter private V8GameMode gameMode;
		@Getter @Setter private int ping;
		
		public Entry(UUID id, String name, V8GameMode gameMode, int ping){
			this.id = id;
			this.name = name;
			this.gameMode = gameMode;
			this.ping = ping;
		}
	}
}