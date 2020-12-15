package dev.warrington.controller;

import java.time.LocalDate;
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
		//System.out.println(personId);
		//System.out.println(roleId);
		Set<Story> stories = storyServ.getMyStories(Integer.parseInt(personId), Integer.parseInt(roleId));
		
		if (stories != null) {
			ctx.status(200);
			ctx.json(stories);
		} else {
			ctx.status(404);
		}
	}
	
	public static void addStory(Context ctx) {
		Story s = ctx.bodyAsClass(Story.class);
		String[] splitDate = s.getCompletionDate().toString().split("-");
		s.setCompletionDate(splitDate[1] + "-" + splitDate[2] + "-" + splitDate[3]);
		s.setSubmissionDate(LocalDate.now());
		storyServ.addStory(s);
		ctx.status(201);
	}
	
	public static void approve(Context ctx) {
		Integer id = Integer.parseInt(ctx.queryParam("id"));
		storyServ.approve(id);
	}
	
	public static void deny(Context ctx) {
		Integer id = Integer.parseInt(ctx.queryParam("id"));
		storyServ.deny(id);
	}
	
	public static void req(Context ctx) {
		Integer id = Integer.parseInt(ctx.queryParam("id"));
		storyServ.req(id);
	}
}