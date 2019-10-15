package de.marcely.pocketcraft.bedrock.component;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import lombok.Getter;

public class SkinData {
	
	private static final int PIXEL_SIZE = 4;
	
	public static final String GEOMETRY_CUSTOM = "geometry.humanoid.custom";
    public static final String GEOMETRY_CUSTOM_SLIM = "geometry.humanoid.customSlim";
    
    public static final int SINGLE_SKIN_SIZE = 64 * 32 * PIXEL_SIZE;
    public static final int DOUBLE_SKIN_SIZE = 64 * 64 * PIXEL_SIZE;
    public static final int SKIN_128_64_SIZE = 128 * 64 * PIXEL_SIZE;
    public static final int SKIN_128_128_SIZE = 128 * 128 * PIXEL_SIZE;
	
	@Getter private final String skinId;
	@Getter private final byte[] skinData, capeData, geometryData;
	@Getter private final String geometryName;
	
	public SkinData(String skinId, byte[] skinData, byte[] capeData, String geometryName, byte[] geometryData){
		this.skinId = skinId;
		this.skinData = skinData;
		this.capeData = capeData;
		this.geometryName = geometryName;
		this.geometryData = geometryData;
	}
	
	public void write(EByteArrayWriter writer) throws IOException {
		writer.writeString(this.skinId);
        writer.writeByteArray(this.skinData);
        writer.writeByteArray(this.capeData);
        writer.writeString(this.geometryName);
        writer.writeByteArray(this.geometryData);
    }
	
	public static SkinData getDefaultSkin(){
		return new SkinData(
				"Steve",
				new byte[SINGLE_SKIN_SIZE],
				new byte[SINGLE_SKIN_SIZE],
				GEOMETRY_CUSTOM,
				new byte[0]);
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
