package com.test.JSON_java;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.math.BigInteger; 
import java.math.BigDecimal; 



public class JSONObjectTest {
	
	/*JSON Objects cannot be tested for equivalence since they are unordered.
	 * the closest thing we have to testing equivalence is the JSONObject.similar function
	 * the similar function checks that the JSON Objects have the same key and value pairs
	 * but ignores ordering
	 */
	//compare two empty JSON Objects
	@Test
	public void similarTest_Empty() {
		JSONObject jo = new JSONObject();
		JSONObject jo2 = new JSONObject();
		assertTrue(jo.similar(jo2));
		assertTrue(jo2.similar(jo));
	}
	
	@Test
	public void SimilarTest_notJSONObject() {
		JSONObject jo = new JSONObject();
		Object notJSON = new Object();
		assertFalse(jo.similar(notJSON));
	}
	
	//good candidate for auto generated inputs
	@Test
	public void SimilarTest_KeysDoNotMatch() {
		JSONObject jo = new JSONObject();
		JSONObject jo2 = new JSONObject();
		jo.put("key1", "val1");
		jo2.put("key2", "val2");
		assertFalse(jo.similar(jo2));
		assertFalse(jo2.similar(jo));
	}
	
	//good candidate for auto generated inputs
	//Testing similarity of two json objects with identical keys and values
	@Test
	public void SimilarTest_IdenticalKeysIdenticalValues() {
		JSONObject jo = new JSONObject();
		JSONObject jo2 = new JSONObject();
		jo.put("key1", "val1");
		jo2.put("key1", "val1");
		assertTrue(jo.similar(jo2));
		assertTrue(jo2.similar(jo));
	}
	
	@Test
	public void SimilarTest_IdenticalKeysDifferentValues() {
		JSONObject jo = new JSONObject();
		JSONObject jo2 = new JSONObject();
		jo.put("key1", "val1");
		jo2.put("key1", "differentValue");
		assertFalse(jo.similar(jo2));
		assertFalse(jo2.similar(jo));
	}
	
	@Test
	public void SimilarTest_IdenticalKeys_IdenticalJSONObjectValues() {
		JSONObject jo = new JSONObject();
		JSONObject jo2 = new JSONObject();
		JSONObject jo3 = new JSONObject();
		jo.put("key", jo3);
		jo2.put("key", jo3);
		assertTrue(jo.similar(jo2));
		assertTrue(jo2.similar(jo));
	}
	
	@Test
	public void SimilarTest_IdenticalKeys_differentJSONObjectValues() {
		JSONObject jo = new JSONObject();
		JSONObject jo2 = new JSONObject();
		JSONObject jo3 = new JSONObject();
		JSONObject jo4 = new JSONObject();
		jo.put("key", jo3);
		jo4.put("key", "value");
		jo2.put("key", jo4);
		assertFalse(jo.similar(jo2));
		assertFalse(jo2.similar(jo));
	}
	//to test remaining similar functions I need to get familar with JSONArray and 

	//how do we test jsonobject it doesn't seem like the output is sorted or even 
	//in the order the elements were put.
	//Can use the similar function instead?
	@Test
	public void testMulti() {
		JSONObject jo = new JSONObject();
		jo.put("name", "jon doe");
		jo.put("age", "22");
		jo.put("city", "chicago");
		
		//It appears that these json objects have a tostring which they have inherited
		//weird cause I don't see tostring overriddgen in JSONObject.java
		//maybe its using toString from hashmap?
		System.out.println(jo);
		
		String testString = "{\"city\":\"chicago\",\"name\":\"jon doe\",\"age\":\"22\"}";
		assertEquals(testString, jo.toString());
	}
	
	//JSONObject Map Constructor
	@Test
	public void JSONObjectConstructorMap() {
		Map<String, String> map = new HashMap<String,String>();
		map.put("name", "jon doe");
		map.put("name2", "carl");
		JSONObject joMap = new JSONObject(map);
		//joTest only created to compare outputs
		JSONObject joTest = new JSONObject();
		joTest.put("name", "jon doe");
		joTest.put("name2", "carl");
		assertEquals(joMap.toString(), joTest.toString());	
	}

	
	@Test
	public void putNullKeyTest() {
		JSONObject jo = new JSONObject();
		String key = null;
		try {
			jo.put(key, "jon doe");
		} catch (NullPointerException aNullPointerException) {
			assertEquals(aNullPointerException.getMessage(), "Null key.");
		}
	}
	
	//test put once function to see if a duplicate key is accepted
	//should throw JSONException	
	@Test
	public void putOnceDuplicateKeyTest() {
		JSONObject jo = new JSONObject();
		jo.put("name", "jon doe");
		try {
			jo.putOnce("name", "shmo");
		} catch (JSONException  aJSONException ) {
			System.out.println(aJSONException.getMessage());
			assertEquals(aJSONException.getMessage(), "Duplicate key \"name\"");
		}
	}
	
	/*  Construct a JSONObject from a subset of another JSONObject. An array of
     * strings is used to identify the keys that should be copied.
     * Test that objects are identical */
	@Test
	public void JSONObjectConstructorSubset1() {
		JSONObject jo = new JSONObject();
		jo.put("name", "jon doe");
		jo.put("name2", "carl");
		String names[] = {"name", "name2"};
		JSONObject jo2 = new JSONObject(jo, names);
		assertEquals(jo.toString(), jo2.toString());
	}
	
	//only copying subset of strings to new object
	@Test
	public void JSONObjectConstructorSubset2() {
		JSONObject jo = new JSONObject();
		jo.put("name", "jon doe");
		jo.put("name2", "carl");
		String names[] = {"name"};
		JSONObject jo2 = new JSONObject(jo, names);
		System.out.println(jo);
		System.out.println(jo2);
		assertEquals("{\"name\":\"jon doe\"}", jo2.toString());
	}
	
	@Test
	public void stringToValueTest() {
		String two = "2";
		assertEquals(JSONObject.stringToValue(two), 2);
	}
	
	@Test
	public void incrementTest_nullValue() {
		JSONObject jo = new JSONObject();
		jo.increment("val1");
		assertEquals(1, jo.get("val1"));
	}

	@Test
	public void incrementTest_int() {
		JSONObject jo = new JSONObject();
		int value = 1;
		jo.put("val1", value);
		jo.increment("val1");
		assertEquals(2, jo.get("val1"));
	}
	
	@Test
	public void incrementTest_Integer() {
		JSONObject jo = new JSONObject();
		Integer value = 1;
		jo.put("val1", value);
		jo.increment("val1");
		assertEquals(2, jo.get("val1"));
	}
	
	@Test
	public void incrementTest_Long() {
		JSONObject jo = new JSONObject();
		Long value = (long) 1;
		jo.put("val1", value);
		jo.increment("val1");
		assertEquals((long)2, jo.get("val1"));
	}
	
	@Test
	public void incrementTest_BigInteger() {
		JSONObject jo = new JSONObject();
		BigInteger value = new BigInteger("1");
		jo.put("val1", value);
		jo.increment("val1");
		BigInteger truthValue = new BigInteger("2");
		assertEquals(truthValue, jo.get("val1"));
	}
	
	@Test
	public void incrementTest_Float() {
		JSONObject jo = new JSONObject();
		Float value = (float) 1;
		jo.put("val1", value);
		jo.increment("val1");
		assertEquals((float) 2, jo.get("val1"));
	}
	
	@Test
	public void incrementTest_Double() {
		JSONObject jo = new JSONObject();
		Double value =  1.0;
		jo.put("val1", value);
		jo.increment("val1");
		assertEquals(2.0, jo.get("val1"));
	}
	
	@Test
	public void incrementTest_BigDecimal() {
		JSONObject jo = new JSONObject();
		BigDecimal value =  new BigDecimal(1);
		jo.put("val1", value);
		jo.increment("val1");
		BigDecimal truthValue =  new BigDecimal(2);
		assertEquals(truthValue, jo.get("val1"));
	}
	
	@Test(expected = JSONException.class)
	public void incrementTest_NonAcceptableType() {
		JSONObject jo = new JSONObject();
		String value = "1";
		jo.put("val1", value);
		jo.increment("val1");
		assertEquals("2", jo.get("val1"));
	}
	
}
