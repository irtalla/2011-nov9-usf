package com.revature.controllers;

import com.revature.beans.Author;
import com.revature.beans.Editor;
import com.revature.beans.User;
import com.revature.services.UserFunctions;
import com.revature.services.UserServicesImpl;

import io.javalin.http.Context;

public class UserController {
	
	private static UserFunctions usi = new UserServicesImpl();
	
	
	public static void validateUser(Context ctx) {
		String username = ctx.queryParam("username");
		String password = ctx.queryParam("password");
		Object foundUser = usi.retrieveAUser(username, password);
		
		if (foundUser instanceof Author) {
			Author author = (Author)foundUser;
			author.getUserInfo().setRole("author");
			ctx.json(foundUser);
			ctx.sessionAttribute("user", author);
			ctx.status(200);
		}
		else if (foundUser instanceof Editor) {
			Editor editor = (Editor)foundUser;
			editor.getUserInfo().setRole("editor");
			ctx.json(foundUser);
			ctx.sessionAttribute("user", editor);
			ctx.status(200);
		}
		else {
			ctx.status(404);
		}
	}
	
	public static void logOut(Context ctx) {
		System.out.println("Logging out");
		ctx.req.getSession().invalidate();
		ctx.status(200);
	}

	
	public static void addUser(Context ctx) {
		String username = ctx.queryParam("username");
		String password = ctx.queryParam("password");
		
		int newUserID = usi.registerAUser(username, password);
		
		if (newUserID != -1) {
			ctx.status(200);
		}
		else {
			ctx.status(500);
		}
	}
	
	public static void removeUser(Context ctx) {
		//User username = ctx
	}
	
	public static void checkLogin(Context ctx) {
		System.out.println("Checking login");
		Object foundUser = ctx.sessionAttribute("user");
		Author a = null;
		Editor e = null;
		
		if (foundUser instanceof Author) {
			a = (Author)foundUser;
		}
		else if (foundUser instanceof Editor) {
			e = (Editor)foundUser;
		}
		else {
			//it used imaginary table type/bean. Nothing happened.
		}
		
		
		if (a != null) {
			System.out.println("Logged in as " + a.getUserInfo().getUsername());
			ctx.json(a);
			ctx.status(200);
		} else if (e != null) {
			System.out.println("Logged in as " + e.getUserInfo().getUsername());
			ctx.json(e);
			ctx.status(200);
		} else {
			System.out.println("Not logged in");
			ctx.status(400);
		}
	}
}


