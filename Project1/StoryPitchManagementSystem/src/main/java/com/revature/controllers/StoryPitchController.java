package com.revature.controllers;

import java.util.Set;

import com.revature.beans.Genre;
import com.revature.beans.GenreSpecialization;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.beans.StoryPitch;
import com.revature.beans.Type;
import com.revature.services.GenreSpecializationServices;
import com.revature.services.GenreSpecializationServicesImpl;
import com.revature.services.PersonServices;
import com.revature.services.PersonServicesImpl;
import com.revature.services.StoryPitchServices;
import com.revature.services.StoryPitchServicesImpl;

import io.javalin.http.Context;

public class StoryPitchController {
	private static PersonServices personServ = new PersonServicesImpl();
	private static StoryPitchServices storyPitchServ = new StoryPitchServicesImpl();
	private static GenreSpecializationServices gsServ = new GenreSpecializationServicesImpl();
	
	public static void acceptChanges(Context ctx) {
		System.out.println("Accepting changes to story");
		Integer id = Integer.valueOf(ctx.queryParam("storyID"));
		StoryPitch sp = storyPitchServ.getByStoryPitchID(id);
		//System.out.println(sp);
		if (sp != null) {
			Status status = sp.getStatus();
			status.setStatusID(6);
			sp.setStatus(status);
			storyPitchServ.updateStoryPitch(sp);
			Person p = personServ.getByPersonID(sp.getPersonID());
			ctx.sessionAttribute("user", p);
			ctx.json(p);
			ctx.status(200);
			//System.out.println(sp);
		} else {
			//System.out.println(sp);
			ctx.status(404);
		}
	}
	
	public static void rejectChanges(Context ctx) {
		System.out.println("Rejecting changes to story");
		Integer id = Integer.valueOf(ctx.queryParam("storyID"));
		StoryPitch sp = storyPitchServ.getByStoryPitchID(id);
		//System.out.println(sp);
		if (sp != null) {
			Status status = sp.getStatus();
			status.setStatusID(9);
			sp.setStatus(status);
			Integer points = 0; // checking how many points to add back to author since they are withdrawing this story pitch
			switch(sp.getType().getTypeID()) {
			case 1:
				points = 50;
				break;
			case 2:
				points = 25;
				break;
			case 3:
				points = 20;
				break;
			case 4:
				points = 10;
				break;
			default:{
					break;
				}
			}
			System.out.println(points);
			Person author = personServ.getByPersonID(sp.getPersonID());
			System.out.println(author.getPoints());
			author.setPoints(author.getPoints()+points);
			System.out.println(author.getPoints());
			personServ.updatePerson(author);
			storyPitchServ.updateStoryPitch(sp);
			Person p = personServ.getByPersonID(sp.getPersonID());
			System.out.println(p.getPoints());
			ctx.sessionAttribute("user", p);
			ctx.json(p);
			ctx.status(200);
			//System.out.println(sp);
		} else {
			//System.out.println(sp);
			ctx.status(404);
		}
	}
	
	public static void getStoryPitchByID(Context ctx) {
		System.out.println("Getting story by ID");
		Integer id = Integer.valueOf(ctx.queryParam("storyID"));
		StoryPitch sp = storyPitchServ.getByStoryPitchID(id);
		//System.out.println(sp);
		if (sp != null) {
			ctx.status(200);
			//System.out.println(sp);
			ctx.json(sp);
			ctx.sessionAttribute("storyPitch", sp);
		} else {
			//System.out.println(sp);
			ctx.status(404);
		}
	}
	
	public static void getAll(Context ctx) {
		Set<StoryPitch> sps = storyPitchServ.getAll();
		if (sps != null) {
			ctx.status(200);
			ctx.json(sps);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getMyStoryPitches(Context ctx) {
		System.out.println("Getting your story pitches");
		Integer id = Integer.valueOf(ctx.pathParam("StoryId"));
		Set<StoryPitch> spSet = storyPitchServ.getByAuthorID(id);
		if (spSet != null) {
			ctx.status(200);
			ctx.json(spSet);
		} else {
			ctx.status(404);
		}
	}
	
	public static void addStoryPitch(Context ctx) {
		StoryPitch sp = ctx.bodyAsClass(StoryPitch.class);
		storyPitchServ.addStoryPitch(sp);
		ctx.status(201);
	}
	
	public static void updateStoryPitch(Context ctx) {
		System.out.println("Updating a story in the controller");
		StoryPitch sp = new StoryPitch();
		Integer sid = Integer.valueOf(ctx.queryParam("storyID"));
		sp.setStoryID(sid);
		Integer pid = Integer.valueOf(ctx.queryParam("author"));
		sp.setPersonID(pid);
		Integer gid = Integer.valueOf(ctx.queryParam("genreID"));
		Genre g = new Genre();
		g.setGenreID(gid);
		sp.setGenre(g);
		Integer tid = Integer.valueOf(ctx.queryParam("typeID"));
		Type t = new Type();
		t.setTypeID(tid);
		sp.setType(t);
		String title = String.valueOf(ctx.queryParam("title"));
		sp.setTitle(title);
		String tagline = String.valueOf(ctx.queryParam("tagline"));
		sp.setTagline(tagline);
		String detailedDescription = String.valueOf(ctx.queryParam("detailedDescription"));
		sp.setDetailedDescription(detailedDescription);
		String draft = String.valueOf(ctx.queryParam("draft"));
		sp.setDraft(draft);
		Long dateNum = Long.valueOf(ctx.queryParam("completionDate")) + 86400000; //adds the numnber of milliseconds in a day to avoid being one day off
		java.sql.Date completionDate = new java.sql.Date(dateNum);  
		sp.setCompletionDate(completionDate);
		System.out.println(sp);
		storyPitchServ.updateStoryPitch(sp);
	}
	
	public static void editStoryPitch(Context ctx) {
		System.out.println("Updating a story in the controller");
		
		Integer sid = Integer.valueOf(ctx.queryParam("storyID"));
		StoryPitch sp = storyPitchServ.getByStoryPitchID(sid);
		String title = String.valueOf(ctx.queryParam("title"));
		sp.setTitle(title);
		String tagline = String.valueOf(ctx.queryParam("tagline"));
		sp.setTagline(tagline);
		Long dateNum = Long.valueOf(ctx.queryParam("completionDate")) + 86400000; //adds the numnber of milliseconds in a day to avoid being one day off
		java.sql.Date completionDate = new java.sql.Date(dateNum);  
		sp.setCompletionDate(completionDate);
		Status status = sp.getStatus();
		status.setStatusID(4);
		status.setName("Pitch Modified - Author Approval Needed");
		sp.setStatus(status);
		System.out.println(sp);
		storyPitchServ.updateStoryPitch(sp);
	}
	
	public static void deleteStoryPitch(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		StoryPitch sp = storyPitchServ.getByStoryPitchID(id);
		if (sp != null) {
			storyPitchServ.deleteStoryPitch(sp);
			ctx.status(204); // 204 = no content
		}
		else {
			ctx.status(204);
		}
	}
	
	public static void submitStory(Context ctx) {
		System.out.println("Submitting a story in the controller");
		StoryPitch sp = new StoryPitch();
		Integer pid = Integer.valueOf(ctx.queryParam("author"));
		sp.setPersonID(pid);
		Integer gid = Integer.valueOf(ctx.queryParam("genreID"));
		Genre g = new Genre();
		g.setGenreID(gid);
		sp.setGenre(g);
		Integer tid = Integer.valueOf(ctx.queryParam("typeID"));
		Type t = new Type();
		t.setTypeID(tid);
		sp.setType(t);
		String title = String.valueOf(ctx.queryParam("title"));
		sp.setTitle(title);
		String tagline = String.valueOf(ctx.queryParam("tagline"));
		sp.setTagline(tagline);
		String detailedDescription = String.valueOf(ctx.queryParam("detailedDescription"));
		sp.setDetailedDescription(detailedDescription);
		String draft = String.valueOf(ctx.queryParam("draft"));
		sp.setDraft(draft);
		Long dateNum = Long.valueOf(ctx.queryParam("completionDate")) + 86400000; //adds the numnber of milliseconds in a day to avoid being one day off
		java.sql.Date completionDate=new java.sql.Date(dateNum);  
		sp.setCompletionDate(completionDate);
		Status status = new Status();
		Set<GenreSpecialization> gspecs = gsServ.getByGenredID(sp.getGenre().getGenreID());
		System.out.println(gspecs);
		boolean existsAssistantGenreEditor = false;
		for(GenreSpecialization gs : gspecs) {
			Person p = personServ.getByPersonID(gs.getPersonID());
			if(p.getRoleID() == 3) {
				existsAssistantGenreEditor = true;
			}
		}
		System.out.println(existsAssistantGenreEditor);
		if(existsAssistantGenreEditor) {
			status.setStatusID(1);
			status.setName("Pitch Under Review - Assistant Genre Editor Approval Needed");
		}
		else {
			status.setStatusID(2);
			status.setName("Pitch Under Review - General Editor Approval Needed");
		}
		Person author = personServ.getByPersonID(sp.getPersonID());
		if(tid == 1 && (author.getPoints() < 50)) {
			status.setStatusID(0);
			status.setName("On Hold");
		}
		if(tid == 2 && (author.getPoints() < 25)) {
			status.setStatusID(0);
			status.setName("On Hold");
		}
		if(tid == 3 && (author.getPoints() < 20)) {
			status.setStatusID(0);
			status.setName("On Hold");
		}
		if(tid == 4 && (author.getPoints() < 10)) {
			status.setStatusID(0);
			status.setName("On Hold");
		}
		sp.setStatus(status);
		System.out.println(sp);
		storyPitchServ.addStoryPitch(sp);
		Person p = personServ.getByPersonID(author.getPersonID());
		ctx.sessionAttribute("user", p);
		ctx.json(p);
		ctx.status(200);
	}
	
	public static void resubmitStory(Context ctx) {
		System.out.println("Resubmitting a story in the controller");
		Integer sid = Integer.valueOf(ctx.queryParam("storyID"));
		StoryPitch sp = storyPitchServ.getByStoryPitchID(sid);
		Status status = sp.getStatus();
		Set<GenreSpecialization> gspecs = gsServ.getByGenredID(sp.getGenre().getGenreID());
		boolean existsAssistantGenreEditor = false;
		for(GenreSpecialization gs : gspecs) {
			Person p = personServ.getByPersonID(gs.getPersonID());
			if(p.getRoleID() == 3) {
				existsAssistantGenreEditor = true;
			}
		}
		if(existsAssistantGenreEditor) {
			status.setStatusID(1);
			status.setName("Pitch Under Review - Assistant Genre Editor Approval Needed");
		}
		else {
			status.setStatusID(2);
			status.setName("Pitch Under Review - General Editor Approval Needed");
		}
		System.out.println(sp);
		storyPitchServ.updateStoryPitch(sp);
		Person author = personServ.getByPersonID(sp.getPersonID());
		// adjusting the author's points based off of the type of story submitted
		Integer points = 0;
		switch(sp.getType().getTypeID()) {
		case 1:
			points = 50;
			break;
		case 2:
			points = 25;
			break;
		case 3:
			points = 20;
			break;
		case 4:
			points = 10;
			break;
		}
		System.out.println(sp);
		System.out.println(points);
		author.setPoints(author.getPoints() - points);
		personServ.updatePerson(author);
		ctx.sessionAttribute("user", author);
		ctx.json(author);
		ctx.status(200);
	}
	
}
