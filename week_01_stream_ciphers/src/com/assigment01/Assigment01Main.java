package com.assigment01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Assigment01Main {

	private static final String MY_PATH = "/home/toni/workspace_kripto/01_week_many_time_pad/messages/";
	private static final String MY_FILE_NAME = "_ciphertext.txt";
	private static final int NUM_TEXT = 11;

	
	public static void main(String[] args) throws FileNotFoundException {
		MessageEncrypted[] messages = new MessageEncrypted[11];
		int maxSize = 0;
		for (int i = 0; i < NUM_TEXT; i++) {
			String name = String.format("%1$02d", i) + MY_FILE_NAME;
			String myText = new Scanner(new File(MY_PATH + name)).useDelimiter("\\A").next();;
			messages[i] = new MessageEncrypted(myText);
			if (maxSize < myText.length()) {
				maxSize = myText.length();
			}
		}
		for (int i = 0; i < NUM_TEXT; i++) {
			System.out.println(messages[i]);
		}
		Key key = new Key(maxSize);
		/* search for spaces. */
		int[] spacesCount;
		for (int i = 0; i < NUM_TEXT; i++) {
			spacesCount = new int[maxSize];
			List<Integer> list1 = messages[i].generateList();
			for (int j = 0; j < NUM_TEXT; j++) {
				if (i!=j) {
					List<Integer> list2 = messages[j].generateList();
					System.out.println("comparando " + i + " con " + j);
					List<Integer> xorIng = xorIng(list1, list2);					
					System.out.println(integersListToHexadecimalStr(xorIng));
					int minSize = (list1.size()<list2.size()) ? list1.size() : list2.size();
					for (int k=0; k<minSize; k++) {
						int xored = list1.get(k) ^ list2.get(k);
						String strHexadecimal = Integer.toString(xored, 16);
						if (strHexadecimal.length()==1) {
							strHexadecimal = "0" + strHexadecimal;
						}
						char charAt = strHexadecimal.charAt(0);
						if (charAt=='4' || charAt=='5' || charAt=='6' || charAt=='7'){
							spacesCount[k]++;
						}
					}
				}
			}
			for (int j = 0; j < maxSize; j++) {
				if (spacesCount[j]>NUM_TEXT-5) {
					key.addCharToKey(j, 32^list1.get(j));
					System.out.println("espacion " + i + ":" + key.getCharsKey());
				}
			}
		}
		key.addCharToKey(2, 110);
		key.addCharToKey(7, 204);
		key.addCharToKey(14, 149);
		key.addCharToKey(21, 127);
		key.addCharToKey(25, 127);
		key.addCharToKey(35, 154);
		key.addCharToKey(36, 25);
		key.addCharToKey(49, 99);
		key.addCharToKey(54, 72);
		
		for (int i = 0; i < NUM_TEXT; i++) {
			System.out.println("[" + messages[i].deCryptMsg(key) + "]");
		}
		
	}
	
	
	public static List<Integer> xorIng(List<Integer> m, List<Integer> k) {
		List<Integer> result = new ArrayList<Integer>();
		int minSize = m.size()<k.size() ? m.size() : k.size();
		for (int i=0; i<minSize; i++) {
			Integer myInt = null;
			if (m.get(i)!=null && k.get(i)!=null) {
				myInt = m.get(i) ^ k.get(i);
			}
			result.add(myInt);
		}
		for (int i=minSize; i<m.size(); i++) {
			result.add(null);
		}
		return result;
	}	

	public static final String integersListToHexadecimalStr(List<Integer> decimalList) {
		String result = "";
		for (Integer integer : decimalList) {
			if (integer!=null) {
				String string = Integer.toString(integer, 16);
				if (string.length()==1) {
					string = "0" + string;
				}
				result += string + ",";
			} else {
				result += "_";
			}
		}
		return result;
	}
	
}
