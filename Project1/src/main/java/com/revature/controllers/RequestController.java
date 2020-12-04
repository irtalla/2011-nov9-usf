package com.revature.controllers;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.beans.Pitch;
import com.revature.beans.Request;

import io.javalin.http.Context;

public class RequestController {
	
	private static Gson gson; 
	
	public static void initGsonBuilder() {
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	    gson = builder.create(); 
	}
	
	public static void getRequests(Context ctx) {}
	public static void addRequest(Context ctx) {}
	public static void ddRequest(Context ctx) {}
	public static void updateRequest(Context ctx) {}
	public static void deleteRequest(Context ctx) {}
	public static void getRequestById(Context ctx) {}
	public static void getAllRequests(Context ctx) {}
	public static void closeRequest(Context ctx) {}
	
	
	public static void getRequestsByPersonId(Context ctx) {
		
		Integer personId = Integer.parseInt( ctx.pathParam("id") ); 
		System.out.println(personId);
		
		Request requestA = new Request(), requestB = new Request(), 
				requestC = new Request(), requestD = new Request(); 
		
		// Two outgoing requests 
		requestA.setId(1);
		requestA.setRequestorId(personId);
		requestA.setRequesteeId(4);
		requestA.setRequestContent("I need information from you");
		
		requestB.setId(2);
		requestB.setRequestorId(personId);
		requestB.setRequesteeId(43);
		requestB.setRequestContent("I need information from you");
		requestB.setResponseContent("Here is the infomation you request");
		
		// Two incoming requests 
		requestC.setId(3);
		requestC.setRequestorId(23);
		requestC.setRequesteeId(personId);
		requestC.setRequestContent("I need information from you");
		
		requestD.setId(4);
		requestD.setRequestorId(12);
		requestD.setRequesteeId(personId);
		requestD.setRequestContent("I need information from you");
		requestD.setResponseContent("Here is the infomation you request");
		
		
		Request requests[] = { requestA, requestB, requestC, requestD }; 
		
	    try {
		    ctx.json( gson.toJson(requests) );
			ctx.status(200);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	ctx.status(500);
	    }
		
		
	}

}
