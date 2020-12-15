package controller;

import java.util.Set;

import exceptions.NonUniqueUsernameException;
import io.javalin.http.Context;
import models.Draft;
import models.Story;
import models.Users;
import servicespackage.story.Story_Service;
import servicespackage.story.Story_ServiceImpl;
import servicespackage.user.User_Service;
import servicespackage.user.User_ServiceImpl;

public class StoryController {
private static Story_Service storyServ = new Story_ServiceImpl();
	
	public static void getById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Story u = storyServ.getById(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	
	public static void add(Context ctx){
		Story u = ctx.bodyAsClass(Story.class);
		storyServ.add(u);
		ctx.status(201);
	}
	
	public static void update(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Story u = ctx.bodyAsClass(Story.class);
		if (u != null) {
			ctx.status(200);
			storyServ.update(u);
		} else {
			ctx.status(404);
		}
	}
	public static void getByUsersID(Context ctx) {
		Integer id = Integer.valueOf(ctx.queryParam("id"));
		Set<Story> paSet = storyServ.getByUserId(id);
		if (paSet != null) {
			ctx.status(200);
			ctx.json(paSet);
		} else {
			ctx.status(404);
		}
	}
}
