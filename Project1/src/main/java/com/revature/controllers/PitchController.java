package com.revature.controllers;

import java.util.Set;

import com.revature.beans.Pitch;
import com.revature.beans.Person;
import com.revature.services.PitchService;
import com.revature.services.PitchServiceImpl;

import io.javalin.http.Context;

public class PitchController {
	private static PitchService pitchServ = new PitchServiceImpl();
	
	public static void getPitchById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch pitch = PitchServ.getPitchById(id);
		handleGet(ctx, pitch);
	}
	
	public static void getAllPitches(Context ctx) {
		Set<Pitch> pitches = pitchServ.getPitchs();
		handleGet(ctx, pitches);
	}
	
	public static void getViewablePitches(Context ctx) {
		System.out.println("Getting viewable pitches");
		Set<Pitch> pitchSet = pitchServ.getViewablePitches();
		handleGet(ctx, pitchSet);
	}
	
	public static void handleGet(Context ctx, Object requestedObject) {
		if (requestedObject != null) {
			ctx.status(200);
			ctx.json(requestedObject);
		} else {
			ctx.status(404);
		}
	}
	
	public static void addPitch(Context ctx) {
		Pitch pitch = ctx.bodyAsClass(Pitch.class);
		pitchServ.addPitch(pitch);
		ctx.status(201);
	}
	
	public static void updatePitch(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch pitch = ctx.bodyAsClass(Pitch.class);
		if (pitch != null) {
			ctx.status(200);
			pitchServ.updatePitch(pitch);
		} else {
			ctx.status(404);
		}
	}
	
	public static void deletePitch(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch pitch = pitchServ.getPitchById(id);
		if (pitch != null) {
			pitchServ.removePitch(pitch);
			ctx.status(204); // 204 = no content
		}
		else {
			ctx.status(204);
		}
	}
	
	public static void approveOrDenyPitch(Context ctx, boolean isApproval) {
		Person loggedPerson = ctx.sessionAttribute("user");
		Pitch pitch = pitchServ.getPitchById(Integer.valueOf(ctx.pathParam("id")));
		if (loggedPerson != null) {
			if (pitch != null) {
				if(isApproval) {
					pitchServ.approvePitch(loggedPerson, pitch);
				}else {
					pitchServ.denyPitch(loggedPerson, pitch);
				}
				ctx.status(200);
			} else {
				ctx.status(409);
			}
		} else {
			ctx.status(401);
		}
	}
}
