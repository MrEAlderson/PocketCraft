package de.marcely.pocketcraft.bedrock.network.packet.action;

import java.io.IOException;

import de.marcely.pocketcraft.bedrock.component.BlockFace;
import de.marcely.pocketcraft.bedrock.component.inventory.Item;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;
import lombok.Getter;

public class UseItemAction extends Action {
	
	public static final byte ACTION_TYPE_INTERACT = 0;
	public static final byte ACTION_TYPE_PLACE_BLOCK = 1;
	public static final byte ACTION_TYPE_PLACE_BREAK = 2;
	
	public final long actionType;
	public final int blockPosX, blockPosY, blockPosZ;
	public final BlockFace face;
	@Getter public final int hotbarSlot;
	public final Item item;
	public final float playerPosX, playerPosY, playerPosZ;
	public final float clickPosX, clickPosY, clickPosZ;
	
	public UseItemAction(long actionType, int blockPosX, int blockPosY, int blockPosZ, BlockFace face,
					     int hotbarSlot, Item item, float playerPosX, float playerPosY, float playerPosZ,
					     float clickPosX, float clickPosY, float clickPosZ){
		
		this.actionType = actionType;
		this.blockPosX = blockPosX;
		this.blockPosY = blockPosY;
		this.blockPosZ = blockPosZ;
		this.face = face;
		this.hotbarSlot = hotbarSlot;
		this.item = item;
		this.playerPosX = playerPosX;
		this.playerPosY = playerPosY;
		this.playerPosZ = playerPosZ;
		this.clickPosX = clickPosX;
		this.clickPosY = clickPosY;
		this.clickPosZ = clickPosZ;
	}
	
	@Override
	public ActionType getType(){
		return ActionType.USE_ITEM;
	}
	
	public static UseItemAction read(EByteArrayReader stream) throws IOException {
		final long actionType = stream.readUnsignedVarInt();
		final int blockPosX = stream.readSignedVarInt();
		final int blockPosY = (int) stream.readUnsignedVarInt();
		final int blockPosZ = stream.readSignedVarInt();
		final int f = stream.readSignedVarInt();
		final BlockFace face = BlockFace.getById((byte) f);
		final int hotbarSlot = stream.readSignedVarInt();
		final Item item = Item.read(stream);
		final float playerPosX = stream.readLFloat();
		final float playerPosY = stream.readLFloat();
		final float playerPosZ = stream.readLFloat();
		final float clickPosX = stream.readLFloat();
		final float clickPosY = stream.readLFloat();
		final float clickPosZ = stream.readLFloat();
		
		return new UseItemAction(actionType, blockPosX, blockPosY, blockPosZ, face, hotbarSlot, item,
								 playerPosX, playerPosY, playerPosZ, clickPosX, clickPosY, clickPosZ);
	}
}
