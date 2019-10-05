package de.marcely.pocketcraft.bedrock.network.packet.action;

import java.io.IOException;

import de.marcely.pocketcraft.bedrock.component.inventory.Item;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.utils.math.Vector3;

public class DropItemAction extends Action {
	
	public final long actionType;
	public final int hotbarSlot;
	public final Item item;
	public final Vector3 headRotation;
	
	public DropItemAction(long actionType, int hotbarSlot, Item item, Vector3 headRotation){
		super(ActionType.DROP_ITEM);
		
		this.actionType = actionType;
		this.hotbarSlot = hotbarSlot;
		this.item = item;
		this.headRotation = headRotation;
	}
	
	public static DropItemAction read(EByteArrayReader stream) throws IOException {
		final long actionType = stream.readUnsignedVarInt();
		final int hotbarSlot = stream.readSignedVarInt();
		final Item item = Item.read(stream);
		final Vector3 headRotation = new Vector3(stream.readLFloat(), stream.readLFloat(), stream.readLFloat());

		return new DropItemAction(actionType, hotbarSlot, item, headRotation);
	}
}
