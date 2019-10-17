package de.marcely.pocketcraft.bedrock.network.packet;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.network.packet.action.*;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketInventoryAction extends PCPacket {
	
	public Action action;
	public InventoryAction[] invActions;
	
	public PacketInventoryAction(){
		super(PacketType.InventoryAction);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception { }

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		final ActionType type = ActionType.getById((int) reader.readUnsignedVarInt());
		
		{
			this.invActions = new InventoryAction[(int) reader.readUnsignedVarInt()];
			
			for(int i=0; i<this.invActions.length; i++)
				this.invActions[i] = InventoryAction.read(reader);
		}
		
		switch(type){
		case USE_ITEM:
			this.action = UseItemAction.read(reader);
			break;
		case USE_ITEM_ON_ENTITY:
			this.action = UseItemOnEntityAction.read(reader);
			break;
		case DROP_ITEM:
			this.action = DropItemAction.read(reader);
			break;
		default:
			this.action = null;
			break;
		}
	}
}
