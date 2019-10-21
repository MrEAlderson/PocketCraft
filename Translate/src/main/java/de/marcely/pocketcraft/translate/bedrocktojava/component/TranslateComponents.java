package de.marcely.pocketcraft.translate.bedrocktojava.component;

import de.marcely.pocketcraft.translate.bedrocktojava.component.v8.*;

public class TranslateComponents {
	
	public static final byte BIOME = 0;
	public static final byte BLOCK_FACE = 1;
	public static final byte BLOCK = 2;
	public static final byte DIFFICULTY = 3;
	public static final byte DIMENSION = 4;
	public static final byte FORMATTED_TEXT = 5;
	public static final byte GAMEMODE = 6;
	public static final byte ITEM = 7;
	public static final byte INVENTORY = 8;
	public static final byte TEXT_COLOR = 9;
	
	private ComponentTranslator<?, ?>[] translators = new ComponentTranslator[10];
	
	public TranslateComponents(){
		this.translators[BIOME] = new V8BiomeTranslator();
		this.translators[BLOCK_FACE] = new V8BlockFaceTranslator();
		this.translators[BLOCK] = new V8BlockTranslator();
		this.translators[DIFFICULTY] = new V8DifficultyTranslator();
		this.translators[DIMENSION] = new V8DimensionTranslator();
		this.translators[FORMATTED_TEXT] = new V8FormattedTextTranslator();
		this.translators[GAMEMODE] = new V8GameModeTranslator();
		this.translators[ITEM] = new V8ItemTranslator();
		// this.translators[INVENTORY] = new V8InventoryTranslator();
		this.translators[TEXT_COLOR] = new V8TextColorTranslator();
	}
	@SuppressWarnings("unchecked")
	public <T>T toBedrock(Object input, byte type){
		return (T) this.translators[type].toBedrockUncast(this, input);
	}
	
	@SuppressWarnings("unchecked")
	public <T>T toJava(Object input, byte type){
		return (T) this.translators[type].toJavaUncast(this, input);
	}
}