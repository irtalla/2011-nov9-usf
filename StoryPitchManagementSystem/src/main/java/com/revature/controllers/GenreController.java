package com.revature.controllers;

import com.revature.services.PitchService;
import com.revature.services.PitchServiceImpl;

import io.javalin.http.Context;

public class GenreController {
	private static PitchService pitchServ = new PitchServiceImpl();
	public static void getGenres(Context ctx) {
		ctx.json(pitchServ.getGenres());
		ctx.status(200);
	}
}
