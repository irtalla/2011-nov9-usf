package com.revature.controllers;




import com.revature.beans.User;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

import io.javalin.http.Context;

public class UserController {

	private static UserService userServ = new UserServiceImpl();
	public static void checkLogin(Context ctx) {
		System.out.println("Checking login");
		User u = ctx.sessionAttribute("user");
		if (u != null) {
			System.out.println("Logged in as " + u.getUsername());
			ctx.json(u);
			ctx.status(200);
		} else {
			System.out.println("Not logged in");
			ctx.status(400);
		}
	}
	
	//LOGIN
	public static void logIn(Context ctx) {
		System.out.println("Logging in");
		String username = ctx.queryParam("user");
		String password = ctx.queryParam("pass");
		
		User u = userServ.getUserByUsername(username);
		if (u != null) {
			if (u.getPassword().equals(password))
			{
				System.out.println("Logged in as " + u.getUsername());
				ctx.status(200);
				ctx.json(u);
				ctx.sessionAttribute("user", u);
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
	
	//LOG OUT
	public static void logOut(Context ctx) {
		System.out.println("Logging out");
		ctx.req.getSession().invalidate();
		ctx.status(200);
	}
	
	public static void getUserById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		User u = userServ.getUserById(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	
	public static void updateUser(Context ctx) {
		User tempUser = ctx.bodyAsClass(User.class);
		userServ.updateUser(tempUser);
		ctx.status(202);
	}
	
	public static void deleteUser(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		User user = userServ.getUserById(id);
		userServ.deleteUser(user);
		ctx.status(204);
	}
}


