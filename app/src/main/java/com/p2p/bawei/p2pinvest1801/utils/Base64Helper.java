package com.p2p.bawei.p2pinvest1801.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Base64Helper {

	//base64编码原文是否是6的整数倍？末尾几个“=”号？
	
	
	public static String encode(byte[] data) {
		return Base64.encodeToString(data, Base64.DEFAULT);
	}

	public static byte[] decode(String str) {
		return Base64.decode(str, Base64.DEFAULT);
	}

	//对于url当中的中文字符进行编码
	public static String encodeUrl(String url) throws UnsupportedEncodingException {
		String path = URLEncoder.encode(url, "utf-8");
		return path;

//		String decode = URLDecoder.decode(path, "utf-8");

	}

}
