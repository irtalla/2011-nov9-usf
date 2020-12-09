package com.revature.controllers;

import com.revature.beans.User;
import com.revature.services.UserFunctions;
import com.revature.services.UserServicesImpl;

import io.javalin.http.Context;

public class UserController {
	
	private static UserFunctions usi = new UserServicesImpl();
	
	
	public static void validateUser(Context ctx) {
		String username = ctx.queryParam("username");
		String password = ctx.queryParam("password");
		User foundUser = usi.retrieveAUser(username, password);
		
		if (foundUser != null) {
			ctx.json(foundUser);
			ctx.status(200);
		}
		else {
			ctx.status(404);
		}
	}
	
	public static void addUser(Context ctx) {
		
	}
	
	public static void removeUser(Context ctx) {
		
	}
}
