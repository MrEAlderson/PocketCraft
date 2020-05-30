package de.marcely.pocketcraft.utils;

import java.util.Arrays;
import java.util.UUID;

public class Util {
	
	public static byte[][] splitBytes(byte[] bytes, int chunkSize){
        byte[][] splits = new byte[(bytes.length + chunkSize - 1) / chunkSize][chunkSize];
        int chunks = 0;

        for (int i = 0; i < bytes.length; i += chunkSize){
            if ((bytes.length - i) > chunkSize) {
                splits[chunks] = Arrays.copyOfRange(bytes, i, i + chunkSize);
            } else {
                splits[chunks] = Arrays.copyOfRange(bytes, i, bytes.length);
            }
            
            chunks++;
        }

        return splits;
    }
	
	public static UUID parseUUID(String raw){
		if(raw.length() == 32){
			return UUID.fromString(raw.replaceFirst(
					"(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5" 
					));
		
		}else
			return UUID.fromString(raw);
	}
}