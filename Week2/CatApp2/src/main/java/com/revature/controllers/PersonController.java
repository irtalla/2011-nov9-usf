package com.revature.controllers;


import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.services.CatService;
import com.revature.services.CatServiceImpl;
import com.revature.services.PersonService;
import com.revature.exceptions.NonUniqueUsernameException;
import com.revature.services.PersonServiceImpl;

import io.javalin.http.Context;

public class PersonController {
private static PersonService personServ = new PersonServiceImpl();

	
	public static void logIn(Context ctx) {
		
	}
	
	public static void logOut(Context ctx) {
	 
		ctx.req.getSession().invalidate();
	}
	
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
	}
	
	public static void getUserById(Context ctx) {
		
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
		ctx.status(200);
	}
}
