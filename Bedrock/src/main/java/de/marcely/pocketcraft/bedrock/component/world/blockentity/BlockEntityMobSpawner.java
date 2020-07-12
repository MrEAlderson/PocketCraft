package de.marcely.pocketcraft.bedrock.component.world.blockentity;

import de.marcely.pocketcraft.bedrock.component.nbt.BNBTCompound;
import lombok.Getter;
import lombok.Setter;

public class BlockEntityMobSpawner extends BlockEntity {
	
	// some of them might not even exist in bedrock. who knows
	@Getter @Setter private String entityId;
	@Getter @Setter private short maxNearbyEntities;
	@Getter @Setter private short requiredPlayerRange;
	@Getter @Setter private short spawnCount;
	@Getter @Setter private short spawnRange;
	@Getter @Setter private short delay;
	@Getter @Setter private short minSpawnDelay;
	@Getter @Setter private short maxSpawnDelay;
	
	public BlockEntityMobSpawner(int x, int y, int z){
		super(x, y, z);
	}
	
	@Override
	public BlockEntityType getType(){
		return BlockEntityType.MOB_SPAWNER;
	}

	@Override
	protected void _write(BNBTCompound nbt){
		if(this.entityId != null){
    		nbt.addString("EntityId", this.entityId);
    		nbt.addShort("MaxNearbyEntities", this.maxNearbyEntities);
    		nbt.addShort("RequiredPlayerRange", this.requiredPlayerRange);
    		nbt.addShort("SpawnCount", this.spawnCount);
    		nbt.addShort("MaxSpawnDelay", this.maxSpawnDelay);
    		nbt.addShort("SpawnRange", this.spawnRange);
    		nbt.addShort("Delay", this.delay);
    		nbt.addShort("MinSpawnDelay", this.minSpawnDelay);
		}
	}

	@Override
	protected void _read(BNBTCompound nbt){
		if(nbt.contains("EntityId")){
    		this.entityId = nbt.getString("EntityId");
    		this.maxNearbyEntities = nbt.getShort("MaxNearbyEntities");
    		this.requiredPlayerRange = nbt.getShort("RequiredPlayerRange");
    		this.spawnCount = nbt.getShort("SpawnCount");
    		this.maxSpawnDelay = nbt.getShort("MaxSpawnDelay");
    		this.spawnRange = nbt.getShort("SpawnRange");
    		this.delay = nbt.getShort("Delay");
    		this.minSpawnDelay = nbt.getShort("MinSpawnDelay");
		}
	}
	
	public void reset(){
		this.entityId = null;
	}
}
