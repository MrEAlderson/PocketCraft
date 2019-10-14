package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import java.util.Arrays;

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
		nbt.addString("Text", String.join("\n", Arrays.stream(this.lines).map((line) -> {
			if(line.getBytes().length >= 16)
				return new String(Arrays.copyOf(line.getBytes(), 16));
			else
				return line;
		}).toArray(String[]::new)));
	}

	@Override
	protected void _read(NBTCompound nbt){
		this.lines = nbt.get("Text").getValue(String.class).split("\n", 4);
	}
}
