package com.sourcedemo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpUtil {

	private String value;
	
	public HttpUtil (String value) {
		this.value = value;
	}

//	convert string to the corresponding object
	public <T> T toModel(Class<T> tClass) {
		try {
			// ObjectMapper convert a JSON String to a JavaObject
			return new ObjectMapper().readValue(value, tClass);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return null;
	}


	// convert parameters from API to string
	public static HttpUtil of (BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		try {
			String line;
		    while ((line = reader.readLine()) != null) {
		        sb.append(line);
		    }
		} catch (IOException e) {
			System.out.print(e.getMessage());
		}
		return new HttpUtil(sb.toString());
	}
}
