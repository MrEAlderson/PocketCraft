package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntitySign extends BlockEntity {
	
	@Getter @Setter private String[] lines = new String[0];
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.SIGN;
	}

	@Override
	protected void _write(NBTCompound nbt){
		nbt.addString("Text", String.join("\n", this.lines));
	}

	@Override
	protected void _read(NBTCompound nbt){
		this.lines = nbt.get("Text").getValue(String.class).split("\n", 4);
	}
}
