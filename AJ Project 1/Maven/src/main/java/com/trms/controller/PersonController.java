package com.trms.controller;

import com.trms.beans.Person;
import com.trms.services.PersonService;
import com.trms.services.PersonServiceImpl;

import io.javalin.http.Context;

public class PersonController {
	private static PersonService personServ = new PersonServiceImpl();
	
	public static void checkLogin(Context ctx) {
		Person p = ctx.sessionAttribute("user");
		if (p != null) {
			ctx.json(p);
			ctx.status(200);
		} else ctx.status(400);
	}
	
	public static void logIn(Context ctx) {
		String username = ctx.queryParam("user");
		String password = ctx.queryParam("pass");
		System.out.println("logging in");
		Person p = personServ.getPersonByUsername(username);
		
		if (p != null) {
			if (p.getPassword().equals(password)) {
				ctx.status(200);
				ctx.json(p);
				ctx.sessionAttribute("user", p);
			} else ctx.status(400);
		} else ctx.status(400);
	}
	public static void logOut(Context ctx) {
		System.out.println("Logging out");
		ctx.req.getSession().invalidate();
		ctx.status(200);
	}
	
	public static void registerUser(Context ctx) {
		Person p = ctx.bodyAsClass(Person.class);
		if (personServ.getPersonByUsername(p.getUsername()) != null) {
			ctx.status(409);
		} else {
			ctx.status(200);
			p.setId(personServ.addPerson(p));
//			ctx.json(p);
//			ctx.sessionAttribute("user", p);
		}
		
	}
	public static void getUserById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Person p = personServ.getPersonById(id);
		if (p != null) {
			ctx.status(200);
			ctx.json(p);
		} else ctx.status(400);
	}
	public static void updateUser(Context ctx) {
		Person p = ctx.bodyAsClass(Person.class);
		personServ.updatePerson(p);
		ctx.status(200);
	}
}
