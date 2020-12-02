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
	    GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	    Gson gson = builder.create(); 
	    Person deserializedPerson, queriedPerson;  
	    
	    deserializedPerson = gson.fromJson( ctx.body(), Person.class);
//	    queriedPerson = personServ.getPersonByUsername(deserializedPerson.getUsername());
//	    
//	    if ( queriedPerson == null ) {
//	    	ctx.result("No user with that password found"); 
//	    	ctx.status(404);
//	    }
//	    
//	    if ( queriedPerson.getPassword().equals(deserializedPerson.getPassword() )) {
//	    	ctx.result("No combination of that username and password found");
//	    	ctx.status(404);
//	    }
	    
	    try {
		    ctx.json( gson.toJson(deserializedPerson) );
			ctx.status(200);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	ctx.status(500);
	    }
		
	};
	
	public static void logOut(Context ctx) {
		 
		ctx.req.getSession().invalidate();
	}
	
	public static void registerUser(Context ctx){
	    GsonBuilder builder = new GsonBuilder(); 
	    builder.setPrettyPrinting(); 
	    Gson gson = builder.create(); 
	    Person newPerson = gson.fromJson( ctx.body(), Person.class); 
		try {
			personServ.addPerson(newPerson);
		}
		catch(Exception e){
			e.printStackTrace();
			ctx.status(409);
		}
		ctx.status(200);
	}

}
