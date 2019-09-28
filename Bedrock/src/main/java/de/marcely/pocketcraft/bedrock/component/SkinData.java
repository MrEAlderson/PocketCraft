package de.marcely.pocketcraft.bedrock.component;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;

public class SkinData {
	
	public static final String DEFAULT_MODEL_STEVE = "Standard_Steve";
	public static final String DEFAULT_MODEL_ALEX = "Standard_Alex";
    public static final int SINGLE_SKIN_SIZE = 64*32*4;
    public static final int DOUBLE_SKIN_SIZE = 64*64*4;
	
	public final byte[] data;
	
	public byte[] geometry, capeData;
	public String model, geometryName;
	public long id;
	
	public SkinData(byte[] data, String model, long id, byte[] geometry, String geometryName, byte[] capeData){
		this.data = data;
		this.model = model;
		this.id = id;
		this.geometry = geometry;
		this.geometryName = geometryName;
		this.capeData = capeData;
	}
	
	public void write(EByteArrayWriter writer) throws IOException {
		writer.writeString(this.model);
		writer.writeByteArray(this.data);
		writer.writeByteArray(this.capeData);
		writer.writeString(this.geometryName);
		writer.writeByteArray(this.geometry);
    }
	
	public static byte[] imageToData(BufferedImage image) throws IOException {
		if(image == null) return new byte[0];
		
		final int size = image.getHeight()*image.getWidth()*4;
		
		if(size != SINGLE_SKIN_SIZE && size != DOUBLE_SKIN_SIZE)
			throw new IllegalArgumentException("Invalid skin");
		
		
        final ByteArrayOutputStream stream = new ByteArrayOutputStream(size);
        
        for(int iy=0; iy<image.getHeight(); iy++){
            for (int ix=0; ix<image.getWidth(); ix++){
                final Color color = new Color(image.getRGB(ix, iy), true);
                
                stream.write(color.getRed());
                stream.write(color.getGreen());
                stream.write(color.getBlue());
                stream.write(color.getAlpha());
            }
        }
        
        image.flush();
        stream.close();
        
        return stream.toByteArray();
	}
}
