package com.revature.controller;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Committee;
import com.revature.beans.Genre;
import com.revature.beans.Person;
import com.revature.beans.Pitch;
import com.revature.service.GenreService;
import com.revature.service.GenreServiceImpl;
import com.revature.service.PersonService;
import com.revature.service.PersonServiceImpl;
import com.revature.service.PitchPriorityService;
import com.revature.service.PitchPriorityServiceImpl;
import com.revature.service.PitchService;
import com.revature.service.PitchServiceImpl;
import com.revature.service.PitchStageService;
import com.revature.service.PitchStageServiceImpl;
import com.revature.service.StatusService;
import com.revature.service.StatusServiceImpl;
import com.revature.service.StoryTypeService;
import com.revature.service.StoryTypeServiceImpl;

import io.javalin.http.Context;

public class PitchController {
	private static PitchService pServ = new PitchServiceImpl();
	private static PersonService personServ = new PersonServiceImpl();
	private static GenreService gServ = new GenreServiceImpl();
	private static StoryTypeService stServ = new StoryTypeServiceImpl();
	private static StatusService statServ = new StatusServiceImpl();
	private static PitchPriorityService ppServ = new PitchPriorityServiceImpl();
	private static PitchStageService psServ = new PitchStageServiceImpl();
	
	public static void getPitchById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch pitch = pServ.getPitchById(id);
		if(pitch != null) {
			ctx.status(200);
			ctx.json(pitch);
		}else {
			ctx.status(404);
		}
	}
	
	public static void addPitch(Context ctx) {
		Pitch pitch = ctx.bodyAsClass(Pitch.class);

		pitch.setGenre(gServ.getGenreById(pitch.getGenre().getId()));
		pitch.setStory_type(stServ.getStoryTypeById(pitch.getStory_type().getId()));
		pitch.setStatus(statServ.getStatusById(pitch.getStatus().getId()));
		pitch.setPriority(ppServ.getPitchPriorityById(pitch.getPriority().getId()));
		pitch.setStage(psServ.getPitchStageById(pitch.getStage().getId()));
		pServ.addPitch(pitch);
		Person p = ctx.sessionAttribute("user");
		p.getPitches().add(pitch);
		personServ.updatePerson(p);
		ctx.status(201);
	}
	
	public static void updatePitch(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch pitch = pServ.getPitchById(id);
		pServ.updatePitch(pitch);
		ctx.status(202);
	}
	
	public static void deletePitch(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch pitch = pServ.getPitchById(id);
		pServ.deletePitch(pitch);
		ctx.status(204);
	}
	
	public static void getPitches(Context ctx) {
		Set<Pitch> pitches = pServ.getPitches();
		if (pitches != null) {
			ctx.status(200);
			ctx.json(pitches);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getCommitteePitches(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Set<Pitch> commPitches = pServ.getCommitteePitches(id);
		System.out.println("after the service call\n"
				+ "number of pitches: "+commPitches.size());
		if(commPitches !=null) {
			ctx.status(200);
			ctx.json(commPitches);
		}else {
			ctx.status(404);
			System.out.println("they told me this code was dead! how are you here! get out!");
		}
	}

	private static Pitch getPitchByCommitteeId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
