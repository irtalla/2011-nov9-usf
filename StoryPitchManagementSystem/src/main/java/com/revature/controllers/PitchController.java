package com.revature.controllers;

import java.util.Set;

import com.revature.beans.Pitch;
import com.revature.services.PitchService;
import com.revature.services.PitchServiceImpl;

import io.javalin.http.Context;

public class PitchController {
	private static PitchService pitchServ = new PitchServiceImpl();
	
	public static void getAllPitches(Context ctx)
	{
		Set<Pitch> pitches = pitchServ.getAll();
		if (pitches != null) {
			ctx.status(200);
			ctx.json(pitches);
		} else {
			ctx.status(404);
		}
	}
	public static void addPitch(Context ctx)
	{
		Pitch pitch = ctx.bodyAsClass(Pitch.class);
		pitchServ.addPitch(pitch);
		ctx.status(201);
	}
	public static void getPitchById(Context ctx)
	{
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Pitch pitch = pitchServ.getPitchById(id);
		if (pitch != null) {
			ctx.status(200);
			ctx.json(pitch);
		} else {
			ctx.status(404);
		}
	}
	public static void updatePitch(Context ctx)
	{
		Pitch pitch = ctx.bodyAsClass(Pitch.class);
		if (pitch != null) {
			ctx.status(200);
			pitchServ.updatePitch(pitch);
		} else {
			ctx.status(404);
		}
	}
}
