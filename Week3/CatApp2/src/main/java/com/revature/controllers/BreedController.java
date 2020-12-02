package com.revature.controllers;

import com.revature.beans.Breed;
import com.revature.data.BreedPostgres;

import io.javalin.http.Context;

public class BreedController {
	private static BreedPostgres breedPostgres = new BreedPostgres();
	
	public static void addBreed(Context ctx) {
		Breed breed = ctx.bodyAsClass(Breed.class);
		breedPostgres.add(breed);
		ctx.status(201);
	}
}
