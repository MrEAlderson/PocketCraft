package de.marcely.pocketcraft.java.component.v8.entity.meta;

import de.marcely.pocketcraft.java.component.MetaEntry;
import de.marcely.pocketcraft.java.util.EByteBuf;
import de.marcely.pocketcraft.utils.math.Vector3;

class V8MetaEntryVector3 extends MetaEntry<Vector3> implements V8MetaEntry<Vector3> {

	public V8MetaEntryVector3(){
		this(new Vector3());
	}
	
	public V8MetaEntryVector3(Vector3 data){
		this.data = data;
	}
	
	@Override
	public void write(EByteBuf buf){
		buf.writeFloat(this.data.getX());
		buf.writeFloat(this.data.getY());
		buf.writeFloat(this.data.getZ());
	}

	@Override
	public void read(EByteBuf buf){
		this.data = new Vector3(buf.readFloat(), buf.readFloat(), buf.readFloat());
	}

	@Override
	public byte getV8Id(){ 
		return 7;
	}
}
