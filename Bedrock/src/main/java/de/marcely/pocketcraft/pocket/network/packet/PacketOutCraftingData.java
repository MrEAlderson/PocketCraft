package de.marcely.pocketcraft.pocket.network.packet;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import de.marcely.pocketcraft.bedrock.inventory.Item;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketOutCraftingData extends PCPacket {
	
	public CraftingDataEntry[] entries;
	 public boolean cleanRecipes;
	
	public PacketOutCraftingData(){
		super(PacketType.OutCraftingData);
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
		public Item result;
	}
	
	public static interface CraftingDataEntry {
		
		public int getType();
		
		public void encode(EByteArrayWriter writer) throws Exception;
	}
	
	public static class ShapelessRecipe extends Resultable implements CraftingDataEntry {
		
		public List<Item> ingredients;
		
		@Override
		public int getType(){ return 0; }
		
		@Override
		public void encode(EByteArrayWriter writer) throws Exception {
			writer.writeUnsignedVarInt(ingredients.size());
			
			for(Item item:ingredients)
				item.write(writer);
			
			writer.writeUnsignedVarInt(1);
			result.write(writer);
			writer.writeUUID(id);
		}
	}
	
	public static class ShapedRecipe extends Resultable implements CraftingDataEntry {
		
		public Character[][] shape;
		public Map<Character, Item> indegridients;
		
		@Override
		public int getType(){ return 1; }

		@Override
		public void encode(EByteArrayWriter writer) throws Exception {
			final int width = shape.length;
			final int height = shape[0].length;
			
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
		
		public Item getIndegridientAt(int x, int y){
			final Character c = shape[x][y];
			
			if(c != null){
				final Item item = indegridients.get(c);
				
				if(item != null)
					return item;
			}
			
			return new Item(0);
		}
	}
	
	public static class FurnaceRecipe extends Resultable implements CraftingDataEntry {
		
		public Item input;
		
		@Override
		public int getType(){ return input.data != 0 ? 3 : 2; }

		@Override
		public void encode(EByteArrayWriter writer) throws Exception {
			writer.writeSignedVarInt(input.id);
			if(input.data != 0) writer.writeSignedVarInt(input.data);
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
