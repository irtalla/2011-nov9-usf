package com.revature.controller;

import java.util.List;
import java.util.Set;

import com.revature.models.Genre;
import com.revature.models.Pitch;
import com.revature.models.PitchStage;
import com.revature.models.Priority;
import com.revature.models.ReviewStatus;
import com.revature.models.StoryType;
import com.revature.models.User;
import com.revature.services.PitchService;
import com.revature.services.PitchServiceImpl;

import io.javalin.http.Context;

public class PitchController {
	private static PitchService pitchServ = new PitchServiceImpl();
	
	public static void submitPitch(Context ctx) {
		System.out.println("Submitting a pitch");
		Pitch newPitch = pitchServ.parseContext(ctx.body());
		try {
			Integer newId = pitchServ.addPitch(newPitch);
			pitchServ.updateFilePaths(newId);
		} catch (Exception e) {
			System.out.println("An exception occurred");
			ctx.status(400);
			return;
		}
		ctx.status(200);
	}
	
	public static void getPitchById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch p = pitchServ.getPitchById(id);
		if (p != null) {
			ctx.status(200);
			ctx.json(p);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getPitchesByAuthor(Context ctx) {
		User u = ctx.bodyAsClass(User.class);
		Set<Pitch> p = pitchServ.getPitchesByAuthor(u);
		if (p != null) {
			ctx.status(200);
			ctx.json(p);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getPitchByGenre(Context ctx) {
		Integer genreId = Integer.valueOf(ctx.pathParam("genre"));
		Boolean withinGenre = Boolean.valueOf(ctx.pathParam("within_genre"));
		Set<Pitch> p = pitchServ.getPitchesByGenre(genreId, withinGenre);
		if (p != null) {
			ctx.status(200);
			ctx.json(p);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getPitchesByStoryType(Context ctx) {
		StoryType st = ctx.bodyAsClass(StoryType.class);
		Set<Pitch> p = pitchServ.getPitchesByStoryType(st);
		if (p != null) {
			ctx.status(200);
			ctx.json(p);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getPitchesByPitchStage(Context ctx) {
		PitchStage ps = ctx.bodyAsClass(PitchStage.class);
		Set<Pitch> p = pitchServ.getPitchesByPitchStage(ps);
		if (p != null) {
			ctx.status(200);
			ctx.json(p);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getPitchesByReviewStatus(Context ctx) {
		ReviewStatus rs = ctx.bodyAsClass(ReviewStatus.class);
		Set<Pitch> p = pitchServ.getPitchesByReviewStatus(rs);
		if (p != null) {
			ctx.status(200);
			ctx.json(p);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getPitchesByPriority(Context ctx) {
		String priority = String.valueOf(ctx.pathParam("priority"));
		Set<Pitch> p = pitchServ.getPitchesByPriority(priority);
		if (p != null) {
			ctx.status(200);
			ctx.json(p);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getAllPitches(Context ctx) {
		Set<Pitch> pitches = pitchServ.getAllPitches();
		if (pitches != null) {
			ctx.status(200);
			ctx.json(pitches);
		} else {
			ctx.status(404);
		}
	}
	
	public static void updatePitch(Context ctx) {
		Pitch tempPitch = ctx.bodyAsClass(Pitch.class);
		try {
			pitchServ.updatePitch(tempPitch);
		} catch (Exception e) {
			System.out.println("An exception has occured");
			ctx.status(200);
			return;
		}
		ctx.status(202);
	}
	
	public static void deletePitch(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch pitch = pitchServ.getPitchById(id);
		pitchServ.deletePitch(pitch);
		ctx.status(204);
	}
	
	// Fetching additional classes for front-end
	public static void getGenres(Context ctx) {
		List<Genre> genres = pitchServ.getAllGenre();
		if (genres != null) {
			ctx.status(200);
			ctx.json(genres);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getStoryTypes(Context ctx) {
		List<StoryType> types = pitchServ.getAllStoryType();
		if (types != null) {
			ctx.status(200);
			ctx.json(types);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getPitchStages(Context ctx) {
		List<PitchStage> stages = pitchServ.getAllPitchStage();
		if (stages != null) {
			ctx.status(200);
			ctx.json(stages);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getReviewStatus(Context ctx) {
		List<ReviewStatus> status = pitchServ.getAllReviewStatus();
		if (status != null) {
			ctx.status(200);
			ctx.json(status);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getPriorities(Context ctx) {
		List<String> priorities = pitchServ.getPriorities();
		if (priorities != null) {
			ctx.status(200);
			ctx.json(priorities);
		} else {
			ctx.status(404);
		}
	}
}
