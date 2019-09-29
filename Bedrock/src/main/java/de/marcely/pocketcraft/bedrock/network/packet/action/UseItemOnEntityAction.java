package de.marcely.pocketcraft.bedrock.network.packet.action;

import java.io.IOException;

import de.marcely.pocketcraft.bedrock.inventory.Item;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.utils.math.Vector3;

public class UseItemOnEntityAction extends Action {
	
	public final long entityID;
	/*
	 * 1 = Hit
	 * 0 = Interact
	 */
	public final long actionType;
	public final int hotbarSlot;
	public final Item item;
	public final Vector3 velo1, velo2;
	
	public UseItemOnEntityAction(long entityID, long actionType, int hotbarSlot, Item item, Vector3 velo1,
								 Vector3 velo2){
		
		super(ActionType.USE_ITEM_ON_ENTITY);
		
		this.entityID = entityID;
		this.actionType = actionType;
		this.hotbarSlot = hotbarSlot;
		this.item = item;
		this.velo1 = velo1;
		this.velo2 = velo2;
	}
	
	public static UseItemOnEntityAction read(EByteArrayReader stream) throws IOException {
		final long entityID = stream.readUnsignedVarLong();
		final long actionType = stream.readUnsignedVarInt();
		final int hotbarSlot = stream.readSignedVarInt();
		final Item item = Item.read(stream);
		final Vector3 velo1 = new Vector3(stream.readLFloat(), stream.readLFloat(), stream.readLFloat());
		final Vector3 velo2 = new Vector3(stream.readLFloat(), stream.readLFloat(), stream.readLFloat());
		
		return new UseItemOnEntityAction(entityID, actionType, hotbarSlot, item, velo1, velo2);
	}
}
