package com.revature.controllers;

import java.util.Set;

import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.services.CatService;
import com.revature.services.CatServiceImpl;

import io.javalin.http.Context;

public class CatController {
	private static CatService catServ = new CatServiceImpl();
	
	public static void getCatById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Cat cat = catServ.getCatById(id);
		if (cat != null) {
			ctx.status(200);
			ctx.json(cat);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getAllCats(Context ctx) {
		Set<Cat> cats = catServ.getCats();
		if (cats != null) {
			ctx.status(200);
			ctx.json(cats);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getAvailableCats(Context ctx) {
		System.out.println("Getting available cats");
		Set<Cat> catSet = catServ.getAvailableCats();
		if (catSet != null) {
			ctx.status(200);
			ctx.json(catSet);
		} else {
			ctx.status(404);
		}
	}
	
	public static void addCat(Context ctx) {
		Cat cat = ctx.bodyAsClass(Cat.class);
		catServ.addCat(cat);
		ctx.status(201);
	}
	
	public static void updateCat(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Cat cat = ctx.bodyAsClass(Cat.class);
		if (cat != null) {
			ctx.status(200);
			catServ.updateCat(cat);
		} else {
			ctx.status(404);
		}
	}
	
	public static void deleteCat(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Cat cat = catServ.getCatById(id);
		if (cat != null) {
			catServ.removeCat(cat);
			ctx.status(204); // 204 = no content
		}
		else {
			ctx.status(204);
		}
	}
	
	public static void adoptCat(Context ctx) {
		Person loggedPerson = ctx.sessionAttribute("user");
		Cat cat = catServ.getCatById(Integer.valueOf(ctx.pathParam("id")));
		if (loggedPerson != null) {
			if (cat != null) {
				catServ.adoptCat(loggedPerson, cat);
				ctx.status(200);
			} else {
				ctx.status(409);
			}
		} else {
			ctx.status(401);
		}
		
	}
}
