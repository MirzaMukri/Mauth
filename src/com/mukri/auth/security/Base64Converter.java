package com.mukri.auth.security;

import java.io.UnsupportedEncodingException;

import javax.xml.bind.DatatypeConverter;


/**
 * CopyRighted by DoomGary / Mukri
 * Please do not edit or copy without permissions.
 * Made on: 1:49:58 AM 
 */

public class Base64Converter {
	
	public static String stringToBase(String password) throws UnsupportedEncodingException {
		byte[] msg = password.getBytes("UTF-8");
		String encodedPass = DatatypeConverter.printBase64Binary(msg);
		
		return encodedPass;
	}
	
	public static String baseToString(String data) throws UnsupportedEncodingException {
		byte[] decoded = DatatypeConverter.parseBase64Binary(data);
		String password = new String(decoded, "UTF-8");
		
		return password;
	}
	
}
