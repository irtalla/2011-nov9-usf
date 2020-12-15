package controller;

import exceptions.NonUniqueUsernameException;
import io.javalin.http.Context;
import models.Story_Type;
import models.Users;
import servicespackage.story_type.Story_Type_Service;
import servicespackage.story_type.Story_Type_ServiceImpl;
import servicespackage.user.User_Service;
import servicespackage.user.User_ServiceImpl;

public class Story_TypeController {
private static Story_Type_Service stServ = new Story_Type_ServiceImpl();
	
	public static void getById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Story_Type u = stServ.getById(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	
	public static void add(Context ctx) {
		Story_Type u = ctx.bodyAsClass(Story_Type.class);
		stServ.add(u);
		ctx.status(201);
	}
	
	public static void update(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Story_Type u = ctx.bodyAsClass(Story_Type.class);
		if (u != null) {
			ctx.status(200);
			stServ.update(u);
		} else {
			ctx.status(404);
		}
	}
}
