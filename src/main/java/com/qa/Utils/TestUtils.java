package com.qa.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestUtils {
	
	public static JSONObject responsejson;
	
	
	// Creating Utils Method for Convert Json Response to String Response
	public static String getvalueJsonpath(JSONObject responsejson , String jpath) {
		Object obj=responsejson;
		
		for(String s :jpath.split("/")) 
			if(!s.isEmpty()) 
				if(!(s.contains("[") || s.contains("]"))) 
					obj=((JSONObject)obj).get(s);
				else if(s.contains("[") || s.contains("]")) 
					obj=((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]","")));
					 return obj.toString();		
		
		}

}
