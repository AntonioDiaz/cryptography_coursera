package com;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.*;

public class CheckingMessageIntegrity {

	public static final String VIDEO_PATH = "C:/Users/toni/git/cryptography_coursera03/week_03_message_integrity/videos/";
	public static final String TRAINING_VIDEO = VIDEO_PATH + "training_video.mp4";
	public static final String EXERCISE_VIDEO = VIDEO_PATH + "exercise_video.mp4";
	public static final String OUTPUT = "C:/Users/toni/git/cryptography_coursera03/week_03_message_integrity/output/output.txt";
	public static final int BLOCK_SIZE = 1024;

	public static String bytesToHexadecimal(byte[] myBytes) {
		StringBuilder cadena = new StringBuilder();
		for (byte b : myBytes) {
			cadena.append(String.format("%02X", b));
		}
		return cadena.toString();
	}
	
	/**
	 * 
	 * @param args
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws CloneNotSupportedException
	 */
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, CloneNotSupportedException {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String output = OUTPUT.replaceAll(".txt", dateFormat.format(new Date()) + ".txt");
		PrintWriter printWriter = new PrintWriter(output);
		File file = new File(EXERCISE_VIDEO);
		System.out.println(file.length());
		printWriter.println(file.length());
		int blocksNumber = (int)file.length() / BLOCK_SIZE;
		System.out.println("Bloques: " + blocksNumber + "\n");
		printWriter.println("Bloques: " + blocksNumber + "\n");
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte[] previousHsa = new byte[0];
		for (int i = blocksNumber; i >=0; i--) {
			long begin = i * BLOCK_SIZE;
			int size = BLOCK_SIZE;
			if (begin + size > file.length()) {
				size = (int)file.length() - (int)begin;
			}
			System.out.println("i: " + i);
			printWriter.println("i: " + i);
			byte[] readFromFile = readFromFile(TRAINING_VIDEO, begin, size);
			System.out.println(readFromFile.length + "-" + bytesToHexadecimal(readFromFile));
			printWriter.println(readFromFile.length + "-" + bytesToHexadecimal(readFromFile));
			readFromFile = ArrayUtils.addAll(readFromFile, previousHsa);
			messageDigest.update(readFromFile);
			System.out.println(readFromFile.length + "-" + bytesToHexadecimal(readFromFile));
			printWriter.println(readFromFile.length + "-" + bytesToHexadecimal(readFromFile));
			previousHsa = messageDigest.digest();
			System.out.println(previousHsa.length + "-" + bytesToHexadecimal(previousHsa));
			printWriter.println(previousHsa.length + "-" + bytesToHexadecimal(previousHsa));
			System.out.println("----");
			printWriter.println("----");
		}
		System.out.println("Resultado: " + bytesToHexadecimal(previousHsa));		
		printWriter.println("Resultado: " + bytesToHexadecimal(previousHsa));
		printWriter.close();
	}

	private static byte[] readFromFile(String filePath, long position, int size) throws IOException {
		RandomAccessFile file = new RandomAccessFile(filePath, "r");
		file.seek(position);
		byte[] bytes = new byte[size];
		file.read(bytes);
		file.close();
		return bytes;
	}
}
