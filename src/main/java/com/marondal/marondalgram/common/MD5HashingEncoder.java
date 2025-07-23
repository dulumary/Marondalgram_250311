package com.marondal.marondalgram.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5HashingEncoder {
	
	// md5 를 통한 암호화 
	public static String encode(String message) {
		
	 	try {
			MessageDigest messageDigest = MessageDigest.getInstance("md5");
			
			byte[] bytes = message.getBytes();
			
			messageDigest.update(bytes);
			
			byte[] digest = messageDigest.digest();
			
			String result = "";
			for(int i = 0; i < digest.length; i++) {
				// 01010111
				// 10101110
				// byte 연산
				result += Integer.toHexString(digest[i] & 0xff);
			}
			
			return result;
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
			
			return null;
		}
	 	
	 	
	}

}
