package de.marcely.pocketcraft.bedrock.network.packet.action;

import java.io.IOException;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import lombok.ToString;
import de.marcely.pocketcraft.bedrock.component.inventory.Item;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

@ToString
public class InventoryAction {
	
	public static final byte SOURCE_TYPE_CONTAINER = 0;
	public static final byte SOURCE_TYPE_WORLD = 2;
	public static final byte SOURCE_TYPE_CREATIVE = 3;
	public static final byte SOURCE_TYPE_CRAFTING_GRID = 100;
	// public static final int SOURCE_TYPE_TODO = 99999;
	
	
	
	public final int sourceType;
	public final int inventoryId;
	public final int sourceFlags;
	public final int slot;
	public final Item newItem, oldItem;
	
	public InventoryAction(int sourceType, int inventoryId, int sourceFlags, int slot, Item newItem, Item oldItem){
		this.sourceType = sourceType;
		this.inventoryId = inventoryId;
		this.sourceFlags = sourceFlags;
		this.slot = slot;
		this.newItem = newItem;
		this.oldItem = oldItem;
	}
	
	public void write(EByteArrayWriter writer) throws IOException { }
	
	public static InventoryAction read(EByteArrayReader reader) throws IOException {
		int sourceType = (int) reader.readUnsignedVarInt();
		int inventoryId = 0xFF;
		int sourceFlags = 0xFF;
		int slot = 0xFF;
		Item newItem = null, oldItem = null;
		
		switch(sourceType){
		case SOURCE_TYPE_CONTAINER:
			inventoryId = reader.readSignedVarInt();
			break;
			
		case SOURCE_TYPE_WORLD:
			sourceFlags = (int) reader.readUnsignedVarInt();
			break;
			
		case SOURCE_TYPE_CREATIVE:
			break;
			
		case SOURCE_TYPE_CRAFTING_GRID:
		// case SOURCE_TYPE_TODO:
			inventoryId = reader.readSignedVarInt();
			break;
			
		default:
			throw new IOException("Unkown inventory action source type: " + sourceType);
		}
		
		slot = (int) reader.readUnsignedVarInt();
		oldItem = Item.read(reader);
		newItem = Item.read(reader);
		
		return new InventoryAction(sourceType, inventoryId, sourceFlags, slot, newItem, oldItem);
	}
}
