package de.marcely.pocketcraft.java.component.v8.entity.meta;

import de.marcely.pocketcraft.java.component.MetaEntry;
import de.marcely.pocketcraft.java.util.EByteBuf;
import de.marcely.pocketcraft.utils.math.Vector3;

class V8MetaEntryBlockPosition extends MetaEntry<Vector3> implements V8MetaEntry<Vector3> {

	public V8MetaEntryBlockPosition(){
		this(new Vector3());
	}
	
	public V8MetaEntryBlockPosition(Vector3 data){
		this.data = data;
	}
	
	@Override
	public void write(EByteBuf buf){
		buf.writeInt(this.data.getFloorX());
		buf.writeInt(this.data.getFloorY());
		buf.writeInt(this.data.getFloorZ());
	}

	@Override
	public void read(EByteBuf buf){
		this.data = new Vector3(buf.readInt(), buf.readInt(), buf.readInt());
	}

	@Override
	public byte getV8Id(){
		return 6;
	}
}
