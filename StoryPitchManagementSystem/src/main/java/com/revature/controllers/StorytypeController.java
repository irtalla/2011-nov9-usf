package com.revature.controllers;

import com.revature.services.PitchService;
import com.revature.services.PitchServiceImpl;

import io.javalin.http.Context;

public class StorytypeController {
	private static PitchService pitchServ = new PitchServiceImpl();
	public static void getStorytypes(Context ctx) {
		ctx.json(pitchServ.getTypes());
		ctx.status(200);
	}
}
