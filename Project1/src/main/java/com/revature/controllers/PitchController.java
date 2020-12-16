package com.revature.controllers;

import com.revature.services.PersonServiceImpl;
import com.revature.services.PersonService;
import com.revature.beans.Pitch;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.services.PitchService;
import com.revature.services.PitchServiceImpl;
import java.util.Set;

import io.javalin.http.Context;

public class PitchController{
	private static PitchService pitchServ = new PitchServiceImpl();
	private static PersonService personServ = new PersonServiceImpl();

	public static void getPitchbyId(Context ctx){
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch pitch = pitchServ.getPitchById(id);
		if(pitch != null){
			ctx.status(200);
			ctx.json(pitch);
		}
		else{
			ctx.status(404);
		}
	}

	public static void getAllPitches(Context ctx){
		Set<Pitch> pitches = pitchServ.getPitches();
		if(pitches != null){
			ctx.status(200);
			ctx.json(pitches);
		}
		else{
			ctx.status(404);
		}
	}

	public static void getPitchesByStatus(Context ctx){
		String status = ctx.sessionAttribute("status");
		System.out.println("Getting pending pitches...");
		Set<Pitch> pitchSet = pitchServ.getPitchesByStatus(status);
		if(pitchSet != null){
			ctx.status(200);
			ctx.json(pitchSet);
		}
		else{
			ctx.status(404);
		}
	}

	public static void getPitchesByAuthor(Context ctx){
		System.out.println("Getting user pitches...");
		//Person author = ctx.sessionAttribute("user");
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Person author = personServ.getPersonById(id);
		Set<Pitch> pitchSet = pitchServ.getPitchesByAuthor(author);
		if(pitchSet != null){
			ctx.status(200);
			ctx.json(pitchSet);
		}
		else{
			ctx.status(404);
		}
	}

	public static void addPitch(Context ctx){
		Pitch pitch = ctx.bodyAsClass(Pitch.class);
		Person author = ctx.sessionAttribute("user");
		pitch.setAuthor(author);
		pitchServ.addPitch(pitch, author);
		ctx.status(201);
	}

	public static void updatePitch(Context ctx){
		//Integer id = Integer .valueOf(ctx.pathParam("id"));
		Pitch pitch = ctx.bodyAsClass(Pitch.class);
		if(pitch != null){
			ctx.status(200);
			pitchServ.updatePitch(pitch);
		}
		else{
			ctx.status(204);
		}
	}

	public static void deletePitch(Context ctx){
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch pitch = pitchServ.getPitchById(id);
		if(pitch != null){
			pitchServ.removePitch(pitch);
			ctx.status(200);
		}
		else{
			ctx.status(401);
		}
	}

	public static void approvePitch(Context ctx){
		Person currentUser = ctx.sessionAttribute("user");
		Pitch pitch = pitchServ.getPitchById(Integer.valueOf(ctx.pathParam("id")));
		if(currentUser != null){
			if(pitch != null){
				pitchServ.approvePitch(pitch, currentUser);
				ctx.status(200);
				if(pitchServ.reviewApprovals(pitch)){
					Status s = new Status();

					pitch.setStatus(s);
				}
			}
			else{
				ctx.status(409);
			}
		}
		else{
			ctx.status(401);
		}
	}

	public static void rejectPitch(Context ctx){
		Person currentUser = ctx.sessionAttribute("user");
		String editorNotes = ctx.sessionAttribute("editorNotes");
		Pitch pitch = pitchServ.getPitchById(Integer.valueOf(ctx.pathParam("id")));
		if(currentUser != null){
			if(pitch != null){
				pitchServ.rejectPitch(pitch, currentUser, editorNotes);
				ctx.status(200);
			}
			else{
				ctx.status(409);
			}
		}
		else{
			ctx.status(401);
		}
	}
}
