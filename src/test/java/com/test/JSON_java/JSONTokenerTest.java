package com.test.JSON_java;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class JSONTokenerTest {

	@Test
	public void testJSONTokener_Next() {
	    String s1 = "[I am the machine]";
		JSONTokener jt = new JSONTokener(s1);
		for(int i = 0; i < s1.length(); i++) {
			assertEquals(s1.charAt(i), jt.next());
		}
		//return 0 once past end of string
		assertEquals(0, jt.next());
	}
	
	@Test
	public void JSONTokener_moreWithCharInput() {
	    String s1 = "[I am the machine]";
		JSONTokener jt = new JSONTokener(s1);
		assertEquals('[',jt.next('['));	
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

	@Test
	public void dehexchar_test() {
		//exhaustive Test of values between 0 and 9
		for (int i = 0; i < 10; i++) {
			int REDIX=16;//redix 16 is for Hexadecimal value   
			char c= Character.forDigit(i,REDIX);    
			assertEquals(i, JSONTokener.dehexchar(c));
		}
	}
	
	@Test
	public void dehexchar_test2() {
		//exhaustive test of values between A and F
		assertEquals(10, JSONTokener.dehexchar('A'));
		assertEquals(10, JSONTokener.dehexchar('a'));
		assertEquals(11, JSONTokener.dehexchar('B'));
		assertEquals(11, JSONTokener.dehexchar('b'));
		assertEquals(12, JSONTokener.dehexchar('C'));
		assertEquals(12, JSONTokener.dehexchar('c'));
		assertEquals(13, JSONTokener.dehexchar('D'));
		assertEquals(13, JSONTokener.dehexchar('d'));
		assertEquals(14, JSONTokener.dehexchar('E'));
		assertEquals(14, JSONTokener.dehexchar('e'));
		assertEquals(15, JSONTokener.dehexchar('F'));
		assertEquals(15, JSONTokener.dehexchar('f'));
	}
	
	@Test
	public void dehexchar_testNegativeInput() {
		Random rand = new Random();
		int negativeInput = -1, REDIX = 16;
		char c= Character.forDigit(negativeInput,REDIX);    
		assertEquals(-1,JSONTokener.dehexchar(c));
	}
	
	@Test
	public void more_test() {
		String s1 = "[I am the machine]";
		JSONTokener jt = new JSONTokener(s1);	
		assertTrue(jt.more());
	}
	
	@Test
	public void more_test_usePreviousEqualsTrue() {
		String s1 = "[I am the machine]";
		JSONTokener jt = new JSONTokener(s1);	
		jt.next();
		jt.back();
		assertTrue(jt.more());
	}
	
	@Test
	public void more_test_ExpectsFalse() {
		String s1 = "";
		JSONTokener jt = new JSONTokener(s1);	
		assertFalse(jt.more());
	}
	
	@Test
	public void skipTo_test() {
		String s1 = "[I am the machine]";
		JSONTokener jt = new JSONTokener(s1);	
		assertEquals('t', jt.skipTo('t'));
	}

}
