package de.marcely.pocketcraft.bedrock.component.inventory;

import java.io.IOException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import de.marcely.pocketcraft.utils.BinaryUtil;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class Item {

	public int id, amount, data;
	public byte[] tags = new byte[0];
	
	public Item(int id){
		this(id, 1, 0);
	}
	
	public Item(int id, int amount){
		this(id, amount, 0);
	}
	
	public Item(int id, int amount, int data){
		this.id = id;
		this.amount = amount;
		this.data = data;
	}
	
	@SuppressWarnings("unused")
	public void write(EByteArrayWriter stream) throws IOException {
		stream.writeSignedVarInt(id);
		
		if(id != 0 /* AIR */){
			stream.writeSignedVarInt(((data >= 0 ? data : -1) << 8) | amount);
			
			if(false/*tags.length >= 1*/){
				stream.writeLShort(0xFFFF);
				stream.writeSignedByte((byte) 0x1);
				stream.write(tags);
			
			}else{
				stream.writeLShort(0);
			}
			
			stream.writeSignedVarInt(0); //TODO CanPlaceOn entry count
			stream.writeSignedVarInt(0); //TODO CanDestroyOn entry count
			
			if(this.id == 513 /* shield */)
				stream.writeSignedVarLong(0);
			
		}
	}
	
	public static Item read(EByteArrayReader stream) throws IOException {
		final int id = stream.readSignedVarInt();
		int amount = 0, data = 0;
		
		if(id != 0){
			final int rawData = stream.readSignedVarInt();
			final int tagLength = stream.readLShort();
			amount = rawData & 0xFF;
			data = rawData >> 8;
			
			if(tagLength == 0xFFFF){
				System.out.println("Unsupported! Item");
			}
			
			stream.readSignedVarInt(); //TODO CanPlaceOn entry count
			stream.readSignedVarInt(); //TODO CanDestroyOn entry count
		}
		
		return new Item(id, amount, data);
	}
	
	public static Item ofJson(JsonObject obj){
		final int id = obj.get("id").getAsInt();
		final JsonElement damageE = obj.get("damage");
		final JsonElement amountE = obj.get("count");
		final JsonElement nbtHex = obj.get("nbt_hex");
		
		int damage = 0;
		int amount = 1;
		
		if(damageE != null)
			damage = damageE.getAsInt();
		
		if(amountE != null)
			amount = amountE.getAsInt();
		
		final Item item = new Item(id, amount, damage);
		
		if(nbtHex != null)
			item.tags = BinaryUtil.parseHexBinary(nbtHex.getAsString());
		
		return item;
	}
}