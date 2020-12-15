package com.revature.controllers;

import java.util.Set;

import com.revature.beans.Genre;
import com.revature.beans.Person;
import com.revature.beans.Pitch;
import com.revature.beans.Role;
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
			if (p.getPassword().equals(password)){
				System.out.println("Logged in as " + p.getUsername());
				ctx.status(200);
				ctx.json(p);
//				ctx.sessionAttribute("user", p);
			}else{
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
		System.out.println(ctx.resultString());
		Person newPerson = ctx.bodyAsClass(Person.class);
		try {
			getServ().addPerson(newPerson);
			newPerson = getServ().getByUsername(newPerson.getUsername());
			System.out.println(newPerson.toString());
			ctx.json(newPerson);
		} catch (NonUniqueUsernameException e) {
			System.out.println("Username already taken :(");
			ctx.status(409); // 409 = conflict
		}
		ctx.status(200);
	}
	
	public void updateUser(Context ctx) {
		Person newPerson = ctx.bodyAsClass(Person.class);
		try {
			getServ().updatePerson(newPerson);
			ctx.json(newPerson);
		} catch (NonUniqueUsernameException e) {
			System.out.println("Username already taken :(");
			ctx.status(409); // 409 = conflict
		}
		ctx.status(200);
	}
	
	public void getAllAuthors(Context ctx) {
		try {
			Set<Person> allAuthors = getServ().getAllPeopleWithRole(Role.AUTHOR);
			ctx.json(allAuthors);
		} catch (Exception e) {
			System.out.println("Error! :(");
			ctx.status(409); // 409 = conflict
		}
		ctx.status(200);
	}
	
	public void getAllEditorsWithRole(Context ctx) {
		Role role = ctx.queryParam("role", Role.class).get();
		try {
			Set<Person> allPeopleWithRole = getServ().getAllPeopleWithRole(role);
			ctx.json(allPeopleWithRole);
		} catch (Exception e) {
			System.out.println("Error! :(");
			ctx.status(409); // 409 = conflict
		}
		ctx.status(200);
	}
	
	public void getAllEditorsWithRoleAndGenre(Context ctx) {
		Genre genre = ctx.queryParam("genre", Genre.class).get();
		Role role = ctx.queryParam("role", Role.class).get();
		
		try {
			Set<Person> allPeopleWithRole = getServ().getAllEditorsWithRole(role, genre);
			ctx.json(allPeopleWithRole);
		} catch (Exception e) {
			System.out.println("Error! :(");
			ctx.status(409); // 409 = conflict
		}
		ctx.status(200);
	}
}
