package com.test.JSON_java;

import java.util.Random;

public class JSONFormatStringGenerator {
	
	private String stringWithQuotes;
	private String stringWithoutQuotes;
	private String JSONFormatString;
	
	//when testing testJSONTokenerNext also need a string without the brackets for comparing too
	public JSONFormatStringGenerator() {
		stringWithQuotes = "";
		stringWithoutQuotes = "";
		JSONFormatString = "";
	}
	
	public void genStrings(int length) {
		//clear out existing strings
		stringWithQuotes = "";
		stringWithoutQuotes = "";
		JSONFormatString = "";
		
		Random rnd = new Random();
		 char c;
		 JSONFormatString += "[";
		 JSONFormatString += "\"";
		 stringWithQuotes += "\""; 
		 for (int i = 0; i < length; i++) {
			 c = (char) (rnd.nextInt(26) + 'a');
			 JSONFormatString += c;
			 stringWithoutQuotes += c;
			 stringWithQuotes += c;
		 }
		 JSONFormatString += "\"";
		 stringWithQuotes += "\""; 
		 JSONFormatString += "]";
	}
	
	public void genRandomLengthStrings(int maxLength) {
		Random rnd = new Random();
		int length = rnd.nextInt(maxLength);
		genStrings(length);
	}
	
	public String getStringWithQuotes() {
		return stringWithQuotes;
	}
	
	public String getStringWithoutQuotes() {
		return stringWithoutQuotes;
	}
	
	public String getJSONFormatString() {
		return JSONFormatString;
	}

}
