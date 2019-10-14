package de.marcely.pocketcraft.java.component.entity.meta;

import de.marcely.pocketcraft.java.util.EByteBuf;
import de.marcely.pocketcraft.utils.math.Vector3;

class MetaEntryBlockPosition extends MetaEntry<Vector3> implements MetaEntryV8<Vector3> {

	public MetaEntryBlockPosition(){
		this(new Vector3());
	}
	
	public MetaEntryBlockPosition(Vector3 data){
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
