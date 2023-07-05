package com.example.project.controller;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Temp {
	
	public static void main(String[] args) throws JSONException {
	    
		
		JSONObject system = new JSONObject();
		system.put("role","system");
		system.put("content", "1");
		
		JSONObject user = new JSONObject();
		user.put("role","user");
		user.put("content", "1");
		
		
		JSONArray ja = new JSONArray();
		ja.put(system);
		ja.put(user);
	     
	    JSONObject jo = new JSONObject();
	    jo.put("model", "gpt-3.5-turbo");
	    jo.put("messages", ja);
	     

	     
	     System.out.println(jo.toString());
	}

}
