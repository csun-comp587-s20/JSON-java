package com.test.JSON_java;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

/*this class can be useful in getting coverage for all of the put function that take
 * different types as inputs.
 */

public class KeyandValuePairGenerator<E> {

	//Keys are always strings
	private String key;
	
	//Values can be of the following types:
	//boolean, collection, map, double, int, float, long, object
	private boolean boolValue;
	private List<E> listValue;
	private HashMap<E,E> hashmapValue;
	private double doubleValue;
	private int intValue;
	private float floatValue;
	private long longValue;
	private Object objectValue;
	private String stringValue;
	
	KeyandValuePairGenerator() {
		key = "";
		boolValue = false;
		//private List<E> listValue;
		//private HashMap<E,E> hashmapValue;
		doubleValue = 0.0;
		intValue = 0;
		floatValue = 0;
		longValue = 0;
		//private Object objectValue;
		stringValue = "";
	}
	
	//generate functions
	public void generateKey(int length) {
		Random rnd = new Random();
		 char c;
		 for (int i = 0; i < length; i++) {
			 c = (char) (rnd.nextInt(26) + 'a');
			 key += c;
		 }
	}
	
	public void generateStringValue(int length) {
		Random rnd = new Random();
		 char c;
		 for (int i = 0; i < length; i++) {
			 c = (char) (rnd.nextInt(26) + 'a');
			 key += c;
		 }
	}
	
	//is this necessary or should I just use random in the specific test class?
	public void generateBoolValue() {
		Random rnd = new Random();
		boolValue = rnd.nextBoolean();
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
}
