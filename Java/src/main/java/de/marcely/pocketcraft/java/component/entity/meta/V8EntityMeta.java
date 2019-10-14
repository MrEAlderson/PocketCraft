package de.marcely.pocketcraft.java.component.entity.meta;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jetbrains.annotations.Nullable;

import de.marcely.pocketcraft.java.component.Item;
import de.marcely.pocketcraft.java.util.EByteBuf;
import de.marcely.pocketcraft.utils.math.Vector3;

public class V8EntityMeta {
	
	private Map<Integer, MetaEntryV8<?>> entries = new HashMap<>();
	
	public void writeByte(int key, int value){
		this.entries.put(key, new MetaEntryByte((byte) value));
	}
	
	public void writeShort(int key, int value){
		this.entries.put(key, new MetaEntryShort((short) value));
	}
	
	public void writeInt(int key, int value){
		this.entries.put(key, new MetaEntryInt(value));
	}
	
	public void writeFloat(int key, byte value){
		this.entries.put(key, new MetaEntryFloat(value));
	}
	
	public void writeString(int key, String value){
		this.entries.put(key, new MetaEntryString(value));
	}
	
	public void writeItem(int key, Item value){
		this.entries.put(key, new MetaEntryItem(value));
	}
	
	public void writeBlockPosition(int key, Vector3 value){
		this.entries.put(key, new MetaEntryBlockPosition(value));
	}
	
	public void writeVector3(int key, Vector3 value){
		this.entries.put(key, new MetaEntryVector3(value));
	}
	
	public byte readByte(int key){
		return (byte) this.entries.get(key).getData();
	}
	
	public short readShort(int key){
		return (short) this.entries.get(key).getData();
	}
	
	public int readInt(int key){
		return (int) this.entries.get(key).getData();
	}
	
	public float readFloat(int key){
		return (float) this.entries.get(key).getData();
	}
	
	public String readString(int key){
		return (String) this.entries.get(key).getData();
	}
	
	public @Nullable Item readItem(int key){
		return (Item) this.entries.get(key).getData();
	}
	
	public Vector3 readBlockPosition(int key){
		return (Vector3) this.entries.get(key).getData();
	}
	
	public Vector3 readVector3(int key){
		return (Vector3) this.entries.get(key).getData();
	}
	
	public void write(EByteBuf buf){
		for(Entry<Integer, MetaEntryV8<?>> e:this.entries.entrySet()){
			final byte key = (byte) (int) e.getKey();
			final MetaEntryV8<?> entry = e.getValue();
			
			buf.writeByte((entry.getV8Id() << 5) | (key & 0x1F));
			entry.write(buf);
		}
		
		buf.writeByte(0x7F);
	}
	
	public static V8EntityMeta read(EByteBuf buf){
		final V8EntityMeta meta = new V8EntityMeta();
		
		while(true){
			final byte item = buf.readByte();
			
			if(item == 0x7F)
				return meta;
			
			final int key = item & 0x1F;
			final byte type = (byte) (item >> 5);
			final MetaEntryV8<?> entry = MetaEntryV8.newInstanceById(type);
			
			entry.read(buf);
			
			meta.entries.put(key, entry);
		}
	}
}