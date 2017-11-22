package org.sentiment.dao;

public class functionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = ".";
		String str2 = getReplacedValue(str);
		System.out.println(str2);
		String[] arrSentences = str2.split("\\.");
		System.out.println(arrSentences.length);
		for(String arrSentence : arrSentences){
			System.out.println(arrSentence);
		}
		//System.out.println(getReplacedValue(str));
	}
	
	public static String getReplacedValue(String str){ 
		return str.replaceAll("\\.+", ".");
	}
}
