package com.revature.controllers;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Person;
import com.revature.beans.StoryPitch;
import com.revature.services.PersonServices;
import com.revature.services.PersonServicesImpl;
import com.revature.services.StoryPitchServices;
import com.revature.services.StoryPitchServicesImpl;

import io.javalin.http.Context;

public class PersonController {
	
	private static PersonServices personServ = new PersonServicesImpl();
	private static StoryPitchServices storyPitchServ = new StoryPitchServicesImpl();
	
	public static void checkLogin(Context ctx) {
		System.out.println("Checking login");
		Person p = ctx.sessionAttribute("user");
		//System.out.println(p);
		if (p != null) {
			System.out.println("Logged in as " + p.getName());
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
		
		Person p = personServ.getByUsername(username);
		//System.out.println(p);
		if (p != null) {
			if (p.getPassword().equals(password))
			{
				System.out.println("Logged in as " + p.getName());
				ctx.status(200);
				ctx.json(p);
				ctx.sessionAttribute("user", p);
				//System.out.println(p);
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
	
	public static void registerUser(Context ctx) {
		Person newPerson = ctx.bodyAsClass(Person.class);
		try {
			personServ.addPerson(newPerson);
		}
		catch (Exception e) {
			System.out.println("Username already taken :(");
			ctx.status(409); // 409 = conflict
		}
		ctx.status(200);
	}
	
	public static Person getUserById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Person p = personServ.getByPersonID(id);
		if (p != null) {
			ctx.status(200);
			//System.out.println(p);
			ctx.json(p);
			ctx.sessionAttribute("rejector", p);
			return p;
		} else {
			ctx.status(404);
			return null;
		}
	}
	
	public static void getStories(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Person p = personServ.getByPersonID(id);
		Set<StoryPitch> sps = new HashSet<StoryPitch>();
		if (p != null) {
			sps = storyPitchServ.getByAuthorID(id);
			ctx.status(200);
			ctx.json(sps);
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
		Person person = personServ.getByPersonID(id);
		personServ.deletePerson(person);
		ctx.status(204);
	}
}
