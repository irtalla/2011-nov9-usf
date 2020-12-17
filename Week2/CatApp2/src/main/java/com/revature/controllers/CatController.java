package com.revature.controllers;

import java.util.Set;

import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.beans.Role;
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
		Cat cat = ctx.bodyAsClass(Cat.class);
		
		//System.out.println(cat.toString());
		Integer id = catServ.addCat(cat);
		if(id != null) {
			ctx.status(200);
			ctx.json(cat);
		}else {
			ctx.status(400);
		}
	}
	
	public static void updateCat(Context ctx) {
		
	}
	
	public static void deleteCat(Context ctx) {
		
	}
	
	public static void adoptCat(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Cat cat = catServ.getCatById(id);
		
//		Integer personId = Integer.parseInt(ctx.body());
		//Person adopter = ctx.bodyAsClass(Person.class);
		
		//not checking if user is logged in
		
		//placeholder person, because don't know how person will be identifiable from client
		Person p = new Person();
		p.setId(1);
		p.setUsername("sierra");
		p.setPassword("pass");
//		Role r = new Role();
		if(p != null) {
			if(cat.getStatus().getName() == "Adopted") {
				Set<Cat> adoptable = catServ.getAvailableCats();
				ctx.json(adoptable);
			}else {		
				catServ.adoptCat(p, cat);
				ctx.json(cat);
				ctx.status(200);
			}		
		}else {
			ctx.status(401);
		}
	}
}
