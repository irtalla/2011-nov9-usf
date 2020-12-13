package com.revature.controllers;

import com.revature.beans.Person;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.PersonServiceImpl;

import io.javalin.http.Context;

public class PersonController extends GenericController<Person>{
	public PersonController() {
		super(Person.class);
	}

	@Override
	PersonServiceImpl getServ() {
		return new PersonServiceImpl();
	}
	
	public void checkLogin(Context ctx) {
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
	
	public void logIn(Context ctx) {
		System.out.println("Logging in");
		String username = ctx.queryParam("user");
		String password = ctx.queryParam("pass");
		
		Person p = getServ().getByUsername(username);
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
	
	public void logOut(Context ctx) {
		System.out.println("Logging out");
		ctx.req.getSession().invalidate();
		ctx.status(200);
	}
	
	public void registerUser(Context ctx) {
		Person newPerson = ctx.bodyAsClass(Person.class);
		try {
			newPerson = getServ().addPerson(newPerson);
		} catch (NonUniqueUsernameException e) {
			System.out.println("Username already taken :(");
			ctx.status(409); // 409 = conflict
		}
		ctx.status(200);
	}
	
	public void updateUser(Context ctx) {
		Person newPerson = ctx.bodyAsClass(Person.class);
		try {
			newPerson = getServ().updatePerson(newPerson);
		} catch (NonUniqueUsernameException e) {
			System.out.println("Username already taken :(");
			ctx.status(409); // 409 = conflict
		}
		ctx.status(200);
	}
}
