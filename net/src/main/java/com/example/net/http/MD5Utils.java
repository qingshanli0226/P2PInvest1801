package com.example.net.http;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String md5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes("GBK"));
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : md5.digest()) {
                stringBuffer.append(String.format("%02x", b & 0xff));
            }
            return stringBuffer.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
