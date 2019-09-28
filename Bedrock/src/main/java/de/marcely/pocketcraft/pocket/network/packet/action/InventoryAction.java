package de.marcely.pocketcraft.pocket.network.packet.action;

import java.io.IOException;

import de.marcely.pocketcraft.bedrock.inventory.Item;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class InventoryAction {
	
	public final InventoryActionSourceType sourceType;
	public final InventoryActionSlotType slotType;
	public final long slot;
	public final Item newItem, oldItem;
	public final long sourceFlags;
	
	public InventoryAction(InventoryActionSourceType sourceType, InventoryActionSlotType slotType, long slot, Item newItem, Item oldItem, long sourceFlags){
		this.sourceType = sourceType;
		this.slotType = slotType;
		this.slot = slot;
		this.newItem = newItem;
		this.oldItem = oldItem;
		this.sourceFlags = sourceFlags;
	}
	
	public void write(EByteArrayWriter writer) throws IOException {
		writer.writeUnsignedVarInt(sourceType.id);
		
		switch(sourceType){
		case CONTAINER:
			writer.writeSignedVarInt(slotType.id);
			break;
		case WORLD:
			writer.writeUnsignedVarInt(sourceFlags);
			break;
		default:
			break;
		}
		
		writer.writeUnsignedVarInt(slot);
		
		newItem.write(writer);
		oldItem.write(writer);
	}
	
	public static InventoryAction read(EByteArrayReader reader) throws IOException {
		final InventoryActionSourceType sourceType = InventoryActionSourceType.VALUES.get(reader.readUnsignedVarInt());
		InventoryActionSlotType slotType = null;
		long sourceFlags = 0;
		
		switch(sourceType){
		case CONTAINER:
			slotType = InventoryActionSlotType.VALUES.get(reader.readSignedVarInt());
			break;
		case WORLD:
			sourceFlags = reader.readUnsignedVarInt();
			break;
		default:
			break;
		}
		
		final long slot = reader.readUnsignedVarInt();
		final Item newItem = Item.read(reader), oldItem = Item.read(reader);
		
		return new InventoryAction(sourceType, slotType, slot, oldItem, newItem, sourceFlags);
	}
}
