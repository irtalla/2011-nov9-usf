package com.revature.controller;

import com.revature.beans.Person;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;

import io.javalin.http.Context;

public class PersonController {
	private static PersonService personServ = new PersonServiceImpl();
	
	public static void checkLogin(Context ctx) {
		System.out.println("Checking login");
		Person p = ctx.sessionAttribute("user");
		if (p != null) {
			System.out.println("Logged in as " + p.getUsername());
			ctx.json(p);
			ctx.status(200);
		} else {
			System.out.println("Not logged in");
			ctx.status(400);
		}
	}
	
	public static void logIn(Context ctx) {
		System.out.println("Logging in");
		String username = ctx.queryParam("user");
		String password = ctx.queryParam("pass");
		
		Person p = personServ.getPersonByUsername(username);
		if (p != null) {
			if (p.getPassword().equals(password))
			{
				System.out.println("Logged in as " + p.getUsername());
				ctx.status(200);
				ctx.json(p);
				ctx.sessionAttribute("user", p);
			}
			else
			{
				// password mismatch
				ctx.status(400);
			}
		}
		else
		{
			// username not found
			ctx.status(404);
		}
	}
	
	public static void logOut(Context ctx) {
		System.out.println("Logging out");
		ctx.req.getSession().invalidate();
		ctx.status(200);
	}

}
