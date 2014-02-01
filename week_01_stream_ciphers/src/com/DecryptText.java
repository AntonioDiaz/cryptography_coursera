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

public class DecryptText {

	private static final String MY_PATH = "/home/toni/workspace_kripto/01_week_many_time_pad/messages/";
	private static final String MY_FILE_NAME = "_ciphertext.txt";
	private static final int NUM_TEXT = 11;
	
	
	
	public static void main(String[] args) throws FileNotFoundException{
		Map<Integer, Integer> letters = null;
		List<List<Integer>> lettersList = null;
		
		for (int i = 0; i < NUM_TEXT; i++) {
			String name = String.format("%1$02d", i) + MY_FILE_NAME;
			System.out.println(name);
			String myText = new Scanner(new File(MY_PATH + name)).useDelimiter("\\A").next();;
			//System.out.println(myText);
			letters = new Hashtable<Integer, Integer>();
			lettersList = new ArrayList<List<Integer>>();
			for (int j=0; j<myText.length(); j=j+2) {
				System.out.print(myText.substring(j, j+2) + ",");
				Integer myInt = Integer.parseInt(myText.substring(j, j+2), 16);
				//System.out.println(myInt);
				if (letters.containsKey(myInt)) {
					letters.put(myInt, letters.get(myInt) + 1);
				} else {
					letters.put(myInt, 1);
				}
			}
			System.out.println("");
			//System.out.println(letters);
			for (int j = 0; j < letters.size(); j++) {
				lettersList.add(null);
			}
			for (Entry<Integer, Integer> entry : letters.entrySet()) {
				if (lettersList.get(entry.getValue())==null) {
					List<Integer> listaAux = new ArrayList<Integer>();
					listaAux.add(entry.getKey());
					lettersList.add(entry.getValue(), listaAux);
				} else {
					lettersList.get(entry.getValue()).add(entry.getKey());
				}
			}
			//System.out.println(lettersList.size());
			for (int j = lettersList.size() -1; j>=0; j--) {
				if (lettersList.get(j)!=null){
					System.out.print(j + "-");					
					for (Integer myInt : lettersList.get(j)) {
						System.out.print("[" + Integer.toHexString(myInt) + "]");
					}
					System.out.println("");
				}
			}
		}
	}
	
}
