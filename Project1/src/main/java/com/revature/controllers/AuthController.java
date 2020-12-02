package com.revature.controllers;

import com.revature.beans.Person;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;

import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;

import io.javalin.http.Context;

public class AuthController {
	
	private static PersonService personServ = new PersonServiceImpl(); 
	
	public static void login(Context ctx) {
		
		System.out.println( ctx.body() );
		
//		Person person = null; 
//		try {
//			person = ctx.bodyAsClass(Person.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	    GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	      
	    Gson gson = builder.create(); 
	    Person person = gson.fromJson( ctx.body(), Person.class); 
	    System.out.println(person);
	      
	      
		
		
		System.out.println(person.getUsername());
		ctx.status(200);
		
//		while (true) {
//			System.out.print("Enter username: ");
//			String username = userInputScanner.nextLine();
//			System.out.print("Enter password: ");
//			String password = userInputScanner.nextLine();
//			
//			Person user = personServ.getPersonByUsername(username);
//			if (user == null) {
//				System.out.print("Nobody exists with that username. ");
//			} else if (user.getPassword().equals(password)) {
//				System.out.println("Welcome back!");
//				Application.setCurrentUser(user);
//				Application.initControllers( Application.currentUser.getRole().getName() );
//				break;
//			} else {
//				System.out.print("That password is incorrect. ");
//			}
//			System.out.println("Do you want to try again? 'yes' for yes, other for 'no' for no.");
//			String input = userInputScanner.nextLine();
//			if (input.equalsIgnoreCase("no")) {
//				break;
//			}
//		}
	};
}
