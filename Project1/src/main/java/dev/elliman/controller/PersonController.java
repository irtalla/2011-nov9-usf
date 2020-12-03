package dev.elliman.controller;

import dev.elliman.beans.Person;
import dev.elliman.services.PersonService;
import dev.elliman.services.PersonServiceImpl;
import io.javalin.http.Context;

public class PersonController {
	
	private static PersonService ps = new PersonServiceImpl();
	
	public static void login(Context ctx) {
		String username = ctx.queryParam("username");
		String password = ctx.queryParam("password");
		//System.out.println("logging in " + username + " with password " + password);
		
		Person p = ps.login(username,password);
		
		if(p == null) {
			ctx.status(400);
		} else {
			ctx.status(200);
			ctx.json(p);
			ctx.sessionAttribute("user", p);
		}
	}
}
