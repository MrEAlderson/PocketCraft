package de.marcely.pocketcraft.bedrock.network.packet.action;

import java.io.IOException;

import de.marcely.pocketcraft.bedrock.component.BlockFace;
import de.marcely.pocketcraft.bedrock.inventory.Item;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class UseItemAction extends Action {
	
	/*
	 * 0 = Only interact
	 * 1 = Place block
	 */
	public final long actionType;
	public final int blockPosX, blockPosY, blockPosZ;
	public final BlockFace face;
	public final int hotbarSlot;
	public final Item item;
	public final float playerPosX, playerPosY, playerPosZ;
	public final float clickPosX, clickPosY, clickPosZ;
	
	public UseItemAction(long actionType, int blockPosX, int blockPosY, int blockPosZ, BlockFace face,
					     int hotbarSlot, Item item, float playerPosX, float playerPosY, float playerPosZ,
					     float clickPosX, float clickPosY, float clickPosZ){
		
		super(ActionType.USE_ITEM);
		
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
	
	public static UseItemAction read(EByteArrayReader stream) throws IOException {
		final long actionType = stream.readUnsignedVarInt();
		final int blockPosX = stream.readSignedVarInt();
		final int blockPosY = (int) stream.readUnsignedVarInt();
		final int blockPosZ = stream.readSignedVarInt();
		final int f = stream.readSignedVarInt();
		final BlockFace face = BlockFace.ofId((byte) f);
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
