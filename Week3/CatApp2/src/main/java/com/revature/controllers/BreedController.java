package com.revature.controllers;

import com.revature.services.CatService;
import com.revature.services.CatServiceImpl;

import io.javalin.http.Context;

public class BreedController {
	private static CatService catServ = new CatServiceImpl();
	public static void getBreeds(Context ctx) {
		ctx.json(catServ.getBreeds());
		ctx.status(200);
	}
}
