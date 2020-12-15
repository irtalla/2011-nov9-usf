package com.revature.controllers;



import java.util.Set;

import com.revature.beans.EvtReq;
import com.revature.beans.Person;
import com.revature.services.EvtReqService;
import com.revature.services.EvtReqServiceImpl;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;

import io.javalin.http.Context;

public class EvtReqController {
	private static EvtReqService evtReqServ = new EvtReqServiceImpl();
	private static PersonService personServ = new PersonServiceImpl();
	
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
	
	public static void approveEvtReqs(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Person p = ctx.sessionAttribute("user");
		if (p != null && personServ.isApprover(p.getId()) == true) {
			boolean result = evtReqServ.approveEvtReq(id, p.getUsername());
			if (result == true) {
				ctx.status(200);
				
//				String html = "<!DOCTYPE html>"
//						+ "<html lang=\"en\">"
//						+ "  <head>"
//						+ "    <meta charset=\"utf-8\">"
//						+ "    <title>The event request is now approved</title>"
//						+ "    <link rel=\"stylesheet\" href=\"http://localhost:8080/css/main.css\">"
//						+ "    <script src=\"http://localhost:8080/js/main.js\"></script>"
//						+ "  </head>"
//						+ "  <body>"
//						+ "   <h2>The event request is now approved, <a href=\"http://localhost:8080/EvtReqToApprove.html\"> Go back</a> </h2>"
//						+ "  </body>"
//						+ "</html>";
				
				String html = "<!DOCTYPE html>"
						+ "<html lang=\"en\">"
						+ "	<head>"
						+ "		<title>TRMS | Event Requests To Approve</title>"
						+ "		<meta charset=\"utf-8\" />"
						+ "		<meta name=\"viewport\" content=\"width=device-width, initital=scale=1\" />"
						+ "		<link rel=\"stylesheet\" href=\"http://localhost:8080/css/main.css\">"  
						+ "		<link rel=\"stylesheet\" href=\"http://localhost:8080/css/myEvtReqs.css\" />"
						+ "		<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1\" crossorigin=\"anonymous\" >"
						+ "	</head>"
						+ "	<body>"
						+ "	<navbar id=\"navBar\">"
						+ "	</navbar>"
						+ " <h4>The event request is now approved, <a href=\"http://localhost:8080/viewEvtReqs.html\"> Return to view events page</a> </h2>"
						+ "	<h3 class=\"my-event-requests\">Event Requests To Approve</h3>"
						+ "	<main>"
			            + "	<div class=\"table-responsive col-lg-12\" id=\"evtReqSection\">"     	
			            + "	</div>"    
			            + "	</main>"
			        	+ "<script src=\"http://localhost:8080/js/main.js\"></script>"
			        	+ "<script src=\"http://localhost:8080/js/EvtReqToApprove.js\"></script>"
			        	+ "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW\" crossorigin=\"anonymous\"></script>"
			        	+ "</body>"
			        	+ "</html>";
						
				
				ctx.html(html);
			} else {
				ctx.status(404);
			}
		} else {
			ctx.status(404);
		}
		
	}
	
	public static void getAllEvtReqs(Context ctx) {
		System.out.println("Getting available events");
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
	
	public static void getEvtReqsToApprove(Context ctx) {
		System.out.println("Getting events request to approve");
		Set<EvtReq> evtReqSet = evtReqServ.getPendingEvtReqs();
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
		
		System.out.println("get the person from the session");
		Person p = ctx.sessionAttribute("user");
		
		if (p != null) {
			System.out.println("Logged in as " + p.getUsername());
			
			evtReq.setPerson_id(p.getId()); //set the id of logged in user from session to the new evtReq
			EvtReq insertedEvtRe = evtReqServ.addEvtReq2(evtReq); 
			
			//update evtreq to list of person
			p.getEvtReqs().add(insertedEvtRe);
			ctx.sessionAttribute("user", p);
			
			ctx.status(201);
			
		} else {
			System.out.println("the user is not logged in");
			ctx.status(400);
		}	
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
		System.out.println("A");
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
