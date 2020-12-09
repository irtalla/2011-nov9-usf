package com.revature.controller;

import com.revature.beans.Pitch;
import com.revature.service.PitchService;
import com.revature.service.PitchServiceImpl;

import io.javalin.http.Context;

public class PitchController {
	private static PitchService pServ = new PitchServiceImpl();
	
	
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
		pServ.addPitch(pitch);
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

}
