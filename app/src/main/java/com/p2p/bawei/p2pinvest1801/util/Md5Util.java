package com.p2p.bawei.p2pinvest1801.util;

import android.util.Base64;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.TreeMap;

public class Md5Util  {
    /*
     * java计算文件32位md5值
     * @param fis 输入流
     * @return
     */
    public static String md5HashCode32(InputStream fis) {
        try {
            //拿到一个MD5转换器,如果想使用SHA-1或SHA-256，则传入SHA-1,SHA-256
            MessageDigest md = MessageDigest.getInstance("MD5");

            //分多次将一个文件读入，对于大型文件而言，比较推荐这种方式，占用内存比较少。
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md.update(buffer, 0, length);
            }
            fis.close();

            //转换并返回包含16个元素字节数组,返回数值范围为-128到127
            byte[] md5Bytes  = md.digest();
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;//解释参见最下方
                if (val < 16) {
                    /*
                     * 如果小于16，那么val值的16进制形式必然为一位，
                     * 因为十进制0,1...9,10,11,12,13,14,15 对应的 16进制为 0,1...9,a,b,c,d,e,f;
                     * 此处高位补0。
                     */
                    hexValue.append("0");
                }
                //这里借助了Integer类的方法实现16进制的转换
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

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
            if (value!=null){
                byte[] encode = Base64.encode(value.getBytes(), Base64.DEFAULT);
                String s = new String(encode);
                stringStringTreeMap.put(key,s);
            }
        }
        return stringStringTreeMap;
    }

}
