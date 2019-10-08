package de.marcely.pocketcraft.bedrock.world.blockentity;

public interface BlockEntityNameable {
	
	public void setCustomName(String customName);
	
	public String getCustomName();
	
	public void removeCustomName();
	
	public boolean hasCustomName();
}
