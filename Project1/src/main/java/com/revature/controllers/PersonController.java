package com.revature.controllers;

import com.revature.beans.Person;

import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;

import io.javalin.http.Context;

public class PersonController {
	private static PersonService personServ = new PersonServiceImpl();
	public static void checkLogin(Context ctx) {
		System.out.println("Checking login");
		Person p = ctx.sessionAttribute("empid");
		if (p != null) {
			System.out.println("Logged in as " + p.getFirstName());
			ctx.json(p);
			ctx.status(200);
		} else {
			System.out.println("Not logged in");
			ctx.status(400);
		}
	}
	public static void logIn(Context ctx) {
		System.out.println("Logging in");
		String employeeId = ctx.queryParam("empid");
		String password = ctx.queryParam("emppw");
		
		Person p = personServ.getPersonById(Integer.parseInt(employeeId));
		if (p != null) {
			if (p.getPassword().equals(password))
			{
				System.out.println("Logged in as " + p.getFirstName());
				String name=p.getFirstName();
				ctx.status(200);
				ctx.json(p);
				ctx.sessionAttribute("empid", p);
			}
			else
			{
				// password mismatch
				ctx.status(400);
			}
		}
		else
		{
			// id not found
			ctx.status(404);
		}
	}
	
	public static void logOut(Context ctx) {
		System.out.println("Logging out");
		ctx.req.getSession().invalidate();
		ctx.status(200);
	}
	
//	public static void registerUser(Context ctx) {
//		Person newPerson = ctx.bodyAsClass(Person.class);
//		try {
//			personServ.addPerson(newPerson);
//		}
//		catch (NonUniqueUsernameException e) {
//			System.out.println("Username already taken :(");
//			ctx.status(409); // 409 = conflict
//		}
//		ctx.status(200);
//	}
	
	public static void getUserById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Person p = personServ.getPersonById(id);
		if (p != null) {
			ctx.status(200);
			ctx.json(p);
		} else {
			ctx.status(404);
		}
	}
	
	public static void updateUser(Context ctx) {
		Person tempPerson = ctx.bodyAsClass(Person.class);
		personServ.updatePerson(tempPerson);
		ctx.status(202);
	}
	
	public static void deleteUser(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Person person = personServ.getPersonById(id);
		personServ.deletePerson(person);
		ctx.status(204);
	}
}
