package de.marcely.pocketcraft.java.component.entity.meta;

import de.marcely.pocketcraft.java.util.EByteBuf;
import de.marcely.pocketcraft.utils.math.Vector3;

class MetaEntryVector3 extends MetaEntry<Vector3> implements MetaEntryV8<Vector3> {

	public MetaEntryVector3(){
		this(new Vector3());
	}
	
	public MetaEntryVector3(Vector3 data){
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
