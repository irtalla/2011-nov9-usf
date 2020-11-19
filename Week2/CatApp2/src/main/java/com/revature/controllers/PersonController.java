package com.revature.controllers;

import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.services.CatService;
import com.revature.services.CatServiceImpl;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;

import io.javalin.http.Context;

public class PersonController {
	private static PersonService personServ = new PersonServiceImpl();
	
	public static void logIn(Context ctx) {
		
	}
	
	public static void logOut(Context ctx) {
		
	}
	
	public static void registerUser(Context ctx) {
		//
	}
	
	public static void getUserById(Context ctx) {
		
	}
	
	public static void updateUser(Context ctx) {
		
	}
	
	public static void deleteUser(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Person person = personServ.getPersonById(id);
		if (person != null) {
			ctx.status(200);
			ctx.json(person);
		} else {
			ctx.status(404);
		}
	}
}
