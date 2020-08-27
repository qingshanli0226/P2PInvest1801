package com.p2p.bawei.p2pinvest1801.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

public class Md5Util  {


    //输入字符串获取摘要信息
    public static String ByMd5(String message){
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(message.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer(hash.length * 2);

        for (byte b : hash){
            if ((b & 0xFF ) < 0x10){
                stringBuffer.append("0");
                stringBuffer.append(Integer.toHexString(b&0xFF));
            }
        }
        return stringBuffer.toString();
    }

    //对参数的value进行base64加密
    public static TreeMap<String,String> ValueBase64(TreeMap<String,String> map){

        TreeMap<String, String> stringStringTreeMap = new TreeMap<>();
        for (String key:map.keySet()){
            String value = map.get(key);
            byte[] encode = Base64.encode(value.getBytes(), Base64.DEFAULT);
            String s = new String(encode);
            stringStringTreeMap.put(key,s);
        }
        return stringStringTreeMap;
    }

}
