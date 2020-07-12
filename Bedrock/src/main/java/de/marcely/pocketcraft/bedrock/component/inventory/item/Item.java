package de.marcely.pocketcraft.bedrock.component.inventory.item;

import java.io.IOException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.nbt.NBTCompound;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

@ToString
@EqualsAndHashCode
public class Item {
	
	@Getter private final int type;
	@Getter @Setter private int amount, data;
	@Getter private final ItemMeta meta;
	
	public Item(int type){
		this(type, 1, 0, new NBTCompound());
	}
	
	public Item(int type, int amount){
		this(type, amount, 0, new NBTCompound());
	}
	
	public Item(int type, int amount, int data){
		this(type, amount, data, new NBTCompound());
	}
	
	public Item(int type, int amount, int data, NBTCompound nbt){
		this.type = type;
		this.amount = amount;
		this.data = data;
		this.meta = type >= 1 ? ItemMeta.newInstance(type, nbt) : null;
	}
	
	public void write(EByteArrayWriter stream) throws IOException {
		stream.writeSignedVarInt(this.type);
		
		if(this.type == 0)
			return;
		
		stream.writeSignedVarInt(((this.data >= 0 ? (this.data & 0x7fff) : -1) << 8) | this.amount);
		
		this.meta.write(stream);
		
		stream.writeSignedVarInt(0); //TODO CanPlaceOn entry count
		stream.writeSignedVarInt(0); //TODO CanDestroyOn entry count
		
		if(this.type == ItemId.SHIELD /* blocking tick */)
			stream.writeSignedVarLong(0);
	}
	
	public static Item read(EByteArrayReader stream) throws IOException {
		final int id = stream.readSignedVarInt();
		
		if(id == 0)
			return new Item(id);
		
		final int aux = stream.readSignedVarInt();
		final int amount = aux & 0xFF;
		final int data = aux >> 8;
		final int tagLength = stream.readLShort();
		final NBTCompound nbt = new NBTCompound();
		
		if(tagLength >= 1){
			System.out.println("nbt reading not supported :(");
		}
		
		if(tagLength == 0xFFFF){
			System.out.println("Unsupported! Item");
		}
		
		return new Item(id, amount, data, nbt);
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
		
		//if(nbtHex != null)
		//	item.tags = BinaryUtil.parseHexBinary(nbtHex.getAsString());
		
		return item;
	}
}