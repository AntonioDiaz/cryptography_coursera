package com;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class ProgrammingAssignment {

	public static final String PATH_FILE = "/Users/toni/git/cryptography_coursera/week_02_block_cipher/src/input.txt";
	
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IOException {
		Security.addProvider(new BouncyCastleProvider());
		String myKey = ProgrammingAssignment.readLine(1, PATH_FILE);
		String myCiphedText = ProgrammingAssignment.readLine(2, PATH_FILE);
		String result = decryptCBC(myKey, myCiphedText);
		System.out.println("Result: [" + result + "]");
		
		myKey = ProgrammingAssignment.readLine(5, PATH_FILE);
		myCiphedText = ProgrammingAssignment.readLine(6, PATH_FILE);
		result = decryptCBC(myKey, myCiphedText);
	    System.out.println("Result: [" + result + "]");
	    
	    myKey = ProgrammingAssignment.readLine(9, PATH_FILE);
	    myCiphedText = ProgrammingAssignment.readLine(10, PATH_FILE);
	    result = decryptCTR(myKey, myCiphedText);
	    System.out.println("Result: [" + result + "]");
	    
	    myKey = ProgrammingAssignment.readLine(13, PATH_FILE);
	    myCiphedText = ProgrammingAssignment.readLine(14, PATH_FILE);
	    result = decryptCTR(myKey, myCiphedText);
	    System.out.println("Result: [" + result + "]");
	}

	private static String decryptCTR(String myKey, String myCiphedText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IOException {
		byte[] myKeyBytes = strHexadecimalToAscii(myKey);
		byte[] myCiphedTextBytes = strHexadecimalToAscii(myCiphedText);
		byte[] myIvBytes = Arrays.copyOfRange(myCiphedTextBytes, 0, 16);
		myCiphedTextBytes = Arrays.copyOfRange(myCiphedTextBytes, 16, myCiphedText.length());		
		SecretKey secretKey = new SecretKeySpec(myKeyBytes, "AES");
		Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding", new BouncyCastleProvider());
		IvParameterSpec ivParameterSpec = new IvParameterSpec(myIvBytes);
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    ByteArrayInputStream inStream = new ByteArrayInputStream(myCiphedTextBytes);
	    CipherInputStream cipherInputStream = new CipherInputStream(inStream, cipher);
	    byte[] buf = new byte[1024];
	    int bytesRead;
	    while ((bytesRead = cipherInputStream.read(buf)) >= 0) {
	        outputStream.write(buf, 0, bytesRead);
	    }
	    String result = new String(outputStream.toByteArray());
	    cipherInputStream.close();
	    return result;
	}
	
	private static String decryptCBC(String myKey, String myCiphedText)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IOException {
		byte[] myKeyBytes = strHexadecimalToAscii(myKey);
		byte[] myCiphedTextBytes = strHexadecimalToAscii(myCiphedText);
		byte[] myIvBytes = Arrays.copyOfRange(myCiphedTextBytes, 0, 16);
		myCiphedTextBytes = Arrays.copyOfRange(myCiphedTextBytes, 16, myCiphedText.length());		
		SecretKey secretKey = new SecretKeySpec(myKeyBytes, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec ivParameterSpec = new IvParameterSpec(myIvBytes);
		cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    ByteArrayInputStream inStream = new ByteArrayInputStream(myCiphedTextBytes);
	    CipherInputStream cipherInputStream = new CipherInputStream(inStream, cipher);
	    byte[] buf = new byte[1024];
	    int bytesRead;
	    while ((bytesRead = cipherInputStream.read(buf)) >= 0) {
	        outputStream.write(buf, 0, bytesRead);
	    }
	    String result = new String(outputStream.toByteArray());
	    cipherInputStream.close();
	    return result;	    
	}
	
	
	public static String readLine (int line, String myPath) throws FileNotFoundException {
		String myStr = null;
		Scanner scanner = new Scanner (new File(myPath));
		for (int i = 0; i <= line; i++) {
			myStr = scanner.nextLine();  			
		}
		scanner.close();
		return myStr;
	}
	
	public static byte[] strHexadecimalToAscii(String input) {
		byte[] salida = new byte[input.length()/2]; 
		for(int i=0; i<input.length(); i=i+2) {
			String subStrEncription = input.substring(i, i+2);
			salida[i/2] = (byte)Integer.parseInt(subStrEncription, 16);
		}		
		return salida;
	}	
	

}
