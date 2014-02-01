package com.cryptography;

import java.io.UnsupportedEncodingException;

public class XorEncryption {

	
	/**
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String operaXor (String strHexa1, String strHexa2) {
		String result = ""; 
		if (strHexa1.length() == strHexa2.length()) {
			for (int i=0; i<strHexa1.length(); i++) {				
				int char1 = Integer.parseInt(strHexa1.substring(i, i+1), 16);
				int char2 = Integer.parseInt(strHexa2.substring(i, i+1), 16);
				result += Integer.toString((char1 ^ char2), 16);;
			}
		}
		return result;
	}
	
	public static String strAsciiToHexadecimal(String input) {
		String output = "";
		for(int i=0; i<input.length(); i++) {
			Integer integer = (int)input.charAt(i);
			output += Integer.toString(integer, 16);
		}		
		return output;
	}

	public static String strHexadecimalToAscii(String input) {
		String output = "";
		for(int i=0; i<input.length(); i=i+2) {
			String subStrEncription = input.substring(i, i+2);
			Character ch = new Character((char)Integer.parseInt(subStrEncription, 16));
			output += ch.toString();
		}		
		return output;
	}
	
	
	
	public static void main(String[] args) {
		String m1 = "attack at dawn";
		String m2 = "attack at dusk";		
		String m1Encrypted = "6c73d5240a948c86981bc294814d";
		String m1Hexadecimal = XorEncryption.strAsciiToHexadecimal(m1);
		String m2Hexadecimal = XorEncryption.strAsciiToHexadecimal(m2);
		String myKey = XorEncryption.operaXor(m1Hexadecimal, m1Encrypted);
		String m2Encrypted = XorEncryption.operaXor(m2Hexadecimal, myKey);
		System.out.println(m2Encrypted);
		System.out.println(XorEncryption.strHexadecimalToAscii(XorEncryption.operaXor(m2Encrypted, myKey)));
		
		
		
		
	}
	
	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void mainAux(String[] args) throws UnsupportedEncodingException {
		String message01 = "attack at dawn";
		String message01Ex = "";
		String message02 = "attack at dusk";
		String encryption01 = "09e1c5f70a65ac519458e7e53f36";
		System.out.println(message01.getBytes("UTF-8"));
		//System.out.println(String.format("%040x", message01.getBytes("UTF-8")));
		for(int i=0; i<message01.length(); i++) {
			Character character = new Character(message01.charAt(i));
			Integer integer = (int)message01.charAt(i);
			message01Ex += Integer.toString(integer, 16);
			//System.out.println((byte)message01.charAt(i));		
		}
		
		for(int i=0; i<message01Ex.length(); i=i+2) {
			String subStrMsg = message01Ex.substring(i, i+2);
			String subStrEncription = encryption01.substring(i, i+2);
			
			Integer byteMsg = Integer.parseInt(subStrMsg, 16);
			Integer byteEncription = Integer.parseInt(subStrEncription, 16);
			Integer key = byteMsg ^ byteEncription;
			System.out.println(key);
		}
		System.out.println(encryption01);
		System.out.println(message01Ex);
		System.out.println(message01Ex.length());
	}

}

