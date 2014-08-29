package com;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class Test {
	
	public static final String ENCRYPTED_TEXT = "f20bdba6ff29eed7b046d1df9fb7000058b1ffb4210a580f748b4ac714c001bd4a61044426fb515dad3f21f18aa577c0bdf302936266926ff37dbf7035d5eeb4";

	public static void main(String[] args) throws IOException {
		int blocksNumber = ENCRYPTED_TEXT.length()/32;
		for (int i=blocksNumber; i>0; i--) {			
			String block = ENCRYPTED_TEXT.substring(i*32 - 32, i*32);
			System.out.println(block);
			System.out.println(block.length());
			findByte(16, block);

		}
	}
	
	public static byte findByte (int byteNumber, String block) throws IOException {
		String initialBlock = "000000000000000000000000000000xx";
		boolean continueFor = true;
		for (int i = 0; (i < 256 && continueFor); i++) {
			String testStr = initialBlock.replace("xx", String.format("%02X", i));
			if (isCorrectPadding( testStr + block)) {
				System.out.println("correcto: " + i + " - " + testStr );
				continueFor = false;
			}
			if (i%20==0) {
				System.out.println("pasando i" + i);
			}
		}
		return -1;
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
	
	
}
