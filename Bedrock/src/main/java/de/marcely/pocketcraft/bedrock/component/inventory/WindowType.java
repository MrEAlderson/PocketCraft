package de.marcely.pocketcraft.bedrock.component.inventory;

public enum WindowType {
	
	CONTAINER((byte) 0x00),
	CHEST((byte) 0x00),
	CRAFTING_TABLE((byte) 0x01),
	FURNACE((byte) 0x02),
	DISPENSER((byte) 0x06),
	ENCHANTING_TABLE((byte) 0x03),
	BREWING_STAND((byte) 0x04),
	VILLAGER((byte) 0x0F),
	BEACON((byte) 0x0D),
	ANVIL((byte) 0x05),
	HOPPER((byte) 0x08),
	DROPPER((byte) 0x07),
	SHULKER_BOX((byte) 0x00),
	ENTITY_HORSE((byte) 0x0C),
	
	STRUCTURE((byte) 0x0E),
	COMMAND_BLOCK((byte) 0x10);
	
	public final byte id;
	
	private WindowType(byte id){
		this.id = id;;
	}
}