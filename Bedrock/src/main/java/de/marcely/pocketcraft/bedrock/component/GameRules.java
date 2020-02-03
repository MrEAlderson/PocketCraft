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
	
	public void setValue(GameRule rule, Object value){
		this.values.put(rule, value);
	}
	
	public void removeValue(GameRule rule){
		this.values.remove(rule);
	}
	
	public void write(EByteArrayWriter stream) throws Exception {
		stream.writeUnsignedVarInt(this.values.size());
		
		for(Entry<GameRule, Object> entry:this.values.entrySet()){
			final GameRule rule = entry.getKey();
			final Object val = entry.getValue();
			
			stream.writeString(rule.getId().toLowerCase());
			stream.writeUnsignedVarInt(rule.getFieldType().getId());
			
			switch(rule.getFieldType()){
			case UNKOWN:
				// TODO
				break;
			
			case BOOL:
				stream.writeBoolean((boolean) val);
				break;
				
			case INT:
				stream.writeUnsignedVarInt((int) val);
				break;
				
			case FLOAT:
				stream.writeLFloat((float) val);
				break;
			}
		}
	}
	
	public static GameRules newDefaultInstance(){
        final GameRules rules = new GameRules();
        
        rules.setValue(GameRule.COMMAND_BLOCK_OUTPUT,  true);
        rules.setValue(GameRule.DO_DAYLIGHT_CYCLE,  true);
        rules.setValue(GameRule.DO_ENTITY_DROPS,  true);
        rules.setValue(GameRule.DO_FIRE_TICK, true);
        rules.setValue(GameRule.DO_IMMEDIATE_RESPAWN, false);
        rules.setValue(GameRule.DO_MOB_LOOT,  true);
        rules.setValue(GameRule.DO_MOB_SPAWNING,  true);
        rules.setValue(GameRule.DO_TILE_DROPS,  true);
        rules.setValue(GameRule.DO_WEATHER_CYCLE,  true);
        rules.setValue(GameRule.DROWNING_DAMAGE,  true);
        rules.setValue(GameRule.FALL_DAMAGE,  true);
        rules.setValue(GameRule.FIRE_DAMAGE,  true);
        rules.setValue(GameRule.KEEP_INVENTORY,  false);
        rules.setValue(GameRule.MOB_GRIEFING,  true);
        rules.setValue(GameRule.NATURAL_REGENERATION,  true);
        rules.setValue(GameRule.PVP,  true);
        rules.setValue(GameRule.RANDOM_TICK_SPEED, 3);
        rules.setValue(GameRule.SEND_COMMAND_FEEDBACK,  true);
        rules.setValue(GameRule.SHOW_COORDINATES,  true);
        rules.setValue(GameRule.TNT_EXPLODES,  true);
        rules.setValue(GameRule.SHOW_DEATH_MESSAGE,  true);

        return rules;
	}
}
