package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntitySkull extends BlockEntity {
	
	@Getter @Setter private byte skullType;
	@Getter @Setter private byte rotation;
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.SKULL;
	}

	@Override
	protected void _write(NBTCompound nbt){
		nbt.addByte("SkullType", this.skullType);
		nbt.addByte("Rot", this.rotation);
	}

	@Override
	protected void _read(NBTCompound nbt){
		this.skullType = nbt.get("SkullType").getValue(byte.class);
		this.rotation = nbt.get("Rot").getValue(byte.class);
	}
}
