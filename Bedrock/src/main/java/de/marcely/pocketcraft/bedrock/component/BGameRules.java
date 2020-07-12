package de.marcely.pocketcraft.bedrock.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import lombok.Getter;

public class BGameRules {
	
	@Getter private Map<BGameRule, Object> values = new HashMap<>();
	
	public BGameRules(){ }
	
	public @Nullable Object getValue(BGameRule rule){
		return this.values.get(rule);
	}
	
	public void setValue(BGameRule rule, Object value){
		this.values.put(rule, value);
	}
	
	public void removeValue(BGameRule rule){
		this.values.remove(rule);
	}
	
	public void write(EByteArrayWriter stream) throws Exception {
		stream.writeUnsignedVarInt(this.values.size());
		
		for(Entry<BGameRule, Object> entry:this.values.entrySet()){
			final BGameRule rule = entry.getKey();
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
	
	public static BGameRules newDefaultInstance(){
        final BGameRules rules = new BGameRules();
        
        rules.setValue(BGameRule.COMMAND_BLOCK_OUTPUT,  true);
        rules.setValue(BGameRule.DO_DAYLIGHT_CYCLE,  true);
        rules.setValue(BGameRule.DO_ENTITY_DROPS,  true);
        rules.setValue(BGameRule.DO_FIRE_TICK, true);
        rules.setValue(BGameRule.DO_IMMEDIATE_RESPAWN, false);
        rules.setValue(BGameRule.DO_MOB_LOOT,  true);
        rules.setValue(BGameRule.DO_MOB_SPAWNING,  true);
        rules.setValue(BGameRule.DO_TILE_DROPS,  true);
        rules.setValue(BGameRule.DO_WEATHER_CYCLE,  true);
        rules.setValue(BGameRule.DROWNING_DAMAGE,  true);
        rules.setValue(BGameRule.FALL_DAMAGE,  true);
        rules.setValue(BGameRule.FIRE_DAMAGE,  true);
        rules.setValue(BGameRule.KEEP_INVENTORY,  false);
        rules.setValue(BGameRule.MOB_GRIEFING,  true);
        rules.setValue(BGameRule.NATURAL_REGENERATION,  true);
        rules.setValue(BGameRule.PVP,  true);
        rules.setValue(BGameRule.RANDOM_TICK_SPEED, 3);
        rules.setValue(BGameRule.SEND_COMMAND_FEEDBACK,  true);
        rules.setValue(BGameRule.SHOW_COORDINATES,  true);
        rules.setValue(BGameRule.TNT_EXPLODES,  true);
        rules.setValue(BGameRule.SHOW_DEATH_MESSAGE,  true);

        return rules;
	}
}
