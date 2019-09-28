package de.marcely.pocketcraft.utils;

public class BinaryUtil {
	
	private final static char[] HEXCHARS = "0123456789ABCDEF".toCharArray();
	
    public static String bytesToDisplayedHex(byte... bytes) {
        final char[] hexChars = new char[bytes.length * 3];
        
        for(int j=0; j<bytes.length; j++){
            final int v = bytes[j] & 0xFF;
            
            hexChars[j * 2] = HEXCHARS[v >>> 4];
            hexChars[j * 2 + 1] = HEXCHARS[v & 0x0F];
            hexChars[j * 2 + 2] = ' ';
        }
        
        return new String(hexChars);
    }
    
    // TODO: @START Replace with Base64
    public static byte[] parseHexBinary(String s){
        final int length = s.length();

        // "111" is not a valid hex encoding.
        if(length%2 != 0)
            throw new IllegalArgumentException("hexBinary needs to be even-length");

        final byte[] out = new byte[length/2];

        for(int i=0; i<length; i+=2) {
            final int h = hexToBin(s.charAt(i));
            final int l = hexToBin(s.charAt(i + 1));
            
            if(h == -1 || l == -1)
                throw new IllegalArgumentException("contains illegal character for hexBinary");

            out[i/2] = (byte)(h*16+l);
        }

        return out;
    }
    
    private static int hexToBin(char ch){
        if('0' <= ch && ch <= '9')    return ch - '0';
        if('A' <= ch && ch <= 'F')    return ch - 'A' + 10;
        if('a' <= ch && ch <= 'f')    return ch - 'a' + 10;
        return -1;
    }
}
