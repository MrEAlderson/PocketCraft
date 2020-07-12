package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntitySkull extends BlockEntity {
	
	@Getter @Setter private byte skullType;
	@Getter @Setter private byte rotation;
	
	public BlockEntitySkull(int x, int y, int z){
		super(x, y, z);
	}
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.SKULL;
	}

	@Override
	protected void _write(BNBTCompound nbt){
		nbt.addByte("SkullType", this.skullType);
		nbt.addByte("Rot", this.rotation);
	}

	@Override
	protected void _read(BNBTCompound nbt){
		this.skullType = nbt.get("SkullType").getValueData();
		this.rotation = nbt.get("Rot").getValueData();
	}
}
