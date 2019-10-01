package de.marcely.pocketcraft.bedrock.world.entity;

import java.io.IOException;

import de.marcely.pocketcraft.utils.io.ByteArrayWriter;
import de.marcely.pocketcraft.utils.math.Vector3;

public enum EntityDataValueType {
	
	// do not change the order of these
	BYTE, // 0
	SHORT, // 1
	INT, // 2
	FLOAT, // 3
	STRING, // 4
	NBT, // 5 TODO
	FLOOR_VECTOR3, // 6
	LONG, // 7
	VECTOR3; // 8
	
	public byte getId(){
		return (byte) this.ordinal();
	}
	
	public boolean isInstance(Object obj){
		switch(this){
		case BYTE:
			return obj instanceof Byte;
		case SHORT:
			return obj instanceof Short;
		case INT:
			return obj instanceof Integer;
		case FLOAT:
			return obj instanceof Float;
		case STRING:
			return obj instanceof String;
		case LONG:
			return obj instanceof Long;
		case VECTOR3:
		case FLOOR_VECTOR3:
			return obj instanceof Vector3;
		default:
			throw new NullPointerException();
		}
	}
	
	public void write(Object obj, ByteArrayWriter writer) throws IOException {
		switch(this){
		case INT:
			writer.writeSignedVarInt((int) obj);
			break;
		case BYTE:
			writer.writeSignedByte((byte) obj);
			break;
		case LONG:
			writer.writeSignedVarLong((long) obj);
			break;
		case FLOAT:
			writer.writeLFloat((float) obj);
			break;
		case SHORT:
			writer.writeLShort((short) obj);
			break;
		case STRING:
			writer.writeString((String) obj);
			break;
		case VECTOR3:
			final Vector3 loc1 = (Vector3) obj;
			
			writer.writeLFloat(loc1.getX());
			writer.writeLFloat(loc1.getY());
			writer.writeLFloat(loc1.getZ());
			break;
		case FLOOR_VECTOR3:
			final Vector3 loc2 = (Vector3) obj;
			
			writer.writeSignedVarInt(loc2.getFloorX());
			writer.writeSignedVarInt(loc2.getFloorY());
			writer.writeSignedVarInt(loc2.getFloorZ());
			break;
		default:
			throw new NullPointerException();
		}
	}
}
