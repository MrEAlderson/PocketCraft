package de.marcely.pocketcraft.utils;

import java.util.Arrays;

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
}