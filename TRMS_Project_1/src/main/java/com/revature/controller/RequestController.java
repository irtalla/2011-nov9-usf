package com.revature.controller;

import java.util.Set;

import com.revature.beans.Person;
import com.revature.beans.Request;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.RequestServiceImpl;

import io.javalin.http.Context;

public class RequestController {
	private static RequestServiceImpl requestServ = new RequestServiceImpl();	
	
	public static void newRequest(Context ctx) throws NonUniqueUsernameException {
		
		Request request = ctx.bodyAsClass(Request.class);
		requestServ.addRequest(request);		
		ctx.status(201);
	}
	
	/*public static void getMyRequests(Context ctx) {
		Person p = ctx.sessionAttribute("user");
		String username = p.getUsername();
		System.out.println("My name is "  + username);
		Set<Request> RequestSet = requestServ.getRequestByUsername(username);	
		
		if (RequestSet != null) {
			ctx.status(200);
			ctx.json(RequestSet);
		} else {
			ctx.status(404);
		}
	}*/
	
	public static void updateReuest(Context ctx) {
		
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Request request = ctx.bodyAsClass(Request.class);
		if (request != null) {
			ctx.status(200);
			requestServ.updateRequest(request);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getAllRequests(Context ctx) {
		Set<Request> requests = requestServ.getRequests();
		
		if (requests != null) {
			ctx.status(200);
			ctx.json(requests);
		} else {
			ctx.status(404);
		}
	}
	
}
