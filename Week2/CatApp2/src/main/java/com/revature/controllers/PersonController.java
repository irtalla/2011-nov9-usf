package com.revature.controllers;

import com.revature.beans.Person;
import com.revature.services.PersonService;

import io.javalin.http.Context;

public class PersonController {
	private static PersonService ps;
	
	public static void logIn(Context ctx) {
		
	}
	
	public static void logOut(Context ctx) {
	 
		ctx.req.getSession().invalidate();
	}
	
	public static void registerUser(Context ctx) {
		
	}
	
	public static void getUserById(Context ctx) {
		
	}
	
	public static void updateUser(Context ctx) {
		
		Person persons=ctx.bodyAsClass(Person.class);
		ps.updatePerson(persons);
		
	}
	
	public static void deleteUser(Context ctx) {
		
	}
}
