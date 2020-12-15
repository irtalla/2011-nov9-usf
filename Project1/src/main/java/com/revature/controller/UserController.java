package com.revature.controller;

import java.util.Set;

import com.revature.exceptions.*;
import com.revature.models.Committee;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

import io.javalin.http.Context;

public class UserController {
	private static UserService userServ = new UserServiceImpl();
	
	public static void checkLogin(Context ctx) {
		System.out.println("Checking login");
		User u = ctx.sessionAttribute("user");
		if (u != null) {
			System.out.println("Logged in as: " + u.getUsername());
			ctx.json(u);
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
				System.out.println("Incorrect password");
				ctx.status(400);
			}
		}
		else
		{
			// username not found
			System.out.println("Username not found");
			ctx.status(404);
		}
		
	}
	
	public static void logOut(Context ctx) {
		System.out.println("Logging out");
		ctx.req.getSession().invalidate();
		ctx.status(200);
	}
	
	public static void registerUser(Context ctx) {
		System.out.println("Registering user");
		User newUser = ctx.bodyAsClass(User.class);
		try {
			userServ.addUser(newUser);
		} catch (NonUniqueUsernameException e) {
			System.out.println("Username already taken");
			ctx.status(455);
			return;
		} catch (NonUniqueEmailException e) {
			System.out.println("Email already used");
			ctx.status(456);
			return;
		} catch (InvalidEmailException e) {
			System.out.println("Invalid email format");
			ctx.status(457);
			return;
		}
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
	
	public static void getUserByUsername(Context ctx) {
		String username = String.valueOf(ctx.pathParam("username"));
		User u = userServ.getUserByUsername(username);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getUserByEmail(Context ctx) {
		String email = String.valueOf(ctx.pathParam("email"));
		User u = userServ.getUserByEmail(email);
		if (u != null) {
			ctx.status(200);
			ctx.json(u);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getUserByRole(Context ctx) {
		Role role = ctx.bodyAsClass(Role.class);
		Set<User> users = userServ.getUserByRole(role);
		if (users != null) {
			ctx.status(200);
			ctx.json(users);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getAllUsers(Context ctx) {
		Set<User> users = userServ.getAllUsers();
		if (users != null) {
			ctx.status(200);
			ctx.json(users);
		} else {
			ctx.status(404);
		}
	}
	
	public static void getUserCommittees(Context ctx) {
		User u = userServ.getUserById(Integer.valueOf(ctx.pathParam("id")));
		Set<Committee> committees = userServ.getUserCommitees(u);
		if (committees != null && !committees.isEmpty()) {
			ctx.status(200);
			ctx.json(committees);
		} else {
			ctx.status(404);
		}
	}
	
	public static void updateUser(Context ctx) {
		User tempUser = ctx.bodyAsClass(User.class);
		try {
			userServ.updateUser(tempUser);
		}catch (NonUniqueUsernameException e) {
			System.out.println("Username already taken");
			ctx.status(455);
		} catch (NonUniqueEmailException e) {
			System.out.println("Email already used");
			ctx.status(456);
		} catch (InvalidEmailException e) {
			System.out.println("Invalid email format");
			ctx.status(457);
		}

		ctx.status(202);
	}
	
	public static void deleteUser(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		User user = userServ.getUserById(id);
		userServ.deleteUser(user);
		ctx.status(204);
	}

}
