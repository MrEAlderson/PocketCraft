package de.marcely.pocketcraft.bedrock.network.packet.action;

import java.io.IOException;

import de.marcely.pocketcraft.bedrock.component.inventory.item.BItem;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import de.marcely.pocketcraft.utils.math.Vector3;
import lombok.Getter;

public class CancelUseItemAction extends Action {
	
	public final long actionType;
	@Getter public final int hotbarSlot;
	public final BItem item;
	public final Vector3 headRotation;
	
	public CancelUseItemAction(long actionType, int hotbarSlot, BItem item, Vector3 headRotation){
		this.actionType = actionType;
		this.hotbarSlot = hotbarSlot;
		this.item = item;
		this.headRotation = headRotation;
	}
	
	@Override
	public ActionType getType(){
		return ActionType.CANCEL_USE_ITEM;
	}
	
	public static CancelUseItemAction read(EByteArrayReader stream) throws IOException {
		final long actionType = stream.readUnsignedVarInt();
		final int hotbarSlot = stream.readSignedVarInt();
		final BItem item = BItem.read(stream);
		final Vector3 headRotation = new Vector3(stream.readLFloat(), stream.readLFloat(), stream.readLFloat());

		return new CancelUseItemAction(actionType, hotbarSlot, item, headRotation);
	}
}
