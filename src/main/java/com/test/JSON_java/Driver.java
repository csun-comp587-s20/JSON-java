package com.test.JSON_java;

import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {
		
		String cats = "\"cats\"";
		String dogs = "dogs";
		
		ArrayList<String> strings = new ArrayList<String>();
		strings.add("a");
		strings.add("a");
		strings.add("a");
		System.out.println(String.join(" ", strings));
		
		System.out.println(String.join(" ", cats, dogs));
		
		
		JSONArray jArr = new JSONArray();
		jArr.put("cats");
		jArr.put("dogs");
		String joined = jArr.join(" ");
		System.out.println(joined);
		jArr.put(2,"turtles");
		System.out.println(jArr.join(" "));
		
		
		/*
		JSONObject jo = new JSONObject();
		jo.put("name", "jon doe");
		jo.put("name2", 22);

		String s1 = "[I am the machine]";
		JSONTokener jt = new JSONTokener(s1);
		JSONArray jArr = new JSONArray("[a string]");
		System.out.println(jArr.length());
		System.out.println(jArr.toString());
		*/
		
		
		/*
		String s1 = "[I am a test string]";
		JSONArray jArray = new JSONArray(s1); 
		System.out.println(jArray.getString(0));
		*/
		
		/*
		JSONObject jo = new JSONObject();
		jo.put("name", "jon doe");
		jo.putOnce("name", "joe");
		jo.put("age", "22");
		jo.put("city", "chicago");
		
		//It appears that these json objects have a to string which they have inherited
		//weird cause I don't see tostring overriddgen in JSONObject.java
		//maybe its using toString from hashmap?
		System.out.println(jo);
		
		//look into JSONObject static function quote
		System.out.println(JSONObject.quote("cat doggy dog dog"));
*/

	}

}
