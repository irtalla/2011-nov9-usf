package com.revature.controllers;



import java.util.Set;

import com.revature.beans.EvtReq;
import com.revature.beans.Person;
import com.revature.services.EvtReqService;
import com.revature.services.EvtReqServiceImpl;

import io.javalin.http.Context;

public class EvtReqController {
	private static EvtReqService evtReqServ = new EvtReqServiceImpl();
	
	public static void getEvtReqById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		EvtReq evtReq = evtReqServ.getEvtReqById(id);
		if (evtReq != null) {
			ctx.status(200);
			ctx.json(evtReq);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getAllEvtReqs(Context ctx) {
		Set<EvtReq> evtReqs = evtReqServ.getEvtReqs();
		if (evtReqs != null) {
		//if (evtReqs.size() > 0) {
			ctx.status(200);
			ctx.json(evtReqs);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getAvailableEvtReqs(Context ctx) {
		System.out.println("Getting available events");
		Set<EvtReq> evtReqSet = evtReqServ.getAvailableEvtReqs();
		if (evtReqSet != null) {
			System.out.println("got some event");
			ctx.status(200);
			ctx.json(evtReqSet);
		} else {
			System.out.println("no event found");
			ctx.status(404); 
		}
	}
	
	public static void addEvtReq(Context ctx) {
		EvtReq evtReq = ctx.bodyAsClass(EvtReq.class);
		evtReqServ.addEvtReq(evtReq);
		ctx.status(201);
	}
	
	public static void updateEvtReq(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		EvtReq evtReq = ctx.bodyAsClass(EvtReq.class);
		if (evtReq != null) {
			ctx.status(200);
			evtReqServ.updateEvtReq(evtReq);
		} else {
			ctx.status(404);
		}
	}
	
	public static void deleteEvtReq(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		EvtReq evtReq = evtReqServ.getEvtReqById(id);
		if (evtReq != null) {
			evtReqServ.removeEvtReq(evtReq);
			ctx.status(204); // 204 = no content
		}
		else {
			ctx.status(204);
		}
	}
	
	public static void initEvtReq(Context ctx) {
		Person loggedPerson = ctx.sessionAttribute("user");
		EvtReq evtReq = evtReqServ.getEvtReqById(Integer.valueOf(ctx.pathParam("id")));
		if (loggedPerson != null) {
			if (evtReq != null) {
				evtReqServ.initEvtReq(loggedPerson, evtReq);
				ctx.status(200);
			} else {
				ctx.status(409);
			}
		} else {
			ctx.status(401);
		}
		
	}

}
