package de.marcely.pocketcraft.bedrock.network.packet;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.inventory.item.BItem;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketCraftingData extends PCPacket {
	
    public static final String CRAFTING_TAG_CRAFTING_TABLE = "crafting_table";
    public static final String CRAFTING_TAG_CARTOGRAPHY_TABLE = "cartography_table";
    public static final String CRAFTING_TAG_STONECUTTER = "stonecutter";
    public static final String CRAFTING_TAG_FURNACE = "furnace";
    public static final String CRAFTING_TAG_CAMPFIRE = "campfire";
    public static final String CRAFTING_TAG_BLAST_FURNACE = "blast_furnace";
    public static final String CRAFTING_TAG_SMOKER = "smoker";
	
	public CraftingDataEntry[] entries;
	public boolean cleanRecipes;
	
	public PacketCraftingData(){
		super(PacketType.CraftingData);
	}
	
	@Override
	public void encode(EByteArrayWriter writer) throws Exception {
		writer.writeUnsignedVarInt(entries.length);
		
		for(CraftingDataEntry entry:entries){
			writer.writeSignedVarInt(entry.getType());
			entry.encode(writer);
		}
		
		writer.writeBoolean(this.cleanRecipes);
	}

	@Override
	public void decode(EByteArrayReader reader) throws Exception { }
	
	
	
	
	
	
	public static abstract class Resultable {
		
		public UUID id;
		public BItem result;
	}
	
	public static interface CraftingDataEntry {
		
		public int getType();
		
		public void encode(EByteArrayWriter writer) throws Exception;
	}
	
	public static class ShapelessRecipe extends Resultable implements CraftingDataEntry {
		
		public String recipeId;
		public List<BItem> ingredients;
		public int priority;
		
		@Override
		public int getType(){ return 0; }
		
		@Override
		public void encode(EByteArrayWriter writer) throws Exception {
			writer.writeString(this.recipeId);
			writer.writeUnsignedVarInt(this.ingredients.size());
			
			for(BItem item:this.ingredients)
				item.write(writer);
			
			writer.writeUnsignedVarInt(1);
			result.write(writer);
			writer.writeUUID(this.id);
			writer.writeString(CRAFTING_TAG_CRAFTING_TABLE);
			writer.writeSignedVarInt(this.priority);
			writer.writeUnsignedVarInt(0);
		}
	}
	
	public static class ShapedRecipe extends Resultable implements CraftingDataEntry {
		
		public String recipeId;
		public Character[][] shape;
		public Map<Character, BItem> indegridients;
		
		@Override
		public int getType(){ return 1; }

		@Override
		public void encode(EByteArrayWriter writer) throws Exception {
			final int width = this.shape.length;
			final int height = this.shape[0].length;
			
			writer.writeString(this.recipeId);
			writer.writeSignedVarInt(width);
			writer.writeSignedVarInt(height);
			
			for(int iy=0; iy<height; iy++){
				for(int ix=0; ix<width; ix++){
					getIndegridientAt(ix, iy).write(writer);
				}
			}
			
			writer.writeUnsignedVarInt(1);
			result.write(writer);
			writer.writeUUID(id);
		}
		
		public BItem getIndegridientAt(int x, int y){
			final Character c = shape[x][y];
			
			if(c != null){
				final BItem item = indegridients.get(c);
				
				if(item != null)
					return item;
			}
			
			return new BItem(0);
		}
	}
	
	public static class FurnaceRecipe extends Resultable implements CraftingDataEntry {
		
		public BItem input;
		
		@Override
		public int getType(){ return input.getDurability() != 0 ? 3 : 2; }

		@Override
		public void encode(EByteArrayWriter writer) throws Exception {
			writer.writeSignedVarInt(input.getType());
			
			if(input.getDurability() != 0)
				writer.writeSignedVarInt(input.getDurability());
			
			result.write(writer);
		}
	}
	
	@Deprecated
	public static class EnchantRecipe implements CraftingDataEntry {

		@Override
		public int getType(){ return 4; }

		@Override
		public void encode(EByteArrayWriter writer) throws Exception {
			
		}
	}
}
