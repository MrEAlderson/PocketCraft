package de.marcely.pocketcraft.bedrock.entity;

import java.awt.Color;
import java.io.IOException;

import de.marcely.pocketcraft.utils.io.ByteArrayWriter;
import de.marcely.pocketcraft.utils.math.Vector3;

public enum EntityDataValueType {
	
	INT(2),
	BYTE(0),
	LONG(7),
	FLOAT(3),
	SHORT(1),
	BOOLEAN(0),
	SLOT(5),
	STRING(4),
	COLOR(2),
	LOCATION(8),
	BLOCK_LOCATION(6);
	
	public final int id;
	
	private EntityDataValueType(int id){
		this.id = id;
	}
	
	public boolean isInstance(Object obj){
		switch(this){
		case INT:
			return obj instanceof Integer;
		case BYTE:
			return obj instanceof Byte;
		case LONG:
			return obj instanceof Long;
		case FLOAT:
			return obj instanceof Float;
		case SHORT:
			return obj instanceof Short;
		case BOOLEAN:
			return obj instanceof Boolean;
		case STRING:
			return obj instanceof String;
		case COLOR:
			return obj instanceof Color;
		case LOCATION:
			return obj instanceof Vector3;
		case BLOCK_LOCATION:
			return obj instanceof Vector3;
		default:
			throw new NullPointerException();
		}
	}
	
	@Deprecated
	public Object convert(Object obj){
		switch(this){
		case INT:
			return (int) obj;
		case BYTE:
			return (byte) obj;
		case LONG:
			return (long) obj;
		case FLOAT:
			return (float) obj;
		case SHORT:
			return (short) obj;
		case BOOLEAN:
			return (boolean) obj;
		case STRING:
			return (String) obj;
		default:
			return obj;
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
		case BOOLEAN:
			writer.writeBoolean((boolean) obj);
			break;
		case STRING:
			writer.writeString((String) obj);
			break;
		case COLOR:
			writer.writeSignedVarInt((int) ((Color) obj).getRGB());
			break;
		case LOCATION:
			final Vector3 loc1 = (Vector3) obj;
			
			writer.writeLFloat(loc1.getX());
			writer.writeLFloat(loc1.getY());
			writer.writeLFloat(loc1.getZ());
			break;
		case BLOCK_LOCATION:
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
