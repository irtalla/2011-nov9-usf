package com.revature.controllers;

<<<<<<< HEAD
import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.services.CatService;
import com.revature.services.CatServiceImpl;
=======
import com.revature.beans.Person;
<<<<<<< HEAD
import com.revature.services.PersonService;
=======
import com.revature.exceptions.NonUniqueUsernameException;
>>>>>>> 3aefbb2a54897fe9b96974090601361665a258d7
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;
>>>>>>> 3aefbb2a54897fe9b96974090601361665a258d7

import io.javalin.http.Context;

public class PersonController {
<<<<<<< HEAD
	private static PersonService ps;
=======
	private static PersonService personServ = new PersonServiceImpl();
>>>>>>> 3aefbb2a54897fe9b96974090601361665a258d7
	
	public static void logIn(Context ctx) {
		
	}
	
	public static void logOut(Context ctx) {
	 
		ctx.req.getSession().invalidate();
	}
	
<<<<<<< HEAD
	public static void registerUser(Context ctx) {
		//
=======
	public static void registerUser(Context ctx){
		Person newPerson = ctx.bodyAsClass(Person.class);
		try {
			personServ.addPerson(newPerson);
		}
		catch(NonUniqueUsernameException e){
			System.out.println("Username already taken. :(");
			ctx.status(409);
		}
		ctx.status(200);
>>>>>>> 3aefbb2a54897fe9b96974090601361665a258d7
	}
	
	public static void getUserById(Context ctx) {
		
	}
	
	public static void updateUser(Context ctx) {
		
		Person persons=ctx.bodyAsClass(Person.class);
		ps.updatePerson(persons);
		
	}
	
	public static void deleteUser(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Person person = personServ.getPersonById(id);
<<<<<<< HEAD
		if (person != null) {
			ctx.status(200);
			ctx.json(person);
		} else {
			ctx.status(404);
		}
=======
		personServ.deletePerson(person);
		ctx.status(200);
>>>>>>> 3aefbb2a54897fe9b96974090601361665a258d7
	}
}
