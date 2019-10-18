package de.marcely.pocketcraft.bedrock.network.packet.action;

import java.io.IOException;

import de.marcely.pocketcraft.bedrock.component.inventory.Item;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.utils.math.Vector3;
import lombok.Getter;

public class UseItemOnEntityAction extends Action {
	
	public static final byte ACTION_TYPE_INTERACT = 0;
	public static final byte ACTION_TYPE_ATTACK = 1;
	
	public final long entityId;
	public final long actionType;
	@Getter public final int hotbarSlot;
	public final Item item;
	public final Vector3 playerPos, clickPos;
	
	public UseItemOnEntityAction(long entityId, long actionType, int hotbarSlot, Item item, Vector3 playerPos,
								 Vector3 clickPos){
		
		this.entityId = entityId;
		this.actionType = actionType;
		this.hotbarSlot = hotbarSlot;
		this.item = item;
		this.playerPos = playerPos;
		this.clickPos = clickPos;
	}
	
	@Override
	public ActionType getType(){
		return ActionType.USE_ITEM_ON_ENTITY;
	}
	
	public static UseItemOnEntityAction read(EByteArrayReader stream) throws IOException {
		final long entityId = stream.readUnsignedVarLong();
		final long actionType = stream.readUnsignedVarInt();
		final int hotbarSlot = stream.readSignedVarInt();
		final Item item = Item.read(stream);
		final Vector3 playerPos = new Vector3(stream.readLFloat(), stream.readLFloat(), stream.readLFloat());
		final Vector3 clickPos = new Vector3(stream.readLFloat(), stream.readLFloat(), stream.readLFloat());
		
		return new UseItemOnEntityAction(entityId, actionType, hotbarSlot, item, playerPos, clickPos);
	}
}
