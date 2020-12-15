package controller;

import java.util.Set;

import exceptions.NonUniqueUsernameException;
import io.javalin.http.Context;
import models.Users;
import servicespackage.user.User_Service;
import servicespackage.user.User_ServiceImpl;

public class UsersController {
	private static User_Service userServ = new User_ServiceImpl();
	public static void checkLogin(Context ctx) {
		System.out.println("Checking login");
		Users p = ctx.sessionAttribute("user");
		if (p != null) {
			System.out.println("Logged in as " + p.getUsername());
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
		
		Users p = userServ.getUserByUsername(username);
		if (p != null) {
			if (p.getPasswords().equals(password))
			{
				System.out.println("Logged in as " + p.getUsername());
				ctx.status(200);
				ctx.json(p);
				ctx.sessionAttribute("user", p);
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
	
	public static void getUserById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Users u = userServ.getUserById(id);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getByUsername(Context ctx) {
		String s = String.valueOf(ctx.pathParam("username"));
		Users u = userServ.getUserByUsername(s);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	
	public static void addUser(Context ctx) throws NonUniqueUsernameException {
		Users u = ctx.bodyAsClass(Users.class);
		userServ.addUser(u);
		ctx.status(201);
	}
	
	public static void updateUser(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Users u = ctx.bodyAsClass(Users.class);
		if (u != null) {
			ctx.status(200);
			userServ.updateUser(u);
		} else {
			ctx.status(404);
		}
	}
}
