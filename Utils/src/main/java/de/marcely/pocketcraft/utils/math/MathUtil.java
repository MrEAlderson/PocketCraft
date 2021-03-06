package de.marcely.pocketcraft.utils.math;

public class MathUtil {
	
	public static float circularDegrees(float deg){
		deg %= 360.0F;
		
        if(deg >= 180.0F)
        	deg -= 360.0F;
        else if(deg < -180.0F)
        	deg += 360.0F;

        return deg;
	}
	
	public static float distance(float x1, float y1, float x2, float y2){
		return (float) Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
}