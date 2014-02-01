package com;

import java.util.ArrayList;
import java.util.List;

public class Assigment01Attempt03Test {

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
	
	/**
	 * Convert a list of integer to a String of its ASCII representation.
	 * @param hexadecimalList
	 * @return
	 */
	public static final String integersListToString(List<Integer> decimalList) {
		String result = "";
		for (Integer integer : decimalList) {
			if (integer!=null) {
				int i = (int)integer;
				result += Character.toString((char) i);
			} else {
				result += "_";
			}
		}
		return result;
	}
	public static final String integersListToHexadecimalStr(List<Integer> decimalList) {
		String result = "";
		for (Integer integer : decimalList) {
			if (integer!=null) {
				result += Integer.toString(integer, 16);
			} else {
				result += "_";
			}
		}
		return result;
	}
	
	/**
	 * Convert a String in a integer list.
	 * @param str
	 * @return
	 */
	public static final List<Integer> strToIntegerList(String str) {
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < str.length(); i++) {
			int charAt = str.charAt(i);
			result.add(charAt);
		}
		return result;
	}
	
	public static String strHexadecimalToAscii(String input) {
		String output = "";
		for(int i=0; i<input.length(); i=i+2) {
			String subStrEncription = input.substring(i, i+2);
			Character ch = new Character((char)Integer.parseInt(subStrEncription, 16));
			output += ch.toString();
		}		
		return output;
	}	
	public static List<Integer> strHexadecimalToList(String strHexadecimal) {
		List<Integer> result = new ArrayList<Integer>();
		for(int i=0; i<strHexadecimal.length(); i=i+2) {
			int char1 = Integer.parseInt(strHexadecimal.substring(i, i+2), 16);
			result.add(char1);
		}		
		return result;
	}	
	
	
	
	public static void main(String[] args) {
		String myKey = "secreto";
		String myMessage = "abcdABCD";
		List<Integer> myMessageList = Assigment01Attempt03Test.strToIntegerList(myMessage);
		List<Integer> myKeyList = Assigment01Attempt03Test.strToIntegerList(myKey);
		List<Integer> xorIng = Assigment01Attempt03Test.xorIng(myMessageList, myKeyList);
		System.out.println(xorIng);
		
		String hexadecimalStr = Assigment01Attempt03Test.integersListToHexadecimalStr(myMessageList);
		System.out.println(hexadecimalStr);
		String toAscii = Assigment01Attempt03Test.strHexadecimalToAscii(hexadecimalStr);
		System.out.println(toAscii);
		List<Integer> strHexadecimalToList = Assigment01Attempt03Test.strHexadecimalToList(hexadecimalStr);
		System.out.println(Assigment01Attempt03Test.integersListToHexadecimalStr(strHexadecimalToList));
		
	}
	
}
