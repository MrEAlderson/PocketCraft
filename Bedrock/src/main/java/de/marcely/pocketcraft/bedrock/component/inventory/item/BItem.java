package de.marcely.pocketcraft.bedrock.component.inventory.item;

import java.io.IOException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.component.nbt.BNBTCompound;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

@ToString
@EqualsAndHashCode
public class BItem {
	
	@Getter private final int type;
	@Getter @Setter private int amount, durability;
	@Getter private final BItemMeta meta;
	
	public BItem(int type){
		this(type, 1, 0, new BNBTCompound());
	}
	
	public BItem(int type, int amount){
		this(type, amount, 0, new BNBTCompound());
	}
	
	public BItem(int type, int amount, int durability){
		this(type, amount, durability, new BNBTCompound());
	}
	
	public BItem(int type, int amount, int durability, BNBTCompound nbt){
		this.type = type;
		this.amount = amount;
		this.durability = durability;
		this.meta = type >= 1 ? BItemMeta.newInstance(type, nbt) : null;
	}
	
	public void write(EByteArrayWriter stream) throws IOException {
		stream.writeSignedVarInt(this.type);
		
		if(this.type == 0)
			return;
		
		stream.writeSignedVarInt(((this.durability >= 0 ? (this.durability & 0x7fff) : -1) << 8) | this.amount);
		
		this.meta.write(stream);
		
		stream.writeSignedVarInt(0); //TODO CanPlaceOn entry count
		stream.writeSignedVarInt(0); //TODO CanDestroyOn entry count
		
		if(this.type == BItemId.SHIELD /* blocking tick */)
			stream.writeSignedVarLong(0);
	}
	
	public static BItem read(EByteArrayReader stream) throws IOException {
		final int id = stream.readSignedVarInt();
		
		if(id == 0)
			return new BItem(id);
		
		final int aux = stream.readSignedVarInt();
		final int amount = aux & 0xFF;
		final int data = aux >> 8;
		final int tagLength = stream.readLShort();
		final BNBTCompound nbt = new BNBTCompound();
		
		if(tagLength >= 1){
			System.out.println("nbt reading not supported :(");
		}
		
		if(tagLength == 0xFFFF){
			System.out.println("Unsupported! Item");
		}
		
		return new BItem(id, amount, data, nbt);
	}
	
	public static BItem ofJson(JsonObject obj){
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
		
		final BItem item = new BItem(id, amount, damage);
		
		//if(nbtHex != null)
		//	item.tags = BinaryUtil.parseHexBinary(nbtHex.getAsString());
		
		return item;
	}
}