package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * 
 * @author antonio.diaz.arroyo@gmail.com
 *
 */

public class Assigment01Attempt02 {

	private static final String MY_PATH = "/home/toni/workspace_kripto/01_week_many_time_pad/messages/";
	private static final String MY_FILE_NAME = "_ciphertext.txt";
	private static final int NUM_TEXT = 11;
	
	
	public static void main(String[] args) throws FileNotFoundException {
		List<String> textList = new ArrayList<String>();
		int maxLength = 0;
		 
		for (int i = 0; i < NUM_TEXT; i++) {
			String name = String.format("%1$02d", i) + MY_FILE_NAME;
			String myText = new Scanner(new File(MY_PATH + name)).useDelimiter("\\A").next();;
			textList.add(myText);
			if (myText.length()>maxLength) {
				maxLength = myText.length();
			}
		}
		
		int[] keys = new int[maxLength];
		/** busca los espacios en blancos */
		 for (int i = 0; i < textList.size(); i++) {
			int[]founds = new int[maxLength];
			for (int j = 0; j < textList.size(); j++) {
			}
		 }
		
		/*		
		 for (int i = 0; i < textList.size(); i++) {
			for (int j = i + 1; j < textList.size(); j++) {
				String myXored = Assigment01Attemp02.xorIng(textList.get(i), textList.get(j));
				System.out.print(i+ "-" + j + ":");
				for (int k = 0; k < myXored.length(); k=k+2) {
					System.out.print(myXored.substring(k, k+2) + ",");
				}
				System.out.println("");
			}
		}*/
	}

	
	private static void searchSpaces(String text1, String text2, int[] keys) {
		String result = "";
		int minSize = text1.length()<text2.length()?text1.length():text2.length();
		int[]founds = new int[minSize];
		for (int i=0; i<minSize; i++) {
			int char1 = Integer.parseInt(text1.substring(i, i+1), 16);
			int char2 = Integer.parseInt(text2.substring(i, i+1), 16);
			result += Integer.toString((char1 ^ char2), 16);
			if (char1==4 || char1==5 || char1==6 || char1==7) {
				founds[i]++;
			}
		}
		System.out.println(founds);
		for (int i = 0; i < founds.length; i++) {
			if (founds[i]>=5) {
				keys[i] = 3;
				keys[i + 1] = 0;
			}
		}
	}
	
	
	
	private static String xorIng(String text1, String text2) {
		String result = "";
		
		int minSize = text1.length()<text2.length()?text1.length():text2.length();
		for (int i=0; i<minSize; i++) {
			int char1 = Integer.parseInt(text1.substring(i, i+1), 16);
			int char2 = Integer.parseInt(text2.substring(i, i+1), 16);
			result += Integer.toString((char1 ^ char2), 16);
		}
		return result;
	}
	
}
