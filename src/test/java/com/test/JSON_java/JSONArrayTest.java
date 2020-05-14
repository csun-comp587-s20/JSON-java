package com.test.JSON_java;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Test;

public class JSONArrayTest {
	
	@Test
	public void similarTest_emptyArrays() {
		JSONArray jArray = new JSONArray();
		JSONArray jArray2 = new JSONArray();
		assertTrue(jArray.similar(jArray2));
		assertTrue(jArray2.similar(jArray));
	}
	
	@Test
	public void similarTest_nonJSONArray() {
		JSONArray jArray = new JSONArray();
		JSONObject jObj = new JSONObject();
		assertFalse(jArray.similar(jObj));
	}
	
	@Test
	public void SimilarTest_JSONArraySameLength() {
		int numOfTests = 10;
		for (int k = 0; k < numOfTests; k++) {
			JSONArray jArr = new JSONArray();
			JSONArray jArr2 = new JSONArray();
			Object obj = new Object();
	
			//random length used for both lists
			Random rand = new Random();
			int max = 25; int min = 1;
			int length = rand.nextInt(max - min) + min;
			
			//push the value to both jsonarrays
			for (int i = 0; i < length; i++) {
				jArr.put(obj);
				jArr2.put(obj);
			}
			
			assertTrue(jArr.similar(jArr2));
			assertTrue(jArr2.similar(jArr));
		}
	}
	
	/*The similar function should return false when comparing 
	* JSONArrays of different lengths.
	* comparing to JSONArrays of different lengths.*/
	@Test
	public void similarTest_JSONArraysDifferentLengths() {
		int numOfTests = 10;
		for(int i = 0; i < numOfTests; i++) {
			JSONArray jArray = new JSONArray();
			JSONArray jArray2 = new JSONArray();
			
			//make jArray a random length by putting an object at a random index.
			int max = 50;
			Random rand = new Random();
			int randomIndex1 = rand.nextInt(max), randomIndex2 = rand.nextInt(max);
			
			if (randomIndex1 == randomIndex2) {
				randomIndex1 += 1;
			}
			
			Object obj = new Object();
			jArray.put(randomIndex1, obj);
			jArray2.put(randomIndex2, obj);
			
			assertFalse(jArray.similar(jArray2));
			assertFalse(jArray2.similar(jArray));
		}
	}
	
	//The join function makes a string from the contents of this JSONArray.
	
	@Test
	public void joinTest_emptyJSONArray() {
		JSONArray jArr = new JSONArray();
		assertEquals("",jArr.join(""));
	}
	
	//auto generated inputs
	@Test
	public void joinTest_automatedInputs() {
		JSONFormatStringGenerator gen = new JSONFormatStringGenerator();
		
		int numOfTests = 10;
		for (int k = 0; k < numOfTests; k++) {
			int maxLengthOfStrings = 20;
			gen.genStrings(maxLengthOfStrings);
			
			Random rand = new Random();
			int max = 10; int min = 1;
			int numOfStrings = rand.nextInt(max - min) + min;
			
			JSONArray jArr = new JSONArray();
			ArrayList<String> listOfStrings = new ArrayList<String>();
			for (int i = 0; i < numOfStrings; i++) {
				jArr.put(gen.getStringWithoutQuotes());
				listOfStrings.add(gen.getStringWithQuotes());
			}
			
			String testString = String.join(" ", listOfStrings);
			assertEquals(testString, jArr.join(" "));
		}
	}
		
	//The put function should not accept negative values as an index argument.
	@Test(expected = JSONException.class)
	public void putTest_negativeIndexValue() {
		JSONArray jArray = new JSONArray();
		Object obj = new Object();
		jArray.put(-1,obj);
	}
	
	//test if JSONArray grows to size of the provided index argument
	//auto generated inputs
	@Test
	public void putTest_IndexValueGreaterThanLength_random() {
		Object obj = new Object();
		
		//run x test iterations
		int numTestIterations = 10;
		for(int k = 0; k < numTestIterations; k++) {
			JSONArray jArray = new JSONArray();
			
			/*push random number of objects to JSONArray so that we have a
			JSONArray of random length.*/
			Random rand = new Random();
			int maxLength  = 20;
			int randomLength = rand.nextInt(maxLength);
			for (int i = 0; i < randomLength; i++) {			
				jArray.put(i,obj);
			}
			
			// produce a random number to add to length by 
			int minValue = 1, maxValue = 100;
			int incrementValue = rand.nextInt(maxValue - minValue) + minValue;
			int indexValue = randomLength + incrementValue;
			jArray.put(indexValue,obj);
		
			assertEquals(indexValue + 1, jArray.length());
		}	
	}
	
	/*test that we can put an object at an index less than length
	 *  and then retrieve that object from the index*/
	//auto generated inputs
	@Test
	public void putAndGetTest_object() {
		Object obj = new Object();
		JSONArray jArray = new JSONArray();
		//insert at a random index to alter length of list
		Random rand = new Random();
		int maxValue = 100, minValue = 1,
		newLengthOfArray = rand.nextInt(maxValue - minValue) + minValue;
		/*put arbitrary value into JSONArray since its only purpose
		is to extend length of list*/
		jArray.put(newLengthOfArray,rand.nextInt(50));
		//insert obj into random index < length of JSONArray
		int randomIndex = rand.nextInt(newLengthOfArray - 1);
		jArray.put(randomIndex,obj);
		assertEquals(obj, jArray.get(randomIndex));
	}
	
	
	/*test that we can put an boolean at an index and then retrieve that
	boolean from the index*/
	//auto generated inputs
	@Test
	public void putAndGetTest_trueBoolean() {
		boolean trueBool = true;
		JSONArray jArray = new JSONArray();
		//insert at a random index
		Random rand = new Random();
		int maxValue = 100, randomIndex = rand.nextInt(maxValue);
		jArray.put(randomIndex,trueBool);
		assertEquals(trueBool, jArray.getBoolean(randomIndex));
	}
	
	
	/*test that we can put a string "true" at an index and then retrieve that
	string as a boolean from the index*/
	//auto generated inputs
	@Test
	public void putAndGetTest_trueBoolean2() {
		String trueBoolString = "true";
		boolean trueBool = true;
		JSONArray jArray = new JSONArray();
		//insert at a random index
		Random rand = new Random();
		int maxValue = 100, randomIndex = rand.nextInt(maxValue);
		jArray.put(randomIndex,trueBoolString);
		assertEquals(trueBool, jArray.getBoolean(randomIndex));
	}

	/*test that we can put an boolean at an index and then retrieve that
	boolean from the index*/
	//auto generated inputs
	@Test
	public void putAndGetTest_falseBoolean() {
		boolean falsebool = false;
		JSONArray jArray = new JSONArray();
		//insert at a random index
		Random rand = new Random();
		int maxValue = 100, randomIndex = rand.nextInt(maxValue);
		jArray.put(randomIndex,falsebool);
		assertEquals(falsebool, jArray.getBoolean(randomIndex));
	}
	
	/*test that we can put a string "false" at an index and then retrieve that
	string as a boolean from the index*/
	//auto generated inputs
	@Test
	public void putAndGetTest_falseBoolean2() {
		String falseboolString = "false";
		boolean falsebool = false;
		JSONArray jArray = new JSONArray();
		//insert at a random index
		Random rand = new Random();
		int maxValue = 100, randomIndex = rand.nextInt(maxValue);
		jArray.put(randomIndex,falseboolString);
		assertEquals(falsebool, jArray.getBoolean(randomIndex));
	}
	
	/*try to retrieve a string using getBoolean. The string will not contain
	 the value true or false so it should throw JSONException*/
	//auto generated inputs
	@Test(expected = JSONException.class) 
	public void getBooleanTest_nonBooleanString() {
		JSONFormatStringGenerator gen = new JSONFormatStringGenerator();
		String testString = gen.getStringWithoutQuotes();
		JSONArray jArray = new JSONArray();
		jArray.put(0,testString);
		jArray.getBoolean(0);
	}
	
	/*try to retrieve a type other than String or boolean using getBoolean. 
	 * function should throw JSONException*/
	@Test(expected = JSONException.class)  
	public void getBooleanTest_wrongFormatException() {
		int testInt = 5;
		JSONArray jArray = new JSONArray();
		jArray.put(0,testInt);
		jArray.getBoolean(0);
	}
	
	//put a double into JSONArray and the retrieve using getDouble
	//auto generated inputs
	@Test
	public void putAndGetDoubleTest() {
		Random rand = new Random();
		Double testDouble = rand.nextDouble();
		JSONArray jArray = new JSONArray();
		//insert at a random index
		int maxValue = 100, randomIndex = rand.nextInt(maxValue);
		jArray.put(randomIndex,testDouble);
		assertEquals(testDouble, (Double)jArray.getDouble(randomIndex));
		
	}
	
	/*Put the String representation of a double value
	into a JSONArray and then retrieve the value using getDouble*/
	//auto generated inputs
	@Test
	public void putAndGetDoubleTest_usingString() {
		Random rand = new Random();
		Double testDouble = rand.nextDouble();
		String testString = testDouble.toString();
		JSONArray jArray = new JSONArray();
		//insert at a random index
		int maxValue = 100, randomIndex = rand.nextInt(maxValue);
		jArray.put(randomIndex,testString);
		assertEquals(testDouble, (Double)jArray.getDouble(randomIndex));
	}
	
	/*Puts a random string into a JSONArray and then attempts to 
	 * retrieve the string as a double. Should throw JSONException*/
	//auto generated inputs
	@Test(expected = JSONException.class) 
	public void putAndGetDoubleTest_wrongFormat() {
		Random rand = new Random();
		JSONFormatStringGenerator gen = new JSONFormatStringGenerator();
		String testString = gen.getStringWithoutQuotes();
		JSONArray jArray = new JSONArray();
		//insert at a random index
		int maxValue = 100, randomIndex = rand.nextInt(maxValue);
		jArray.put(randomIndex,testString);
		assertEquals(testString, (Double)jArray.getDouble(randomIndex));
	}
	

	/*test the JSONArray to list function produces a list including
	 elements that were added to the JSONArray*/
	//auto generated inputs
	@Test
	public void toListTest() {
		//test x random inputs
		int numberOfTests = 10;
		JSONFormatStringGenerator generator = new JSONFormatStringGenerator();
		int maxLength = 50;
		for (int i = 0; i < numberOfTests; i++) {	
			List<String> list = new ArrayList<String>();
			
			//generate random json strings with max length
			generator.genRandomLengthStrings(maxLength);
			
			//JSONArray constructor take string formated as such:
			// [""]
			JSONArray jArray = new JSONArray(generator.getJSONFormatString());
			list.add(generator.getStringWithoutQuotes());
			
			//JSONArray put function takes strings with no additional formatting.
			//Trying to add strings with quotations or brackets causes the jsonArray
			//to do some strang reformatting to these strings.
			jArray.put(generator.getStringWithoutQuotes());
			list.add(generator.getStringWithoutQuotes());

			//convert JSONArray to list.
			List<Object> list2 = new ArrayList<Object>();
			list2 = jArray.toList();

			assertArrayEquals(list.toArray(), list2.toArray());
		}
	}
	
	
	/*Note the toString functions prints data from index 0.
	 * can push objects to different indexes by proving an initial
	 * int argument.
	 */
	//auto generated inputs.
	@Test
	public void toStringTestSingleElementLists() {
		//test 10 random inputs
		int numberOfTests = 10;
		JSONFormatStringGenerator generator = new JSONFormatStringGenerator();
		int maxLength = 50;
		for (int i = 0; i < numberOfTests; i++) {	
			//generate random json strings with max length
			generator.genRandomLengthStrings(maxLength);
			JSONArray jArray = new JSONArray(generator.getJSONFormatString());
			assertTrue(jArray.toString().equals(generator.getJSONFormatString()));
		}
	}
	
	/* ATTENTION
	* sort of weird implementation json array required the string
	* used in the constructor to contain a [ in 0 position.
	* However strings pushed to json array do not require this bracket.
	* the formatting gets weird if pusshing string with brackets or ""
	* just pushing regular strings works.
	* 
	* NEED TO FINISH THIS FUNCTION SO THAT IT TRIES MORE
	* RANDOM STRINGS
	*/
	//auto generated inputs
	@Test
	public void toStringLongerLists() {
		JSONFormatStringGenerator generator = new JSONFormatStringGenerator();
		int maxLength = 50;
		generator.genRandomLengthStrings(maxLength);
		JSONArray jArray = new JSONArray(generator.getJSONFormatString());
		String testString = "[";
		testString += generator.getStringWithQuotes();
		generator.genRandomLengthStrings(maxLength);
		jArray.put(generator.getStringWithoutQuotes());
		testString += ",";
		testString += generator.getStringWithQuotes();
		testString += "]";
		
		assertTrue(jArray.toString().equals(testString));
	}
}