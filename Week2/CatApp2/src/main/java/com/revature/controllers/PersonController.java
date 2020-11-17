package com.revature.controllers;

import com.revature.beans.Person;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;

import io.javalin.http.Context;

public class PersonController {
	public static PersonService serv = new PersonServiceImpl();
	
	public static void logIn(Context ctx) {
		String username = ctx.pathParam("user");
		String password = ctx.pathParam("pass");
		Person p = serv.getPersonByUsername(username);
		if (p != null)
		{
			if (p.getPassword().equals(password))
			{
				ctx.status(200);
				ctx.json(p);
			}
			else
			{
				//password mismatch
				ctx.status(400);
			}
		}
		else
		{
			//username not found
			ctx.status(404);
		}
		
	}
	
	public static void logOut(Context ctx) {
		
	}
	
	public static void registerUser(Context ctx) {
		
	}
	
	public static void getUserById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Person p = serv.getPersonById(id);
		if (p != null) {
			ctx.status(200);
			ctx.json(p);
		} else {
			ctx.status(404);
		}
		
		
	}
	
	public static void updateUser(Context ctx) {
		
	}
	
	public static void deleteUser(Context ctx) {
		
	}
}
