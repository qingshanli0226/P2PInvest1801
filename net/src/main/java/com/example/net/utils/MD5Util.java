package com.example.net.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    private static final char hexDigsits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String getMD5(String str) {
        byte[] bytes = str.getBytes();
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(bytes);
            byte[] digest = md5.digest();
            char[] chars = new char[digest.length * 2];
            int k = 0;
            for (byte aChar : digest) {
                chars[k++] = hexDigsits[aChar >>> 4 & 0xf];
                chars[k++] = hexDigsits[aChar & 0xf];
            }
            return new String(chars);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
