package com.dbs.quiz.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CommonUtils 
{
	public static Object getObjectFromJSONText(Class Quiz, String jsonMessage)
    {	    	
        if (jsonMessage == null || jsonMessage.isEmpty() || Quiz == null) 
        {
            return null;
        }
        try 
        {
        	GsonBuilder builder = new GsonBuilder();
	        Gson gson = builder.create();
	        return gson.fromJson(jsonMessage, Quiz);
        }
        catch ( Throwable e)
        {
        	return null; 
        }		
       
    }

}
