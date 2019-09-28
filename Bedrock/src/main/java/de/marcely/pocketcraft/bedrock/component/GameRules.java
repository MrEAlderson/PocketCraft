package de.marcely.pocketcraft.bedrock.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import lombok.Getter;

public class GameRules {
	
	@Getter private Map<GameRule, Object> values = new HashMap<>();
	
	public GameRules(){ }
	
	public @Nullable Object getValue(GameRule rule){
		return this.values.get(rule);
	}
	
	public void setValue(GameRule rule, boolean value){
		this.values.put(rule, value);
	}
	
	public void removeValue(GameRule rule){
		this.values.remove(rule);
	}
	
	public void write(EByteArrayWriter stream) throws Exception {
		stream.writeUnsignedVarInt(this.values.size());
		
		for(Entry<GameRule, Object> entry:this.values.entrySet()){
			stream.writeString(entry.getKey().id);
			
			switch(entry.getKey().fieldType){
			case BOOL:
				stream.writeBoolean((boolean) entry.getValue());
				break;
				
			case UNSIGNED_VAR_INT:
				stream.writeUnsignedVarInt((long) entry.getValue());
				break;
				
			case LFLOAT:
				stream.writeLFloat((float) entry.getValue());
				break;
			}
		}
	}
	
	public static GameRules newDefaultInstance(){
        final GameRules rules = new GameRules();

        rules.setValue(GameRule.NATURAL_REGENERATION, false);
        
        /*rules.setValue(PGameRule.COMMAND_BLOCK_OUTPUT,  true);
        rules.setValue(PGameRule.DO_DAYLIGHT_CYCLE,  false);
        rules.setValue(PGameRule.DO_ENTITY_DROPS,  true);
        rules.setValue(PGameRule.DO_FIRE_TICK, true);
        rules.setValue(PGameRule.DO_MOB_LOOT,  true);
        rules.setValue(PGameRule.DO_MOB_SPAWNING,  true);
        rules.setValue(PGameRule.DO_TILE_DROPS,  true);
        rules.setValue(PGameRule.DO_WEATHER_CYCLE,  true);
        rules.setValue(PGameRule.DROWNING_DAMAGE,  true);
        rules.setValue(PGameRule.FALL_DAMAGE,  true);
        rules.setValue(PGameRule.FIRE_DAMAGE,  true);
        rules.setValue(PGameRule.KEEP_INVENTORY,  false);
        rules.setValue(PGameRule.MOB_GRIEFING,  true);
        rules.setValue(PGameRule.NATURAL_REGENERATION,  false);
        rules.setValue(PGameRule.PVP,  true);
        rules.setValue(PGameRule.SEND_COMMAND_FEEDBACK,  true);
        rules.setValue(PGameRule.SHOW_COORDINATES,  true);
        rules.setValue(PGameRule.TNT_EXPLODES,  true);
        rules.setValue(PGameRule.SHOW_DEATH_MESSAGE,  true);*/

        return rules;
	}
}
