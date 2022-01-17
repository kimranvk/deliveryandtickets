package org.abudhabi.tickets.utils;

import com.google.gson.Gson;

public class JsonUtils { 

	public static String toJson(Object object) {
	    Gson gson = new Gson();
	    return gson.toJson(object);
	}
}
