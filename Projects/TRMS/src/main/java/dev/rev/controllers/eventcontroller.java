package dev.rev.controllers;

import java.util.Set;


import dev.rev.beans.event;
import dev.rev.services.eventservice;
import dev.rev.services.eventserviceimp;
import io.javalin.http.Context;

public class eventcontroller {
	
	private static eventservice es=new eventserviceimp();
	
	public static void getallevents(Context ctx){
		Set<event> cats = es.getallevent();
				if (cats != null) {
			ctx.status(200);
			ctx.json(cats);
		} else {
			ctx.status(404);
		}
		
	}
	public static void geteventbyid(Context ctx) {
	//	event event=es.getbyid(ctx.pathParam("id"));
		Integer id= Integer.valueOf(ctx.pathParam("id"));
		event ev=es.getbyid(id);
		if(ev !=null ) {
			ctx.status(200);
			ctx.json(ev);
		}else {
			ctx.status(400);
		}
	}
	
	

}
