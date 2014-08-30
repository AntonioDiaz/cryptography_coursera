package com;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;


public class PaddingOracleAttack {
	
	public static final String ENCRYPTED_TEXT = "f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa577c0bdf302936266926ff37dbf7035d5eeb4";

	/** The Magic Words are Squeamish Ossifrage */
	public static void main(String[] args) throws IOException {
		int blocksNumber = ENCRYPTED_TEXT.length()/32;
		String previousBlock = null;
		String decryptedText = "";
		for (int i=0; i<blocksNumber; i++) {			
			String block = ENCRYPTED_TEXT.substring(i*32, i*32 + 32);
			if (i>=1) {
				decryptedText += decryptBlockBytes(previousBlock, block);
				System.out.println("[" + decryptedText + "]");
			}
			previousBlock = block;
		}
	}
	
	
	public static String decryptBlockBytes (String blockPrevious, String block) throws IOException {
		byte[] blockPreviousArray = strHexadecimalToDecimal(blockPrevious);
		List<Byte> plainText = new ArrayList<Byte>(); 
		List<Byte> intermediate = new ArrayList<Byte>(); 
		for (int i = 0; i<16; i++) {
			List<Byte> list = new ArrayList<Byte>();
			for (int k=0; k<i; k++) {
				list.add((byte)(intermediate.get(k) ^ (i+1)));
			}
			boolean continueFor = true;
			for (int j = 0; (j < 256 && continueFor); j++) {
				list.add(0, (byte)j);
				String temp = listBytesToStrHexadeciaml(list);
				temp = StringUtils.leftPad(temp, 32, '0');
				if (isCorrectPadding(temp + block)) {
					continueFor = false;
					byte i2 = (byte)(j ^ (i+1));
					intermediate.add(0, i2);
					byte p = (byte) (blockPreviousArray[16-1-i] ^ i2);
					plainText.add(0, p);
					System.out.println("\n" + arrayBytesToAsciiStr(plainText));	
				} 
				list.remove(0);
				if (j%20==0) {
					System.out.print(" " + j);
				}				
			}
		}
		return arrayBytesToAsciiStr(plainText);
	}

	public static boolean isCorrectPadding(String cypherText) throws IOException {
		final String url = "http://crypto-class.appspot.com/po?er=";
		URL obj = new URL(url + cypherText);
		HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
		conn.setReadTimeout(5000);
		conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
		conn.addRequestProperty("User-Agent", "Mozilla");
		conn.addRequestProperty("Referer", "google.com");
		int status = conn.getResponseCode();
		return status != HttpURLConnection.HTTP_FORBIDDEN; 		
	}

	
	public static byte[] strHexadecimalToDecimal(String input) {
		byte[] salida = new byte[input.length()/2]; 
		for(int i=0; i<input.length(); i=i+2) {
			String subStrEncription = input.substring(i, i+2);
			salida[i/2] = (byte)Integer.parseInt(subStrEncription, 16);
		}		
		return salida;
	}
	
	public static String listBytesToStrHexadeciaml (List<Byte> bytes) {		
		return arrayBytesToStrHexadecimal(ArrayUtils.toPrimitive(bytes.toArray(new Byte[bytes.size()])));
	}
	
	public static String arrayBytesToStrHexadecimal (byte[] bytes) {
		String strHexadecimal = "";
		for(int i=0; i<bytes.length; i++) {
			strHexadecimal += String.format("%02X", bytes[i]);			
		}		
		return strHexadecimal;
	}
	
	public static String arrayBytesToAsciiStr(List<Byte> list) {
		return arrayBytesToAsciiStr(ArrayUtils.toPrimitive(list.toArray(new Byte[list.size()])));
	}
	
	public static String arrayBytesToAsciiStr(byte[] bytes) {
		String strHexadecimal = "";
		for(int i=0; i<bytes.length; i++) {
			Character ch = new Character((char)bytes[i]);
			strHexadecimal += ch.toString();			
		}		
		return strHexadecimal;
	}
	
	
}
