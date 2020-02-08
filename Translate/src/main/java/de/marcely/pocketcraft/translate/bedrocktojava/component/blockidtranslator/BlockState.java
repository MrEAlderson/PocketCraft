package de.marcely.pocketcraft.translate.bedrocktojava.component.blockidtranslator;

import org.jetbrains.annotations.Nullable;

import lombok.Data;

@Data
public class BlockState {
	
	public final int bedrockId;
	public final int bedrockData;
	@Nullable public final BlockCollision collision;
}
