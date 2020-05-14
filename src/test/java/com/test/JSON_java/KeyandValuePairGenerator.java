package com.test.JSON_java;

import java.util.Random;

/*this class can be useful in getting coverage for all of the put function that take
 * different types as inputs.
 */

public class KeyandValuePairGenerator {

	//Keys are always strings
	private String key;
	
	//Values can be of the following types:
	//boolean, collection, map, double, int, float, long, object
	private boolean boolValue;
	private double doubleValue;
	private int intValue;
	private float floatValue;
	private long longValue;
	private Object objectValue;
	private String stringValue;
	
	KeyandValuePairGenerator() {
		key = "";
		boolValue = false;
		doubleValue = 0.0;
		intValue = 0;
		floatValue = 0;
		longValue = 0;
		Object objectValue;
		stringValue = "";
	}
	
	private String generateRandomString(int length) {
		Random rnd = new Random();
		 char c;
		 String s1 = "";
		 for (int i = 0; i < length; i++) {
			 c = (char) (rnd.nextInt(26) + 'a');
			 s1 += c;
		 }
		 return s1;
	}
	
	//public generate functions
	public void generateNewKey(int length) {
		key = generateRandomString(length);
	}
	
	public void generateNewStringValue(int length) {
		stringValue = generateRandomString(length);
	}
	
	public void generateNewBoolValue() {
		Random rnd = new Random();
		boolValue = rnd.nextBoolean();
	}
	
	public void generateNewIntValue() {
		int floor = 0, ceiling = 100;
		Random rnd = new Random();
		intValue = rnd.nextInt(ceiling - floor) + floor;
	}
	
	public void generateNewIntValue(int ceiling) {
		int floor = 0;
		Random rnd = new Random();
		intValue = rnd.nextInt(ceiling - floor) + floor;
	}
	
	public void generateNewIntValue(int floor, int ceiling) {
		Random rnd = new Random();
		intValue = rnd.nextInt(ceiling - floor) + floor;
	}
	
	//getter functions
	public String getKey() {
		return key;
	}
	
	public String getStringValue() {
		return stringValue;
	}
	
	public boolean getBoolValue() {
		return boolValue;
	}
	
	public int getIntValue() {
		return intValue;
	}
}