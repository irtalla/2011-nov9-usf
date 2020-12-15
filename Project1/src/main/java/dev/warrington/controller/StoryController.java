package dev.warrington.controller;

import java.util.Set;

import dev.warrington.beans.Person;
import dev.warrington.beans.Story;
import dev.warrington.services.StoryService;
import dev.warrington.services.StoryServiceImpl;
import io.javalin.http.Context;

public class StoryController {
	
	private static StoryService storyServ = new StoryServiceImpl();

	public static void getMyStories(Context ctx) {
		System.out.println("Retrieving stories");
		String personId = ctx.queryParam("id");
		String roleId = ctx.queryParam("role");
		Set<Story> stories = storyServ.getMyStories(Integer.parseInt(personId), Integer.parseInt(roleId));
		
		if (stories != null) {
			ctx.status(200);
			ctx.json(stories);
		} else {
			ctx.status(404);
		}
	}
	
}