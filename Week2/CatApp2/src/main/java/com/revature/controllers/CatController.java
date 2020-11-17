package com.revature.controllers;

import java.util.Set;

import com.revature.beans.Cat;
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
		
	}
	
	public static void getAvailableCats(Context ctx) {
	}
	
	public static void addCat(Context ctx) {
		
	}
	
	public static void updateCat(Context ctx) {
	}
	
	public static void deleteCat(Context ctx) {
		
	}
	
	public static void adoptCat(Context ctx) {
		
	}
}
