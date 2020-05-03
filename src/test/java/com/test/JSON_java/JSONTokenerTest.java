package com.test.JSON_java;

import static org.junit.Assert.*;

import org.junit.Test;

public class JSONTokenerTest {
	
	

	@Test
	public void testJSONTokenerNext() {
	    String s1 = "[I am the machine]";
		JSONTokener jt = new JSONTokener(s1);
		for(int i = 0; i < s1.length(); i++) {
			assertEquals(s1.charAt(i), jt.next());
		}
		//return 0 once past end of string
		assertEquals(0, jt.next());
	}
	
	//the following test uses auto generated inputs
	@Test
	public void testJSONTokenerNext_AutoGenInputs() {
		//test 10 random inputs
		int numberOfTests = 10;
		for (int i = 0; i < numberOfTests; i++) {
			JSONFormatStringGenerator generator = new JSONFormatStringGenerator();
			int maxLength = 50;
			//generate random json string with max length
			generator.genRandomLengthStrings(maxLength);
			JSONTokener jt = new JSONTokener(generator.getJSONFormatString());
			for(int k = 0; k < generator.getJSONFormatString().length(); k++) {
				assertEquals(generator.getJSONFormatString().charAt(k), jt.next());
			}
			//return 0 once past end of string
			assertEquals(0, jt.next());
		}
	}
	
	/*
	* @throws JSONException Thrown if trying to step back more than 1 step
    */
	@Test(expected = JSONException.class)
	public void back2Steps( ) {
		String s1 = "[I am the machine]";
		JSONTokener jt = new JSONTokener(s1);
		for (int i = 0; i < 3; i++) {
			jt.next();
		}
		jt.back();
		jt.back();
	}
	
	/* 
	 * JSONException Thrown if trying to step back when at the start of the string
	 */
	@Test(expected = JSONException.class)
	public void backFromStartofString() {
		String s1 = "[I am the machine]";
		JSONTokener jt = new JSONTokener(s1);
		jt.back();
	}

}
