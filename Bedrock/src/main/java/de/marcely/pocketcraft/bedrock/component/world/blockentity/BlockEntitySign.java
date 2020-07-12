package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import java.util.Arrays;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntitySign extends BlockEntity {
	
	@Getter @Setter private String[] lines = new String[0];
	
	public BlockEntitySign(int x, int y, int z){
		super(x, y, z);
	}
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.SIGN;
	}

	@Override
	protected void _write(BNBTCompound nbt){
		nbt.addString("Text", String.join("\n", Arrays.stream(this.lines).map((line) -> {
			if(line.getBytes().length >= 16)
				return new String(Arrays.copyOf(line.getBytes(), 16));
			else
				return line;
		}).toArray(String[]::new)));
	}

	@Override
	protected void _read(BNBTCompound nbt){
		final String rawLines = nbt.get("Text").getValueData();
		
		this.lines = rawLines.split("\n", 4);
	}
}
