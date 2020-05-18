package de.marcely.pocketcraft.translate.bedrocktojava.world.block;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.translate.bedrocktojava.world.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class BlockCollision implements Cloneable {
	
	public final Cube[] entries;
	
	public BlockCollision(Cube... entries){
		this.entries = entries;
	}
	
	public @Nullable Cube getCollidingWith(int blockX, int blockY, int blockZ, Entity entity){
		final float x = entity.getX() - blockX;
		final float y = entity.getY() - blockY;
		final float z = entity.getZ() - blockZ;
		
		final float width = entity.getType().getWidth();
		final float height = entity.getType().getHeight();
		
		for(Cube entry:this.entries){
			// will falsely return true without the "- 0.0001F"
			if(entry.collidesWith(x - width/2F, y, z - width/2F, width - 0.0001F, height - 0.0001F, width - 0.0001F))
				return entry;
		}
		
		return null;
	}
	
	@Override
	public BlockCollision clone(){
		try{
			final BlockCollision coll = (BlockCollision) super.clone();
			
			for(int i=0; i<this.entries.length; i++)
				coll.entries[i] = this.entries[i].clone();
			
			return coll;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	@Data
	@EqualsAndHashCode
	public static class Cube implements Cloneable {
		
		public float x, y, z, widthX, height, widthZ;
		
		public boolean collidesWith(float x1, float y1, float z1, float widthX1, float height1, float widthZ1){
			final float maxX = x + widthX;
			final float maxY = y + height;
			final float maxZ = z + widthZ;
			final float maxX1 = x1 + widthX1;
			final float maxY1 = y1 + height1;
			final float maxZ1 = z1 + widthZ1;
			
			if(x1 >= x && x1 <= x + widthX || x1 < x && x1 + widthX1 >= x){
				if(y1 >= y && y1 <= y + height || y1 < y && y1 + height1 >= y){
					if(z1 >= z && z1 <= z + widthZ || z1 < z && z1 + widthZ1 >= z){
						return true;
					}
				}
			}
			
			return false;
		}
		
		@Override
		public Cube clone(){
			try{
				return (Cube) super.clone();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return null;
		}
	}
}
