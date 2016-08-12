package com.cver.team.persistence.jena.helper;

public class ByteConverter {

    public static String convertBytesToString(byte[] array) {

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < array.length; i++) {
            sb.append(array[i]+" ");
        }
        return sb.toString();
    }

    public static byte[] convertStringToBytes(String array) {
        String[] bytz = array.split(" ");
        byte[] byteArray = new byte[bytz.length];
        for(int i = 0; i < byteArray.length; i++)
            byteArray[i] = (byte) Integer.parseInt(bytz[i]);

        return byteArray;
    }

}
