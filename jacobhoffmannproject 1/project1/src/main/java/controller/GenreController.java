package controller;

import io.javalin.http.Context;
import models.Genre;
import models.Story;
import servicespackage.Genre.Genre_Service;
import servicespackage.Genre.Genre_ServiceImpl;
import servicespackage.story.Story_Service;
import servicespackage.story.Story_ServiceImpl;

public class GenreController {
private static Genre_Service gserv = new Genre_ServiceImpl();
	
	public static void getById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Genre u = gserv.getById(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	
	public static void add(Context ctx){
		Genre u = ctx.bodyAsClass(Genre.class);
		gserv.add(u);
		ctx.status(201);
	}
	
	public static void update(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Genre u = ctx.bodyAsClass(Genre.class);
		if (u != null) {
			ctx.status(200);
			gserv.update(u);
		} else {
			ctx.status(404);
		}
	}
}
