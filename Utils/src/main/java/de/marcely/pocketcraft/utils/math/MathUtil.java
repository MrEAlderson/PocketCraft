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
}