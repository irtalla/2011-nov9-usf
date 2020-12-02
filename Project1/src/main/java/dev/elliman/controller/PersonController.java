package dev.elliman.controller;

import io.javalin.http.Context;

public class PersonController {
	
	public static void login(Context ctx) {
		String username = ctx.queryParam("username");
		String password = ctx.queryParam("password");
		System.out.println("logging in " + username + " with password " + password);
	}
}
