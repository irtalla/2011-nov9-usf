package com.cross.controllers;

import static io.javalin.apibuilder.ApiBuilder.delete;
import static io.javalin.apibuilder.ApiBuilder.get;
import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.put;

import com.cross.beans.Person;
import com.cross.beans.Pitch;
import com.cross.beans.Request;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.services.RequestService;
import com.revature.services.RequestServiceImpl;

import io.javalin.http.Context;

public class RequestController {
	
	private static RequestService requestServ = new RequestServiceImpl();
	private static Gson gson; 
	
	public static void initGsonBuilder() {
		GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	    gson = builder.create(); 
	}
	
	public static void getRequests(Context ctx) {}
	public static void addRequest(Context ctx) {}
	public static void deleteRequest(Context ctx) {}
	public static void getRequestById(Context ctx) {}
	public static void getAllRequests(Context ctx) {}
	
    
    
	public static void updateRequest(Context ctx) {
		
	    Request request;
	    request = gson.fromJson( ctx.body(), Request.class);
	    System.out.println(request.getId());
	    System.out.println(request.getStatus().getName());
	    System.out.println(request.getRequestContent());
	    System.out.println(request.getResponseContent());
	    ctx.status(200); 
		
		
	}
    
    
	public static void closeRequest(Context ctx) {
		
		Integer personId = Integer.parseInt( ctx.pathParam("id") ); 
		System.out.println(personId);
		ctx.status(200); 
		
	}
	
	public static void getRequestsByPersonId(Context ctx) {		
		try {
			Integer id = Integer.parseInt( ctx.pathParam("id") ); 
			System.out.println(id);
			Object[] requests = requestServ.getRequestsByParticipantId(id).toArray();
		    ctx.json( gson.toJson(requests) );
			ctx.status(200);
			
		} catch (Exception e) {
			e.printStackTrace();
			ctx.status(500);
		}
		
//		
//		Request requestA = new Request(), requestB = new Request(), 
//				requestC = new Request(), requestD = new Request(); 
//		
//		// Two outgoing requests 
//		requestA.setId(1);
//		requestA.setSenderId(personId);
//		requestA.setRecieverId(4);
//		requestA.setRequestContent("I need information from you");
//		
//		requestB.setId(2);
//		requestB.setSenderId(personId);
//		requestB.setRecieverId(43);
//		requestB.setRequestContent("I need information from you");
//		requestB.setResponseContent("Here is the infomation you request");
//		
//		// Two incoming requests 
//		requestC.setId(3);
//		requestC.setSenderId(23);
//		requestC.setRecieverId(personId);
//		requestC.setRequestContent("I need information from you");
//		
//		requestD.setId(4);
//		requestD.setSenderId(12);
//		requestD.setRecieverId(personId);
//		requestD.setRequestContent("I need information from you");
//		requestD.setResponseContent("Here is the infomation you request");
//		
//		
//		Request requests[] = { requestA, requestB, requestC, requestD }; 
//		
//	    try {
//		    ctx.json( gson.toJson(requests) );
//			ctx.status(200);
//	    } catch (Exception e) {
//	    	e.printStackTrace();
//	    	ctx.status(500);
//	    }
		
		
	}

}
