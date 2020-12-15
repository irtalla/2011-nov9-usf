package com.revature.controller;

import java.util.Set;

import com.revature.models.Requests;
import com.revature.services.RequestService;
import com.revature.services.RequestServiceImpl;

import io.javalin.http.Context;

public class RequestController {
	private static RequestService reqServ = new RequestServiceImpl();
	
	public static void makeRequest(Context ctx) {
		System.out.println("Making request");
		Requests newReq = reqServ.parseContext(ctx.body());
		try {
			Integer newId = reqServ.addRequest(newReq);
		} catch (Exception e) {
			System.out.println("An exception occurred");
			ctx.status(500);
			return;
		}
		ctx.status(200);
	}
	
	public static void getRequestById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Requests r = reqServ.getRequestById(id);
		if (r != null) {
			ctx.status(200);
			ctx.json(r);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getRequestByRequester(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Set<Requests> r = reqServ.getRequestByRequester(id);
		if (r != null) {
			ctx.status(200);
			ctx.json(r);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getRequestByRequestee(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Set<Requests> r = reqServ.getRequestByRequestee(id);
		if (r != null) {
			ctx.status(200);
			ctx.json(r);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getAllRequests(Context ctx) {
		Set<Requests> requests = reqServ.getAllRequests();
		if (requests != null) {
			ctx.status(200);
			ctx.json(requests);
		} else {
			ctx.status(404);
		}
	}
	
	public static void updateRequests(Context ctx) {
		Requests tempReq = reqServ.parseContext(ctx.body());
		System.out.println(tempReq);
		try {
			reqServ.updateRequests(tempReq);
		} catch (Exception e) {
			System.out.println("An exception has occured");
			ctx.status(200);
			return;
		}
		ctx.status(202);
	}
	
	public static void deleteRequests(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Requests request = reqServ.getRequestById(id);
		reqServ.deleteRequests(request);
		ctx.status(204);
	}
}
