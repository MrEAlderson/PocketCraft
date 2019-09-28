package de.marcely.pocketcraft.pocket.network.packet.action;

import java.util.HashMap;
import java.util.Map;

public enum InventoryActionSlotType {
	
	CRAFTING_INGREDIENT_ADD(-2),
	CRAFTING_INGREDIENT_REMOVE(-3),
	CRAFTING_RESULT(-4),
	CRAFTING_INGREDIENT_USE(-5),
	
	ANVIL_INPUT(-10),
	ANVIL_MATERIAL(-11),
	ANVIL_RESULT(-12),
	ANVIL_OUTPUT(-13),
	
	ENCHANT_INPUT(-15),
	ENCHANT_MATERIAL(-16),
	ENCHANT_OUTPUT(-17),
	
	TRADING_INPUT_1(-20),
	TRADING_INPUT_2(-21),
	TRADING_INPUTS_USE(-22),
	TRADING_OUTPUT(-23),
	
	BEACON(-24),
	
	DROP_CONTENS(-100),
	
	MAGIC_NOT_CURSOR(0),
	MAGIC_UNKOWN(1);
	
	public static final Map<Integer, InventoryActionSlotType> VALUES = new HashMap<>();
	
	public final int id;
	
	static {
		for(InventoryActionSlotType type:values())
			VALUES.put(type.id, type);
	}
	
	private InventoryActionSlotType(int id){
		this.id = id;
	}
}
