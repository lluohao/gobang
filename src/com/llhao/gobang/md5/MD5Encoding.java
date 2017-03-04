package com.llhao.gobang.md5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encoding {
	public static String encoding(String str){
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] mdData = md.digest(str.getBytes());
			char[] charArr = "0123456789ABCDEF".toCharArray();
			StringBuilder sb = new StringBuilder();
			for (byte b : mdData) {
				sb.append(charArr[b>>>4&0x0f]);
				sb.append(charArr[b&0x0f]);
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.println(MD5Encoding.encoding("sttm123457"));
	}
}
