package com.assignment;

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

public class CopyOfQuestion01 {

	public static void main(String[] args) throws NoSuchAlgorithmException,
			NoSuchPaddingException, IOException, InvalidKeyException, InvalidAlgorithmParameterException {
		String key = "140b41b22a29beb4061bda66b6747e14";
		String cipherText = "4ca00ff4c898d61e1edbf1800618fb2828a226d160dad07883d04e008a7897ee2e4b7465d5290d0c0e6c6822236e1daafb94ffe0c5da05d9476be028ad7c1d81";

		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128);
		SecretKey aesKey = kgen.generateKey();
		
		
		
		Cipher encryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	    encryptCipher.init(Cipher.ENCRYPT_MODE, aesKey);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, encryptCipher);
		cipherOutputStream.write("hola".getBytes());
		cipherOutputStream.flush();
		cipherOutputStream.close();
		byte[] encryptedBytes = outputStream.toByteArray();
		String string = new String(encryptedBytes);
		System.out.println(string);
		
		// Decrypt
		Cipher decryptCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		//IvParameterSpec ivParameterSpec = new IvParameterSpec(aesKey.getEncoded());
		byte[] iv = encryptCipher.getIV();
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
		
		decryptCipher.init(Cipher.DECRYPT_MODE, aesKey, ivParameterSpec);
		
	    outputStream = new ByteArrayOutputStream();
	    ByteArrayInputStream inStream = new ByteArrayInputStream(encryptedBytes);
	    CipherInputStream cipherInputStream = new CipherInputStream(inStream, decryptCipher);
	    byte[] buf = new byte[1024];
	    int bytesRead;
	    while ((bytesRead = cipherInputStream.read(buf)) >= 0) {
	        outputStream.write(buf, 0, bytesRead);
	    }
	    System.out.println("Result: [" + new String(outputStream.toByteArray()) + "]");

	}

}
