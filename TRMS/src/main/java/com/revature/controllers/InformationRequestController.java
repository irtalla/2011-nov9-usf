package com.revature.controllers;

import java.util.Set;


import com.revature.beans.InformationRequest;
import com.revature.services.InformationRequestService;
import com.revature.services.InformationRequestServiceImpl;

import io.javalin.http.Context;

public class InformationRequestController {
	private static InformationRequestService serv = new InformationRequestServiceImpl();
	
	public static void getInformationRequestById(Context ctx)
	{
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		InformationRequest evt = serv.getInformationRequestById(id);
		
		if (evt != null) 
		{
			ctx.status(200);
			ctx.json(evt);
		} 
		else 
		{
			ctx.status(404);
		}
	}
	
	public static void getAllInformationRequests(Context ctx)
	{
		Set<InformationRequest> results = serv.getInformationRequests();
		if (results != null)
		{
			ctx.status(200);
			ctx.json(results);
		}
		else
		{
			ctx.status(404);
		}
	}
	
	public static void addInformationRequest(Context ctx)
	{
		InformationRequest evt = ctx.bodyAsClass(InformationRequest.class);
		int id = serv.addInformationRequest(evt);
		ctx.json(id);
		ctx.status(201);
	}
	
	public static void deleteInformationRequest(Context ctx)
	{
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		InformationRequest evt = serv.getInformationRequestById(id);
		serv.removeInformationRequest(evt);
		if (evt != null) 
		{
			ctx.status(200);
			ctx.json(evt);
		} 
		else 
		{
			ctx.status(404);
		}
	}
	
	public static void updateInformationRequest(Context ctx)
	{
		InformationRequest inf = ctx.bodyAsClass(InformationRequest.class);
		
		if (inf != null) {
			ctx.status(200);
			serv.updateInformationRequest(inf);
		} else {
			ctx.status(404);
		}
	}

}
