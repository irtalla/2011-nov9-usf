package com.revature.controllers;

import com.revature.beans.Individual;
import com.revature.services.IndividualService;
import com.revature.services.IndividualServiceImpl;

import io.javalin.http.Context;

public class IndividualController {
	private static IndividualService IndividualServ = new IndividualServiceImpl();
	public static void checkLogin(Context ctx) {
		System.out.println("Checking login");
		Individual p = ctx.sessionAttribute("user");
		if (p != null) {
			System.out.println("Logged in as " + p.getName());
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
		
		Individual p = IndividualServ.getIndividualByUsername(username);
		if (p != null) {
			if (p.getPasswd().equals(password))
			{
				System.out.println("Logged in as " + p.getName());
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
	
	public static void registerUser(Context ctx) {
		Individual newIndividual = ctx.bodyAsClass(Individual.class);
		try {
			IndividualServ.addIndividual(newIndividual);
		}
		catch (Exception e) {
			System.out.println("Username already taken :(");
			ctx.status(409); // 409 = conflict
		}
		ctx.status(200);
	}
	
	public static void getUserById(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Individual p = IndividualServ.getIndividualById(id);
		if (p != null) {
			ctx.status(200);
			ctx.json(p);
		} else {
			ctx.status(404);
		}
	}
	
	public static void updateUser(Context ctx) {
		Individual tempIndividual = ctx.bodyAsClass(Individual.class);
		IndividualServ.updateIndividual(tempIndividual);
		ctx.status(202);
	}
	
	public static void deleteUser(Context ctx) {
		Integer id = Integer.valueOf(ctx.pathParam("id"));
		Individual Individual = IndividualServ.getIndividualById(id);
		IndividualServ.deleteIndividual(Individual);
		ctx.status(204);
	}
}
