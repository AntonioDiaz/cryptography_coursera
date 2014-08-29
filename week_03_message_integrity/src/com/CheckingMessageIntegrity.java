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

	/**
	 * 
	 * @param args
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws CloneNotSupportedException
	 */
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException, CloneNotSupportedException {
		File file = new File(EXERCISE_VIDEO);
		int blocksNumber = (int)file.length() / BLOCK_SIZE;
		byte[] previousHsa = new byte[0];
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		for (int i = blocksNumber; i >=0; i--) {
			messageDigest.reset();
			long begin = i * BLOCK_SIZE;
			int size = BLOCK_SIZE;
			if (begin + size > file.length()) {
				size = (int)file.length() - (int)begin;
			}
			byte[] readFromFile = readFromFile(TRAINING_VIDEO, begin, size);
			readFromFile = ArrayUtils.addAll(readFromFile, previousHsa);
			messageDigest.update(readFromFile);
			previousHsa = messageDigest.digest();
		}
	}

	private static byte[] readFromFile(String filePath, long position, int size) throws IOException {
		RandomAccessFile file = new RandomAccessFile(filePath, "r");
		file.seek(position);
		byte[] bytes = new byte[size];
		file.read(bytes);
		file.close();
		return bytes;
	}
	
	public static String bytesToHexadecimal(byte[] myBytes) {
		StringBuilder cadena = new StringBuilder();
		for (byte b : myBytes) {
			cadena.append(String.format("%02X", b));
		}
		return cadena.toString();
	}
		
}
