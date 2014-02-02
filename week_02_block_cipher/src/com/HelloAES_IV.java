package com;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class HelloAES_IV {

	public static void main(String[] args) 
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, InvalidAlgorithmParameterException {
		/*
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		SecretKey secretKey = keyGenerator.generateKey();
		*/
		byte[] bytes = "1111111111111111".getBytes();
		SecretKey secretKey = new SecretKeySpec(bytes, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		
		byte[] iv = cipher.getIV();
		IvParameterSpec ivParameterSpec = new IvParameterSpec(bytes);
		
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher);
		cipherOutputStream.write("22222222222222213".getBytes());
		cipherOutputStream.flush();
		cipherOutputStream.close();
		byte[] encryptedBytes = outputStream.toByteArray();
		String string = new String(encryptedBytes);
		System.out.println(string.length());
		System.out.println(HelloAES_IV.strAsciiToHexadecimal(string));
				
		iv = cipher.getIV();
		ivParameterSpec = new IvParameterSpec(iv);
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
		outputStream = new ByteArrayOutputStream();
	    ByteArrayInputStream inStream = new ByteArrayInputStream(encryptedBytes);
	    CipherInputStream cipherInputStream = new CipherInputStream(inStream, cipher);
	    byte[] buf = new byte[1024];
	    int bytesRead;
	    while ((bytesRead = cipherInputStream.read(buf)) >= 0) {
	        outputStream.write(buf, 0, bytesRead);
	    }
	    System.out.println("Result: [" + new String(outputStream.toByteArray()) + "]");
	    cipherInputStream.close();
	}
	
	
	public static String strAsciiToHexadecimal(String input) {
		String output = "";
		for(int i=0; i<input.length(); i++) {
			Integer integer = (int)input.charAt(i);
			output += Integer.toString(integer, 16);
		}		
		return output;
	}

	
	
	
	
}
