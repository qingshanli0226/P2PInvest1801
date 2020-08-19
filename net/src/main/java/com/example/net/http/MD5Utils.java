package com.example.net.http;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

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

    public static TreeMap<String, String> ValuesBase64(TreeMap<String, String> treeMap) {
        TreeMap<String, String> treeMap1 = new TreeMap<>();
        for (String s : treeMap1.keySet()) {
            String s1 = treeMap1.get(s);
            byte[] encode = Base64.encode(s1.getBytes(), Base64.DEFAULT);
            String s2 = new String(encode);
            treeMap1.put(s, s2);
        }
        return treeMap1;
    }
}
