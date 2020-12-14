package com.revature.controllers;

import java.util.Set;


import com.revature.beans.Event;
import com.revature.beans.EventType;
import com.revature.services.EventService;
import com.revature.services.EventServiceImpl;

import io.javalin.http.Context;

public class EventController {
	private static EventService evServ = new EventServiceImpl();
	
	public static void getEventById(Context ctx)
	{
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Event evt = evServ.getEventById(id);
		
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
	
	public static void getAllEvents(Context ctx)
	{
		Set<Event> results = evServ.getEvents();
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
	
	public static void addEvent(Context ctx)
	{
		Event evt = ctx.bodyAsClass(Event.class);
		int id = evServ.addEvent(evt);
		ctx.json(id);
		ctx.status(201);
	}
	
	public static void deleteEvent(Context ctx)
	{
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Event evt = evServ.getEventById(id);
		evServ.removeEvent(evt);
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
	
	public static void getEventTypeById(Context ctx)
	{
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		EventType type = evServ.getEventTypeById(id);
		
		if (type != null) 
		{
			ctx.status(200);
			ctx.json(type);
		} 
		else 
		{
			ctx.status(404);
		}
		
	}
	
	public static void getAllEventTypes(Context ctx)
	{
		Set<EventType> results = evServ.getEventTypes();
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
}
