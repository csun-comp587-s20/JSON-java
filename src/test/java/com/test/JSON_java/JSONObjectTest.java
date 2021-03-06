package com.test.JSON_java;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.DoubleAdder;
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
	
	@Test
	public void SimilarTest_KeysDoNotMatch() {
		JSONObject jo = new JSONObject();
		JSONObject jo2 = new JSONObject();
		jo.put("key1", "val1");
		jo2.put("key2", "val2");
		assertFalse(jo.similar(jo2));
		assertFalse(jo2.similar(jo));
	}
	
	//auto generated inputs
	@Test
	public void SimilarTest_KeysDoNotMatch_AutoInputs() {
		JSONObject jo = new JSONObject();
		JSONObject jo2 = new JSONObject();
		KeyandValuePairGenerator kvpg = new KeyandValuePairGenerator();
		int keyLength = 10, stringLength = 10;
		kvpg.generateNewKey(keyLength);
		kvpg.generateNewStringValue(stringLength);
		jo.put(kvpg.getKey(), kvpg.getStringValue());
		kvpg.generateNewKey(keyLength);
		kvpg.generateNewStringValue(stringLength);
		jo2.put(kvpg.getKey(), kvpg.getStringValue());
		assertFalse(jo.similar(jo2));
		assertFalse(jo2.similar(jo));	
	}
	
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
	
	//auto generated inputs
	@Test
	public void SimilarTest_IdenticalKeys_differentAutoGeneratedValues() {
		JSONObject jo = new JSONObject();
		JSONObject jo2 = new JSONObject();
		JSONObject jo3 = new JSONObject();
		JSONObject jo4 = new JSONObject();
		KeyandValuePairGenerator kvpg = new KeyandValuePairGenerator();
		int keyLength = 5;
		kvpg.generateNewKey(keyLength);
		jo.put(kvpg.getKey(), jo3);
		kvpg.generateNewIntValue();
		jo4.put(kvpg.getKey(), kvpg.getIntValue());
		jo2.put("key", jo4);
		assertFalse(jo.similar(jo2));
		assertFalse(jo2.similar(jo));
	}

	@Test
	public void testMulti() {
		JSONObject jo = new JSONObject();
		jo.put("name", "jon doe");
		jo.put("age", "22");
		jo.put("city", "chicago");

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
	
	//auto generated inputs
	@Test
	public void JSONObjectConstructorMap_autoGenInputs() {	
		KeyandValuePairGenerator kvpg = new KeyandValuePairGenerator();
		Map<String, String> map = new HashMap<String,String>();
		JSONObject joTest = new JSONObject();
		int keyLength = 5, stringLength = 20;
		kvpg.generateNewKey(keyLength);
		kvpg.generateNewStringValue(stringLength);
		String key1 = kvpg.getKey();
		
		map.put(kvpg.getKey(), kvpg.getStringValue());
		joTest.put(kvpg.getKey(), kvpg.getStringValue());
		kvpg.generateNewKey(keyLength);
		kvpg.generateNewStringValue(stringLength);
		
		String key2 = kvpg.getKey();
		map.put(kvpg.getKey(), kvpg.getStringValue());
		joTest.put(kvpg.getKey(), kvpg.getStringValue());
		JSONObject joMap = new JSONObject(map);

		assertEquals(joMap.get(key1), joTest.get(key1));
		assertEquals(joMap.get(key2), joTest.get(key2));
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
	@Test(expected = JSONException.class)
	public void putOnceDuplicateKeyTest() {
		JSONObject jo = new JSONObject();
		jo.put("name", "jon doe");
		jo.putOnce("name", "shmo");
	}

	
	//Auto generated Inputs
	@Test(expected = JSONException.class)
	public void putOnceDuplicateKeyTest_autoGenInputs() {
		KeyandValuePairGenerator kvpg = new KeyandValuePairGenerator();
		Random rand = new Random();
		int maxKeyLength = 20;
		int keyLength = rand.nextInt(maxKeyLength);
		kvpg.generateNewKey(keyLength);
		JSONObject jo = new JSONObject();
		jo.put(kvpg.getKey(), "jon doe");
		jo.putOnce(kvpg.getKey(), "shmo");

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
		assertEquals("{\"name\":\"jon doe\"}", jo2.toString());
	}
	
	@Test
	public void stringToValueTestNegative() {
		String negative = "-1";
		assertEquals(JSONObject.stringToValue(negative), -1);
	}
	
	@Test
	public void stringToValueTest0() {
		String zero = "0";
		assertEquals(JSONObject.stringToValue(zero), 0);
	}
	
	@Test
	public void stringToValueTest1() {
		String one = "1";
		assertEquals(JSONObject.stringToValue(one), 1);
	}
	
	@Test
	public void stringToValueTest2() {
		String two = "2";
		assertEquals(JSONObject.stringToValue(two), 2);
	}
	
	//auto generated inputs
	@Test
	public void stringToValueTestRandom() {
		int numberOfTests = 10;
		for (int i = 0; i < numberOfTests; i++) {
			Random rand = new Random();
			int ceiling = 1000;
			int randomInt = rand.nextInt(ceiling);
			String value = Integer.toString(randomInt);
			assertEquals(JSONObject.stringToValue(value), randomInt);
		}
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
		assertEquals(new BigInteger("2"), jo.get("val1"));
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
	
	/*when passing a null object to objectToBigInteger 
	the default value should be returned.*/
	@Test
	public void obj2BigInteger_defaultValue() {
		Object val = null;
		BigInteger defaultVal = new BigInteger("0");
		BigInteger returnedVal = JSONObject.objectToBigInteger(val, defaultVal);
		assertEquals(returnedVal, defaultVal);
	}
	
	@Test
	public void obj2BigInteger_bigInteger() {
		Object val = new BigInteger("2");
		BigInteger defaultVal = new BigInteger("0");
		BigInteger returnedVal = JSONObject.objectToBigInteger(val, defaultVal);
		assertEquals(returnedVal, val);
	}
	
	@Test
	public void obj2BigInteger_bigDecimal() {
		Object val = new BigDecimal("2");
		BigInteger defaultVal = new BigInteger("0");
		BigInteger returnedVal = JSONObject.objectToBigInteger(val, defaultVal);
		assertEquals(returnedVal, new BigInteger("2"));
	}
	
	
	@Test
	public void obj2BigInteger_float() {
		Object val = (float) 2;
		BigInteger defaultVal = new BigInteger("0");
		BigInteger returnedVal = JSONObject.objectToBigInteger(val, defaultVal);
		assertEquals(returnedVal, new BigInteger("2"));
	}
	
	@Test
	public void obj2BigInteger_double() {
		Object val = (double) 2;
		BigInteger defaultVal = new BigInteger("0");
		BigInteger returnedVal = JSONObject.objectToBigInteger(val, defaultVal);
		assertEquals(returnedVal, new BigInteger("2"));
	}
	
	@Test
	public void obj2BigInteger_long() {
		Object val = (long) 2;
		BigInteger defaultVal = new BigInteger("0");
		BigInteger returnedVal = JSONObject.objectToBigInteger(val, defaultVal);
		assertEquals(returnedVal, new BigInteger("2"));
	}
	
	@Test
	public void obj2BigInteger_Integer() {
		Object val = (Integer) 2;
		BigInteger defaultVal = new BigInteger("0");
		BigInteger returnedVal = JSONObject.objectToBigInteger(val, defaultVal);
		assertEquals(returnedVal, new BigInteger("2"));
	}
	
	@Test
	public void obj2BigInteger_Short() {
		Object val = (short) 2;
		BigInteger defaultVal = new BigInteger("0");
		BigInteger returnedVal = JSONObject.objectToBigInteger(val, defaultVal);
		assertEquals(returnedVal, new BigInteger("2"));
	}
	
	@Test
	public void obj2BigInteger_Byte() {
		Object val = (byte) 2;
		BigInteger defaultVal = new BigInteger("0");
		BigInteger returnedVal = JSONObject.objectToBigInteger(val, defaultVal);
		assertEquals(returnedVal, new BigInteger("2"));
	}
	
	//AtomicInteger is an unchecked Number subclass
	//it should still be handled by the function
	@Test
	public void obj2BigInteger_AtomicInteger() {
		AtomicInteger val = new AtomicInteger(2);
		BigInteger defaultVal = new BigInteger("0");
		BigInteger returnedVal = JSONObject.objectToBigInteger(val, defaultVal);
		assertEquals(returnedVal, new BigInteger("2"));	
	}
	
	//DoubleAdder is an unchecked Number subclass
	//it should still be handled by the function
	@Test
	public void obj2BigInteger_DoubleAdder() {
		DoubleAdder val = new DoubleAdder();
		val.add(2.0);
		BigInteger defaultVal = new BigInteger("0");
		BigInteger returnedVal = JSONObject.objectToBigInteger(val, defaultVal);
		assertEquals(returnedVal, new BigInteger("2"));	
	}
	
	@Test
	public void stringToValue_emptyTest() {
		String s1 = "";
		assertEquals(s1, JSONObject.stringToValue(s1));
	}
	
	@Test
	public void stringToValue_trueTest() {
		String s1 = "true";
		boolean b = true;
		assertEquals(b, JSONObject.stringToValue(s1));
	}
	
	@Test
	public void stringToValue_falseTest() {
		String s1 = "false";
		boolean b = false;
		assertEquals(b, JSONObject.stringToValue(s1));
	}
	
	@Test
	public void stringToValue_nullTest() {
		String s1 = "null";
		JSONObject jo = new JSONObject();
		assertEquals(jo.NULL, JSONObject.stringToValue(s1));
	}
	
	@Test
	public void stringToValue_num1() {
		String s1 = "1";
		assertEquals(1, JSONObject.stringToValue(s1));
	}
	
	@Test
	public void stringToValue_numNegative() {
		String s1 = "-1.0";
		assertEquals(-1.0, JSONObject.stringToValue(s1));
	}
	

	@Test
	public void toMapTest() {
		Map<String, String> map = new HashMap<String,String>();
		map.put("name", "jon doe");
		map.put("name2", "carl");
		JSONObject joMap = new JSONObject(map);
		assertEquals(map, joMap.toMap());
	}
	
	//auto generated inputs
	@Test
	public void getNumberTest() {
		JSONObject jo = new JSONObject();
		KeyandValuePairGenerator kvpg = new KeyandValuePairGenerator();
		int keyLength = 5;
		kvpg.generateNewKey(keyLength);
		int minValue = 1, maxValue = 100;
		kvpg.generateNewIntValue(minValue, maxValue);
		jo.put(kvpg.getKey(), kvpg.getIntValue());
		assertEquals(jo.getNumber(kvpg.getKey()), kvpg.getIntValue());
	}
	
	//auto generated inputs
	@Test
	public void getNumberTest_numberIsAString() {
		JSONObject jo = new JSONObject();
		KeyandValuePairGenerator kvpg = new KeyandValuePairGenerator();
		int keyLength = 5;
		kvpg.generateNewKey(keyLength);
		int minValue = 1, maxValue = 100;
		kvpg.generateNewIntValue(minValue, maxValue);
		String numberAsString =  Integer.toString(kvpg.getIntValue()); 
		jo.put(kvpg.getKey(), numberAsString);
		assertEquals(jo.getNumber(kvpg.getKey()), kvpg.getIntValue());
	}
	
	//auto generated inputs
	@Test(expected = JSONException.class)
	public void getNumberTest_exception() {
		JSONObject jo = new JSONObject();
		KeyandValuePairGenerator kvpg = new KeyandValuePairGenerator();
		int keyLength = 5;
		kvpg.generateNewKey(keyLength);
		int stringLength = 5;
		kvpg.generateNewStringValue(stringLength);
		jo.put(kvpg.getKey(), kvpg.getStringValue());
		assertEquals(jo.getNumber(kvpg.getKey()), kvpg.getStringValue());
	}
	
	@Test
	public void toJSONArray() {
		Object obj = new Object();
		JSONArray jArrNames = new JSONArray();
		jArrNames.put("a");
		
		JSONObject jo = new JSONObject();
		jo.put("a", obj);
		
		JSONArray jArrValues = new JSONArray();
		jArrValues.put(obj);
		
		assertTrue(jArrValues.similar(jo.toJSONArray(jArrNames)));
		assertTrue(jo.toJSONArray(jArrNames).similar(jArrValues));
	}
}